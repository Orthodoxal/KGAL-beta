package genetic.clusters.simple_cluster

import genetic.chromosome.Chromosome
import genetic.clusters.AbstractCluster
import genetic.clusters.simple_cluster.builder.SimpleClusterBuilder
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycleInstance
import genetic.clusters.simple_cluster.lifecycle.utils.fitnessAll
import genetic.clusters.state.ClusterState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

internal class SimpleClusterInstance<V, F> : AbstractCluster<V, F>(), SimpleClusterBuilder<V, F> {
    private val lifecycleScope: SimpleClusterLifecycle<V, F> by lazy { SimpleClusterLifecycleInstance(this) }
    private var beforeLifecycle: suspend SimpleClusterLifecycle<V, F>.() -> Unit = BASE_BEFORE_LIFECYCLE
    private var afterLifecycle: suspend SimpleClusterLifecycle<V, F>.() -> Unit = { }
    private lateinit var lifecycle: suspend SimpleClusterLifecycle<V, F>.() -> Unit

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

    override suspend fun start() {
        if (state == ClusterState.STARTED) return

        state = ClusterState.STARTED
        val dispatcher = mainDispatcher
        if (dispatcher == null) {
            clusterJob = coroutineContext.job
            startCluster()
        } else {
            coroutineScope {
                clusterJob = launch(dispatcher) { startCluster() }
            }
        }
        state = ClusterState.FINISHED
    }

    private suspend fun startCluster() {
        with(lifecycleScope) {
            beforeLifecycle()
            while (generation < maxGeneration) {
                lifecycle()
                super.generation++
            }
            afterLifecycle()
        }
    }

    private companion object {
        val BASE_BEFORE_LIFECYCLE: suspend SimpleClusterLifecycle<*, *>.() -> Unit = { fitnessAll() }
    }
}
