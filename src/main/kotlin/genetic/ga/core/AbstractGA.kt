package genetic.ga.core

import genetic.ga.core.config.ConfigGA
import genetic.ga.core.lifecycle.Lifecycle
import genetic.ga.core.state.GAState
import genetic.ga.core.state.StopPolicy
import genetic.ga.core.state.clusterStateMachine
import genetic.stat.config.StatisticsConfig
import genetic.stat.note.StatisticNote
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

abstract class AbstractGA<V, F, L : Lifecycle<V, F>>(
    configuration: ConfigGA<V, F, L>,
) : GA<V, F> {
    override var state: GAState = GAState.INITIALIZE
        protected set(value) {
            field = clusterStateMachine(field, value)
        }

    override val random: Random = configuration.random
    override var iteration: Int = 0
    override val maxIteration: Int = configuration.maxIteration

    override var fitnessFunction: (V) -> F = configuration.fitnessFunction
    override val mainDispatcher: CoroutineDispatcher? = configuration.mainDispatcher
    override val extraDispatchers: Array<CoroutineDispatcher>? = configuration.extraDispatchers

    protected abstract val lifecycle: L
    protected val beforeEvolution: suspend L.() -> Unit = configuration.beforeEvolution
    protected val evolution: suspend L.() -> Unit = configuration.evolution
    protected val afterEvolution: suspend L.() -> Unit = configuration.afterEvolution

    protected val statisticsConfig: StatisticsConfig = configuration.statisticsConfig
    protected var stat: MutableSharedFlow<StatisticNote<Any?>> = statisticsConfig.flow
    protected var statisticsCoroutineScope: CoroutineScope = statisticsConfig.newCoroutineScope
        get() {
            if (field.coroutineContext.job.isCancelled) {
                field = statisticsConfig.newCoroutineScope
            }
            return field
        }
    private var statFlowCollector: (suspend CoroutineScope.(stat: SharedFlow<StatisticNote<Any?>>) -> Unit)? = null
    private var statCollector: FlowCollector<StatisticNote<Any?>>? = null

    private var stopSignal: Boolean = false
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
            is StopPolicy.Default -> stopSignal = true
            is StopPolicy.Immediately -> {
                gaJob?.cancel(
                    cause = CancellationException(
                        message = "Cluster $name stop cause force $stopPolicy policy",
                        cause = null
                    )
                )
                state = GAState.STOPPED
            }

            is StopPolicy.Timeout -> {
                stopSignal = true
                withTimeout(stopPolicy.millis) {
                    if (state != GAState.STOPPED) {
                        gaJob?.cancel(
                            cause = CancellationException(
                                message = "Cluster $name stop cause $stopPolicy policy",
                                cause = null
                            )
                        )
                        stopSignal = false
                        state = GAState.STOPPED
                    }
                }
            }
        }
    }

    override fun collectStat(collector: FlowCollector<StatisticNote<Any?>>) =
        this.apply { statCollector = collector }

    override fun statFlow(collector: suspend CoroutineScope.(stat: SharedFlow<StatisticNote<Any?>>) -> Unit) =
        this.apply { statFlowCollector = collector }

    override suspend fun emitStat(value: StatisticNote<Any?>) =
        stat.emit(value)

    protected open fun prepareStatistics() {
        with(statisticsCoroutineScope) {
            statFlowCollector?.let { launch { it(stat) } }
            statCollector?.let { launch { stat.collect(it) } }
            if (statisticsConfig.enableDefaultCollector) {
                launch { stat.collect(statisticsConfig.defaultCollector) }
            }
        }
    }

    protected open suspend fun startByOption(iterationFrom: Int) {
        prepareStatistics()
        this.iteration = iterationFrom
        state = GAState.STARTED
        val dispatcher = mainDispatcher
        if (dispatcher == null) {
            gaJob = coroutineContext.job
            startGA()
            // FIXME Добавить аналогичную проверку? if (iteration == maxIteration)
            state = GAState.FINISHED
        } else {
            with(CoroutineScope(coroutineContext)) {
                gaJob = launch(dispatcher) {
                    startGA()
                    if (iteration == maxIteration) {
                        state = GAState.FINISHED
                        statisticsCoroutineScope.cancel()
                    }
                }
            }
        }
    }

    protected open suspend fun startGA() {
        if (iteration >= maxIteration) return

        with(lifecycle) {
            if (iteration == 0) {
                beforeEvolution()
            }
            while (iteration < maxIteration) {
                evolution()
                if (this.stopSignal) {
                    state = GAState.FINISHED
                    this.stopSignal = false
                    break
                }
                this@AbstractGA.iteration++
                if (this@AbstractGA.stopSignal) {
                    state = GAState.STOPPED
                    this@AbstractGA.stopSignal = false
                    return
                }
            }
            if (iteration == maxIteration || state == GAState.FINISHED) {
                this@AbstractGA.stopSignal = false
                afterEvolution()
            }
        }
    }
}
