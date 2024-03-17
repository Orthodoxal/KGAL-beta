package genetic.clusters.simple_cluster

import genetic.chromosome.Chromosome
import genetic.clusters.AbstractCluster
import genetic.clusters.simple_cluster.builder.SimpleClusterBuilder
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycleInstance
import genetic.clusters.state.ClusterState
import genetic.ga.panmictic.lifecycle.utils.fitnessAll
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

internal class SimpleClusterInstance<V, F> : AbstractCluster<V, F>(), SimpleClusterBuilder<V, F> {
    private val lifecycleScope: SimpleClusterLifecycle<V, F> by lazy { SimpleClusterLifecycleInstance(builder = this) }
    private var lifecycle: (SimpleClusterLifecycle<V, F>.() -> Unit)? = null

    override var randomSeed: Int = 0
        set(value) {
            random = Random(value)
        }
    override lateinit var populationFactory: (index: Int, random: Random) -> Chromosome<V, F>
    override lateinit var fitnessFunction: (V) -> F
    override var dispatcher: CoroutineDispatcher? = null

    override fun SimpleClusterBuilder<V, F>.lifecycle(lifecycle: SimpleClusterLifecycle<V, F>.() -> Unit) {
        this@SimpleClusterInstance.lifecycle = lifecycle
    }

    override suspend fun start() {
        state = ClusterState.STARTED
        val dispatcher = dispatcher
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

    private fun startCluster() {
        lifecycle?.let { lifecycle ->
            with(lifecycleScope) {
                generation++
                fitnessAll()
                while (generation <= maxGeneration) {
                    lifecycle()
                    fitnessAll()
                    generation++
                }
            }
        }
    }
}
