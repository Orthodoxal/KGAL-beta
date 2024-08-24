package genetic.clusters.base

import genetic.clusters.base.builder.ClusterBuilder
import genetic.clusters.base.lifecycle.ClusterLifecycle
import genetic.clusters.base.population.Population
import genetic.clusters.base.state.ClusterState
import genetic.clusters.base.state.StopPolicy
import genetic.clusters.base.state.clusterStateMachine
import genetic.stat.StatisticNote
import genetic.stat.Statistics
import genetic.stat.StatisticsInstance
import genetic.utils.statAfter
import genetic.utils.statBefore
import genetic.utils.statOnLifecycleIteration
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.FlowCollector
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

abstract class AbstractCluster<V, F, L : ClusterLifecycle<V, F>> : Cluster<V, F>, ClusterBuilder<V, F, L> {
    override var name: String? = null
    override var state: ClusterState = ClusterState.INITIALIZE
        protected set(value) {
            field = clusterStateMachine(field, value)
        }

    override var random: Random = Random
    override var randomSeed: Int = 0
        set(value) {
            random = Random(value)
        }

    override lateinit var population: Population<V, F>
    override var iteration: Int = 0
    override var maxIteration: Int = 0
    override var fitnessFunction: ((V) -> F)? = null

    override var stat: StatisticsInstance? = null

    override var mainDispatcher: CoroutineDispatcher? = null
    override var extraDispatchers: Array<CoroutineDispatcher>? = null

    protected abstract val lifecycleScope: L
    protected abstract var lifecycle: suspend L.() -> Unit
    protected open var beforeLifecycle: suspend L.() -> Unit = { }
    protected open var afterLifecycle: suspend L.() -> Unit = { }

    protected var statisticsCoroutineContext: CoroutineContext = Dispatchers.IO
        set(value) {
            statisticsCoroutineScope = CoroutineScope(value)
        }
    protected var statisticsCoroutineScope: CoroutineScope = CoroutineScope(statisticsCoroutineContext)
    private var statFlowCollector: (suspend CoroutineScope.(stat: Statistics) -> Unit)? = null
    private var statCollector: FlowCollector<StatisticNote>? = null

    private var stopSignal: Boolean = false
    private var clusterJob: Job? = null

    override fun lifecycle(
        before: (suspend L.() -> Unit)?,
        after: (suspend L.() -> Unit)?,
        lifecycle: suspend L.() -> Unit
    ) {
        before?.let { beforeLifecycle = before }
        after?.let { afterLifecycle = after }
        this@AbstractCluster.lifecycle = lifecycle
    }

    override suspend fun start() {
        if (state == ClusterState.STARTED) return
        startByOption(iterationFrom = 0)
    }

    override suspend fun resume() {
        if (state != ClusterState.STOPPED)
            throw IllegalStateException("Cluster $name state $state incorrect for resuming: State must be STOPPED")

        startByOption(iterationFrom = iteration)
    }

    override suspend fun restart(resetPopulation: Boolean) {
        if (resetPopulation) {
            with(population) { population = Array(maxSize) { factory(it, random) } }
        }
        startByOption(iterationFrom = 0)
    }

    override suspend fun stop(stopPolicy: StopPolicy) {
        when (stopPolicy) {
            is StopPolicy.Default -> stopSignal = true
            is StopPolicy.Immediately -> {
                clusterJob?.cancel(
                    cause = CancellationException(
                        message = "Cluster $name stop cause force $stopPolicy policy",
                        cause = null
                    )
                )
                state = ClusterState.STOPPED
            }

            is StopPolicy.Timeout -> {
                stopSignal = true
                withTimeout(stopPolicy.millis) {
                    if (state != ClusterState.STOPPED) {
                        clusterJob?.cancel(
                            cause = CancellationException(
                                message = "Cluster $name stop cause $stopPolicy policy",
                                cause = null
                            )
                        )
                        stopSignal = false
                        state = ClusterState.STOPPED
                    }
                }
            }
        }
    }

    override fun collectStat(collector: FlowCollector<StatisticNote>) =
        this.apply { statCollector = collector }

    override fun statFlow(collector: suspend CoroutineScope.(stat: Statistics) -> Unit) =
        this.apply { statFlowCollector = collector }

    override fun setStatInstance(statisticsInstance: StatisticsInstance, coroutineContext: CoroutineContext) {
        statisticsCoroutineContext = coroutineContext
        stat = statisticsInstance
    }

    protected open fun prepareStatistics() {
        val stat = stat ?: return
        if (statisticsCoroutineScope.coroutineContext.job.isCancelled) {
            statisticsCoroutineScope = CoroutineScope(statisticsCoroutineContext)
        }

        with(statisticsCoroutineScope) {
            // TODO() переместить в реализацию Distributed
            statFlowCollector?.let { launch { it(stat) } }
            statCollector?.let { launch { stat.collect(it) } }
        }
    }

    protected open suspend fun startByOption(iterationFrom: Int) {
        this.iteration = iterationFrom
        state = ClusterState.STARTED
        val dispatcher = mainDispatcher
        if (dispatcher == null) {
            clusterJob = coroutineContext.job
            startCluster()
            // FIXME Добавить аналогичную проверку? if (generation == maxGeneration)
            state = ClusterState.FINISHED
        } else {
            with(CoroutineScope(coroutineContext)) {
                clusterJob = launch(dispatcher) {
                    startCluster()
                    if (iteration == maxIteration) {
                        state = ClusterState.FINISHED
                        statisticsCoroutineScope.cancel()
                    }
                }
            }
        }
    }

    protected open suspend fun startCluster() {
        if (iteration >= maxIteration) return

        with(lifecycleScope) {
            if (iteration == 0) {
                beforeLifecycle()
                stat?.let { statBefore(it) }
            }
            while (iteration < maxGeneration) {
                lifecycle()
                stat?.let { statOnLifecycleIteration(it) }
                if (this.stopSignal) {
                    state = ClusterState.FINISHED
                    this.stopSignal = false
                    break
                }
                this@AbstractCluster.iteration++
                if (this@AbstractCluster.stopSignal) {
                    state = ClusterState.STOPPED
                    this@AbstractCluster.stopSignal = false
                    return
                }
            }
            if (iteration == maxGeneration || state == ClusterState.FINISHED) {
                this@AbstractCluster.stopSignal = false
                stat?.let { statAfter(it) }
                afterLifecycle()
            }
        }
    }
}
