package genetic.clusters.base

import genetic.clusters.base.builder.ClusterBuilder
import genetic.clusters.base.lifecycle.ClusterLifecycle
import genetic.clusters.base.population.Population
import genetic.clusters.base.state.ClusterState
import genetic.clusters.base.state.StopPolicy
import genetic.clusters.base.state.clusterStateMachine
import genetic.stat.StatisticsInstance
import genetic.utils.statAfter
import genetic.utils.statBefore
import genetic.utils.statOnLifecycleIteration
import kotlinx.coroutines.*
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

abstract class AbstractCluster<V, F, L : ClusterLifecycle<V, F>> : Cluster<V, F>, ClusterBuilder<V, F> {
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
    override var generation: Int = 0
    override var maxGeneration: Int = 0
    override lateinit var fitnessFunction: (V) -> F

    override var stat: StatisticsInstance? = null

    override var mainDispatcher: CoroutineDispatcher? = null
    override var extraDispatchers: Array<CoroutineDispatcher>? = null

    protected abstract val lifecycleScope: L
    protected abstract var lifecycle: suspend L.() -> Unit
    protected abstract var beforeLifecycle: suspend L.() -> Unit
    protected abstract var afterLifecycle: suspend L.() -> Unit

    private var stopSignal: Boolean = false
    private var clusterJob: Job? = null

    override suspend fun start(generationFrom: Int) {
        if (state == ClusterState.STARTED) return
        startByOption(generationFrom)
    }

    override suspend fun resume() {
        if (state != ClusterState.STOPPED)
            throw IllegalStateException("Cluster $name state $state incorrect for resuming: State must be STOPPED")

        startByOption(generationFrom = generation)
    }

    override suspend fun restart() {
        with (population) { population = Array(maxSize) { factory(it, random) } }
        startByOption(generationFrom = 0)
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

    private suspend fun startByOption(generationFrom: Int) {
        this.generation = generationFrom
        state = ClusterState.STARTED
        val dispatcher = mainDispatcher
        if (dispatcher == null) {
            clusterJob = coroutineContext.job
            startCluster()
            state = ClusterState.FINISHED
        } else {
            with(CoroutineScope(coroutineContext)) {
                clusterJob = launch(dispatcher) {
                    startCluster()
                    if (generation == maxGeneration) {
                        state = ClusterState.FINISHED
                    }
                }
            }
        }
    }

    private suspend fun startCluster() {
        if (generation >= maxGeneration) return

        with(lifecycleScope) {
            if (generation == 0) {
                beforeLifecycle()
                stat?.let { statBefore(it) }
            }
            while (generation < maxGeneration) {
                lifecycle()
                stat?.let { statOnLifecycleIteration(it) }
                if (this.stopSignal) {
                    state = ClusterState.FINISHED
                    this.stopSignal = false
                    break
                }
                this@AbstractCluster.generation++
                if (this@AbstractCluster.stopSignal) {
                    state = ClusterState.STOPPED
                    this@AbstractCluster.stopSignal = false
                    return
                }
            }
            if (generation == maxGeneration || state == ClusterState.FINISHED) {
                this@AbstractCluster.stopSignal = false
                stat?.let { statAfter(it) }
                afterLifecycle()
            }
        }
    }
}
