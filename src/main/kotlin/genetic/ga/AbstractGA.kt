package genetic.ga

import genetic.clusters.base.state.ClusterState
import genetic.clusters.base.state.StopPolicy
import genetic.ga.lifecycle.GALifecycle
import genetic.ga.lifecycle.LifecycleStartOption
import genetic.ga.panmictic.lifecycle.PanmicticGALifecycleInstance
import genetic.ga.state.GAState
import genetic.ga.state.gaStateMachine
import genetic.stat.StatisticNote
import genetic.stat.Statistics
import genetic.stat.StatisticsInstance
import genetic.stat.stat
import genetic.utils.statAfter
import genetic.utils.statBefore
import genetic.utils.statOnLifecycleIteration
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.FlowCollector
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.cancellation.CancellationException

abstract class AbstractGA<V, F, L : GALifecycle<V, F>> : GA<V, F> {
    protected abstract val lifecycleScope: L
    protected abstract var lifecycle: suspend L.() -> Unit
    protected abstract var beforeLifecycle: suspend L.() -> Unit
    protected abstract var afterLifecycle: suspend L.() -> Unit

    protected var stopSignal: Boolean = false
    protected var state: GAState = GAState.INITIALIZE
        set(value) {
            field = gaStateMachine(field, value)
        }
    protected var gaJob: Job? = null

    protected var gaStatisticsCoroutineContext: CoroutineContext = Dispatchers.IO
        set(value) {
            gaStatisticsCoroutineScope = CoroutineScope(value)
        }
    protected var gaStatisticsCoroutineScope: CoroutineScope = CoroutineScope(gaStatisticsCoroutineContext)
    protected var statisticsInstance: StatisticsInstance = stat(0, 100, BufferOverflow.SUSPEND)
    private var statFlowCollector: (suspend CoroutineScope.(stat: Statistics) -> Unit)? = null
    private var statCollector: FlowCollector<StatisticNote>? = null

    protected fun prepareStatistics(
        clusterStat: Statistics,
    ) {
        if (gaStatisticsCoroutineScope.coroutineContext.job.isCancelled) {
            gaStatisticsCoroutineScope = CoroutineScope(gaStatisticsCoroutineContext)
        }

        with(gaStatisticsCoroutineScope) {
            launch { clusterStat.collect { statisticsInstance.emit(it) } }
            statFlowCollector?.let { launch { it(statisticsInstance) } }
            statCollector?.let { launch { statisticsInstance.collect(it) } }
        }
    }

    protected fun prepareStatistics(
        clusterStats: List<Statistics>,
    ) {
        if (clusterStats.isEmpty()) return
        if (gaStatisticsCoroutineScope.coroutineContext.job.isCancelled) {
            gaStatisticsCoroutineScope = CoroutineScope(gaStatisticsCoroutineContext)
        }

        with(gaStatisticsCoroutineScope) {
            clusterStats.forEach { clusterStat ->
                launch { clusterStat.collect { statisticsInstance.emit(it) } }
            }
            statFlowCollector?.let { launch { it(statisticsInstance) } }
            statCollector?.let { launch { statisticsInstance.collect(it) } }
        }
    }

    protected abstract fun applyStatistics()
    protected abstract fun CoroutineScope.stopGA(coroutineContext: CoroutineContext, stopPolicy: StopPolicy)
    protected abstract suspend fun L.execute()

    private fun CoroutineScope.startByOption(
        coroutineContext: CoroutineContext,
        startOption: LifecycleStartOption,
    ) {
        lifecycleScope.lifecycleStartOption = startOption
        state = GAState.STARTED

        launchGA(coroutineContext = coroutineContext)
    }

    override fun CoroutineScope.start(coroutineContext: CoroutineContext) {
        if (state == GAState.STARTED) return

        applyStatistics()
        startByOption(coroutineContext, LifecycleStartOption.START)
    }


    override fun CoroutineScope.resume(coroutineContext: CoroutineContext) {
        if (state != GAState.STOPPED) throw IllegalStateException("GA state $state incorrect for resuming: State must be STOPPED")

        applyStatistics()
        startByOption(coroutineContext, LifecycleStartOption.RESUME)
    }

    override fun CoroutineScope.stop(coroutineContext: CoroutineContext, stopPolicy: StopPolicy) {
        if (stopSignal) return
        stopSignal = true

        stopGA(coroutineContext, stopPolicy)
    }

    override fun CoroutineScope.restart(coroutineContext: CoroutineContext) {
        try {
            gaStatisticsCoroutineScope.coroutineContext.cancelChildren()
            gaJob?.cancel()
        } catch (_: CancellationException) {
            // nothing to do
        }

        applyStatistics()
        startByOption(coroutineContext, LifecycleStartOption.RESTART)
    }

    override fun collectStat(collector: FlowCollector<StatisticNote>) = this.apply { statCollector = collector }

    override fun statFlow(collector: suspend CoroutineScope.(stat: Statistics) -> Unit) =
        this.apply { statFlowCollector = collector }

    protected open fun CoroutineScope.launchGA(
        coroutineContext: CoroutineContext,
    ) {
        gaJob = launch(coroutineContext) {
            baseStartGA()
            if (state == GAState.FINISHED) {
                gaStatisticsCoroutineScope.cancel()
            }
        }
    }

    protected open suspend fun baseStartGA() {
        with(lifecycleScope) {
            if (lifecycleStartOption == LifecycleStartOption.START) {
                beforeLifecycle()
                statBefore(statisticsInstance)
            }
            execute()
            /*while (this@AbstractGA.iteration < maxIteration) {
                lifecycle()
                statOnLifecycleIteration(statisticsInstance)

                val allFinished = clusters.all { it.state == ClusterState.FINISHED }
                val allFinishedExpected = clusters.all { it.generation == it.maxGeneration }

                if (this.stopSignal || (allFinished && !allFinishedExpected)) {
                    // Cluster stop by stop conditions -> stop GA
                    state = GAState.FINISHED
                    this.stopSignal = false
                    break
                }

                if (allFinished) {
                    this@AbstractGA.iteration++
                }

                if (this@AbstractGA.stopSignal) {
                    state = GAState.STOPPED
                    this@AbstractGA.stopSignal = false
                    gaStatisticsCoroutineScope.coroutineContext.cancelChildren()
                    return
                }
            }*/
            if (state == GAState.FINISHED) {
                this@AbstractGA.stopSignal = false
                statAfter(statisticsInstance)
                afterLifecycle()
            }
        }
    }
}
