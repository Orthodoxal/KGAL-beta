package genetic.ga.core

import genetic.ga.core.config.ConfigGA
import genetic.ga.core.lifecycle.Lifecycle
import genetic.ga.core.state.GAState
import genetic.ga.core.state.StopPolicy
import genetic.stat.config.StatisticsConfig
import genetic.stat.note.StatisticNote
import genetic.stat.note.stat
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.takeWhile
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

abstract class AbstractGA<V, F, L : Lifecycle<V, F>>(
    configuration: ConfigGA<V, F, L>,
) : GA<V, F> {
    override var state: GAState = GAState.INITIALIZED
        protected set

    override val random: Random = configuration.random
    override var iteration: Int = 0

    override var fitnessFunction: (V) -> F = configuration.fitnessFunction
    override val mainDispatcher: CoroutineDispatcher? = configuration.mainDispatcher
    override val extraDispatchers: Array<CoroutineDispatcher>? = configuration.extraDispatchers

    protected abstract val lifecycle: L
    protected val beforeEvolution: suspend L.() -> Unit = configuration.beforeEvolution
    protected val evolution: suspend L.() -> Unit = configuration.evolution
    protected val afterEvolution: suspend L.() -> Unit = configuration.afterEvolution

    protected val statisticsConfig: StatisticsConfig = configuration.statisticsConfig
    protected var statistics: MutableSharedFlow<StatisticNote<Any?>> = statisticsConfig.flow
    protected val cancelableStatistics
        get() = statistics.takeWhile { it.statistic.name != STAT_STOP_COLLECT_FLAG }
    protected var statisticsCoroutineScope: CoroutineScope = statisticsConfig.newCoroutineScope
        get() {
            if (field.coroutineContext.job.isCancelled) {
                field = statisticsConfig.newCoroutineScope
            }
            return field
        }
    private var statFlowCollector: (suspend CoroutineScope.(stat: Flow<StatisticNote<Any?>>) -> Unit)? = null
    private var statCollector: FlowCollector<StatisticNote<Any?>>? = null

    private var pause: Boolean = false
    private var gaJob: Job? = null

    override suspend fun start() {
        if (state == GAState.STARTED) return
        startByOption(iterationFrom = 0)
    }

    override suspend fun resume() {
        if (state != GAState.STOPPED)
            throw IllegalStateException("GA $name state $state incorrect for resuming: State must be STOPPED")

        startByOption(iterationFrom = iteration)
    }

    override suspend fun restart(resetPopulation: Boolean) {
        if (resetPopulation) {
            with(population) { population = Array(maxSize) { random.factory() } }
        }
        startByOption(iterationFrom = 0)
    }

    override suspend fun stop(stopPolicy: StopPolicy) {
        when (stopPolicy) {
            is StopPolicy.Default -> pause = true
            is StopPolicy.Immediately -> {
                gaJob?.cancel(
                    cause = CancellationException(
                        message = "Cluster $name stop cause force $stopPolicy policy",
                        cause = null
                    )
                )
                statisticsCoroutineScope.cancel()
                state = GAState.STOPPED
            }

            is StopPolicy.Timeout -> {
                pause = true
                withTimeout(stopPolicy.millis) {
                    if (state == GAState.STOPPED || state is GAState.FINISHED) return@withTimeout
                    stop(stopPolicy = StopPolicy.Immediately)
                }
            }
        }
    }

    override fun collectStat(collector: FlowCollector<StatisticNote<Any?>>) =
        this.apply { statCollector = collector }

    override fun statFlow(collector: suspend CoroutineScope.(stat: Flow<StatisticNote<Any?>>) -> Unit) =
        this.apply { statFlowCollector = collector }

    override suspend fun emitStat(value: StatisticNote<Any?>) =
        statistics.emit(value)

    protected open fun prepareStatistics() {
        with(statisticsCoroutineScope) {
            statFlowCollector?.let { launch { it(cancelableStatistics) } }
            statCollector?.let { launch { cancelableStatistics.collect(it) } }
            if (statisticsConfig.enableDefaultCollector) {
                launch { cancelableStatistics.collect(statisticsConfig.defaultCollector) }
            }
        }
    }

    protected open suspend fun startByOption(iterationFrom: Int) {
        prepareStatistics()
        this.iteration = iterationFrom
        val dispatcher = mainDispatcher
        if (dispatcher == null) {
            gaJob = coroutineContext.job
            launch()
        } else {
            with(CoroutineScope(coroutineContext)) {
                gaJob = launch(dispatcher) { launch() }
            }
        }
    }

    protected suspend fun launch() {
        with(lifecycle) {
            beforeEvolve()
            evolve()
            afterEvolve()
        }
    }

    protected open suspend fun L.beforeEvolve() {
        pause = false
        finishByStopConditions = false
        finishedByMaxIteration = false

        if (iteration == 0) {
            beforeEvolution()
        }
    }

    protected open suspend fun L.evolve() {
        state = GAState.STARTED
        while (true) {
            this@AbstractGA.iteration++
            evolution()

            if (finishByStopConditions) {
                state = GAState.FINISHED.ByStopConditions
                break
            }

            if (finishedByMaxIteration) {
                state = GAState.FINISHED.ByMaxIteration
                break
            }

            if (pause) {
                state = GAState.STOPPED
                break
            }
        }
    }

    protected open suspend fun L.afterEvolve() {
        if (state is GAState.FINISHED) {
            afterEvolution()
        }

        // wait for all children coroutines of lifecycle completed
        coroutineContext.job.children.forEach { it.join() }
        // send STAT_STOP_COLLECT_FLAG as a terminal for all collectors
        stat(STAT_STOP_COLLECT_FLAG, null)
        // wait for all collectors of statistics completed
        statisticsCoroutineScope.coroutineContext.job.children.forEach { it.join() }
        // cancel statisticsCoroutineScope
        statisticsCoroutineScope.cancel()
    }

    protected companion object {
        const val STAT_STOP_COLLECT_FLAG = "STAT_STOP_COLLECT_FLAG"
    }
}
