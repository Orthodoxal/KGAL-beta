package genetic.ga

import genetic.clusters.Cluster
import genetic.clusters.state.ClusterState
import genetic.clusters.state.ClusterStopPolicy
import genetic.ga.lifecycle.GALifecycle
import genetic.ga.lifecycle.LifecycleStartOption
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

abstract class AbstractGA<V, F> : GA<V, F> {
    protected var stopSignal: Boolean = false
    protected var state: GAState = GAState.INITIALIZE
        set(value) {
            field = gaStateMachine(field, value)
        }
    override var clusters: MutableList<Cluster<V, F>> = mutableListOf()
    private var gaJob: Job? = null
    override var iteration: Int = 0
    override var maxIteration: Int = 1

    protected var gaStatisticsCoroutineContext: CoroutineContext = Dispatchers.IO
        set(value) {
            gaStatisticsCoroutineScope = CoroutineScope(value)
        }
    protected var gaStatisticsCoroutineScope: CoroutineScope = CoroutineScope(gaStatisticsCoroutineContext)
    protected var statisticsInstance: StatisticsInstance = stat(0, 100, BufferOverflow.SUSPEND)
    private var statFlowCollector: (suspend CoroutineScope.(stat: Statistics) -> Unit)? = null
    private var statCollector: FlowCollector<StatisticNote>? = null

    protected abstract fun CoroutineScope.startByOption(
        startOption: LifecycleStartOption,
        iterationFrom: Int,
        coroutineContext: CoroutineContext,
    )

    private fun CoroutineScope.prepareStatisticsAndStartByOption(
        startOption: LifecycleStartOption,
        iterationFrom: Int,
        coroutineContext: CoroutineContext,
    ) {
        val clusterStats = clusters.mapNotNull { it.stat }
        if (clusterStats.isEmpty()) return startByOption(startOption, iterationFrom, coroutineContext)
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

        startByOption(startOption, iterationFrom, coroutineContext)
    }

    override fun CoroutineScope.start(iterationFrom: Int, coroutineContext: CoroutineContext) {
        if (state == GAState.STARTED) return
        prepareStatisticsAndStartByOption(LifecycleStartOption.START, iterationFrom, coroutineContext)
    }


    override fun CoroutineScope.resume(coroutineContext: CoroutineContext) {
        if (state != GAState.STOPPED) throw IllegalStateException("GA state $state incorrect for resuming: State must be STOPPED")

        prepareStatisticsAndStartByOption(LifecycleStartOption.RESUME, iteration, coroutineContext)
    }

    override fun CoroutineScope.stop(stopPolicy: ClusterStopPolicy, coroutineContext: CoroutineContext) {
        if (stopSignal) return
        stopSignal = true

        clusters.forEach {
            launch(coroutineContext) { it.stop(stopPolicy) }
        }
    }

    override fun CoroutineScope.restart(iterationFrom: Int, coroutineContext: CoroutineContext) {
        try {
            gaStatisticsCoroutineScope.coroutineContext.cancelChildren()
            gaJob?.cancel()
        } catch (_: CancellationException) {
            // nothing to do
        }

        prepareStatisticsAndStartByOption(LifecycleStartOption.RESTART, iterationFrom, coroutineContext)
    }

    override fun collectStat(collector: FlowCollector<StatisticNote>) = this.apply { statCollector = collector }

    override fun statFlow(collector: suspend CoroutineScope.(stat: Statistics) -> Unit) =
        this.apply { statFlowCollector = collector }

    protected fun CoroutineScope.launchGA(
        iterationFrom: Int,
        coroutineContext: CoroutineContext,
        startGA: suspend () -> Unit,
    ) {
        iteration = iterationFrom
        state = GAState.STARTED
        gaJob = launch(coroutineContext) {
            startGA()
            if (iteration == maxIteration || state == GAState.FINISHED) {
                state = GAState.FINISHED
                gaStatisticsCoroutineScope.cancel()
            }
        }
    }

    protected suspend inline fun <L : GALifecycle<V, F>> baseStartGA(
        lifecycleScope: L,
        beforeLifecycle: suspend L.() -> Unit,
        lifecycle: suspend L.() -> Unit,
        afterLifecycle: suspend L.() -> Unit,
    ) {
        with(lifecycleScope) {
            if (this@AbstractGA.iteration == 0) {
                beforeLifecycle()
                statBefore(statisticsInstance)
            }
            while (this@AbstractGA.iteration < maxIteration) {
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
            }
            if (this@AbstractGA.iteration == maxIteration || state == GAState.FINISHED) {
                this@AbstractGA.stopSignal = false
                statAfter(statisticsInstance)
                afterLifecycle()
            }
        }
    }
}
