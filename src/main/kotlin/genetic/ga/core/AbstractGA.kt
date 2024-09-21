package genetic.ga.core

import genetic.ga.core.config.ConfigGA
import genetic.ga.core.lifecycle.Lifecycle
import genetic.ga.core.state.GAState
import genetic.ga.core.state.StopPolicy
import genetic.statistics.provider.StatisticsProvider
import kotlinx.coroutines.*
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

    override val statisticsProvider: StatisticsProvider by lazy {
        StatisticsProvider(name, configuration.statisticsConfig)
    }

    protected abstract val lifecycle: L
    protected val beforeEvolution: suspend L.() -> Unit = configuration.beforeEvolution
    protected val evolution: suspend L.() -> Unit = configuration.evolution
    protected val afterEvolution: suspend L.() -> Unit = configuration.afterEvolution

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
                statisticsProvider.stopCollectors(force = true)
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

    protected open suspend fun startByOption(iterationFrom: Int) {
        statisticsProvider.prepareStatistics()
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
        // stop all statistics collectors
        statisticsProvider.stopCollectors(force = false)
    }
}
