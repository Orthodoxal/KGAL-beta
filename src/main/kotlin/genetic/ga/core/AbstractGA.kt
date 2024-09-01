package genetic.ga.core

import genetic.ga.core.builder.GABuilder
import genetic.ga.core.lifecycle.GALifecycle
import genetic.ga.core.population.Population
import genetic.ga.core.state.GAState
import genetic.ga.core.state.StopPolicy
import genetic.ga.core.state.clusterStateMachine
import genetic.stat.note.StatisticNote
import genetic.stat.config.StatisticsConfig
import genetic.ga.core.builder.name
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

abstract class AbstractGA<V, F, L : GALifecycle<V, F>>(
    override val random: Random
) : GA<V, F>, GABuilder<V, F, L> {
    override var state: GAState = GAState.INITIALIZE
        protected set(value) {
            field = clusterStateMachine(field, value)
        }

    override lateinit var population: Population<V, F>
    override var iteration: Int = 0
    override var maxIteration: Int = 0
    override var fitnessFunction: ((V) -> F)? = null


    override var mainDispatcher: CoroutineDispatcher? = null
    override var extraDispatchers: Array<CoroutineDispatcher>? = null

    protected abstract val evolveScope: L
    protected open val baseBefore: suspend L.() -> Unit = { }
    protected open val baseEvolve: suspend L.() -> Unit = { }
    protected open val baseAfter: suspend L.() -> Unit = { }

    protected abstract var before: suspend L.() -> Unit
    protected abstract var evolve: suspend L.() -> Unit
    protected abstract var after: suspend L.() -> Unit

    protected var statisticsConfig: StatisticsConfig = StatisticsConfig
        set(value) {
            field = value
            stat = value.flow
            statisticsCoroutineScope = StatisticsConfig.coroutineScope
        }
    protected var stat: MutableSharedFlow<StatisticNote<Any?>> = StatisticsConfig.flow
    protected var statisticsCoroutineScope: CoroutineScope = StatisticsConfig.coroutineScope

    private var statFlowCollector: (suspend CoroutineScope.(stat: SharedFlow<StatisticNote<Any?>>) -> Unit)? = null
    private var statCollector: FlowCollector<StatisticNote<Any?>>? = null

    private var stopSignal: Boolean = false
    private var gaJob: Job? = null

    override fun GABuilder<V, F, L>.before(useDefault: Boolean, beforeEvolution: suspend L.() -> Unit) {
        before = beforeEvolution.takeIf { !useDefault } ?: { baseBefore(); beforeEvolution() }
    }

    override fun GABuilder<V, F, L>.evolve(evolution: suspend L.() -> Unit) {
        evolve = evolution
    }

    override fun GABuilder<V, F, L>.after(useDefault: Boolean, afterEvolution: suspend L.() -> Unit) {
        after = afterEvolution.takeIf { !useDefault } ?: { afterEvolution(); baseAfter() }
    }

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
            with(population) { population = Array(maxSize) { factory(it) } }
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

    override fun StatisticsConfig.applyToGA() {
        statisticsConfig = this
    }

    protected open fun prepareStatistics() {
        if (statisticsCoroutineScope.coroutineContext.job.isCancelled) {
            statisticsCoroutineScope = StatisticsConfig.coroutineScope
        }

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

        with(evolveScope) {
            if (iteration == 0) {
                before()
            }
            while (iteration < maxGeneration) {
                evolve()
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
            if (iteration == maxGeneration || state == GAState.FINISHED) {
                this@AbstractGA.stopSignal = false
                after()
            }
        }
    }

    internal suspend fun emitStat(value: StatisticNote<Any?>) = stat.emit(value)
}
