package genetic.clusters.simple_cluster

import genetic.chromosome.Chromosome
import genetic.clusters.AbstractCluster
import genetic.clusters.simple_cluster.builder.SimpleClusterBuilder
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle.Companion.BASE_AFTER_LIFECYCLE
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle.Companion.BASE_BEFORE_LIFECYCLE
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycleInstance
import genetic.clusters.state.ClusterState
import genetic.stat.StatisticsInstance
import genetic.utils.statAfter
import genetic.utils.statBefore
import genetic.utils.statOnLifecycleIteration
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

internal class SimpleClusterInstance<V, F> : AbstractCluster<V, F>(), SimpleClusterBuilder<V, F> {
    private val lifecycleScope: SimpleClusterLifecycle<V, F> by lazy { SimpleClusterLifecycleInstance(this) }
    private var beforeLifecycle: suspend SimpleClusterLifecycle<V, F>.() -> Unit = BASE_BEFORE_LIFECYCLE
    private lateinit var lifecycle: suspend SimpleClusterLifecycle<V, F>.() -> Unit
    private var afterLifecycle: suspend SimpleClusterLifecycle<V, F>.() -> Unit = BASE_AFTER_LIFECYCLE

    override var randomSeed: Int = 0
        set(value) {
            random = Random(value)
        }
    override lateinit var populationFactory: (index: Int, random: Random) -> Chromosome<V, F>
    override lateinit var fitnessFunction: (V) -> F
    override var mainDispatcher: CoroutineDispatcher? = null
    override var extraDispatchers: Array<CoroutineDispatcher>? = null

    override fun SimpleClusterBuilder<V, F>.lifecycle(
        before: (suspend SimpleClusterLifecycle<V, F>.() -> Unit)?,
        after: (suspend SimpleClusterLifecycle<V, F>.() -> Unit)?,
        lifecycle: suspend SimpleClusterLifecycle<V, F>.() -> Unit,
    ) {
        before?.let { beforeLifecycle = before }
        after?.let { afterLifecycle = after }
        this@SimpleClusterInstance.lifecycle = lifecycle
    }

    override suspend fun startByOption(generationFrom: Int) {
        super.generation = generationFrom
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
                statisticsInstance?.let { statBefore(it) }
            }
            while (generation < maxGeneration) {
                lifecycle()
                statisticsInstance?.let { statOnLifecycleIteration(it) }
                if (this.stopSignal) {
                    state = ClusterState.FINISHED
                    this.stopSignal = false
                    break
                }
                super.generation++
                if (this@SimpleClusterInstance.stopSignal) {
                    state = ClusterState.STOPPED
                    this@SimpleClusterInstance.stopSignal = false
                    return
                }
            }
            if (generation == maxGeneration || state == ClusterState.FINISHED) {
                this@SimpleClusterInstance.stopSignal = false
                statisticsInstance?.let { statAfter(it) }
                afterLifecycle()
            }
        }
    }

    override suspend fun restart() {
        population = Array(populationSize) { populationFactory(it, random) }
        startByOption(generationFrom = 0)
    }

    override fun setStatInstance(statisticsInstance: StatisticsInstance) {
        this.statisticsInstance = statisticsInstance
    }
}
