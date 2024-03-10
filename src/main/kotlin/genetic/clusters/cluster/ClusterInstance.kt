package genetic.clusters.cluster

import genetic.chromosome.Chromosome
import genetic.chromosome.ChromosomeComparator
import genetic.clusters.AbstractCluster
import genetic.clusters.cluster.builder.ClusterBuilder
import genetic.clusters.cluster.lifecycle.ClusterLifecycle
import genetic.clusters.cluster.lifecycle.ClusterLifecycleInstance
import genetic.clusters.state.ClusterState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random

internal class ClusterInstance<V, F> : AbstractCluster<V, F>(), ClusterBuilder<V, F> {
    private val lifecycleScope: ClusterLifecycle<V, F> by lazy { ClusterLifecycleInstance(builder = this) }
    private lateinit var lifecycle: ClusterLifecycle<V, F>.() -> Unit
    private var random: Random = Random

    override var randomSeed: Int = 0
        set(value) {
            random = Random(value)
        }
    override lateinit var populationFactory: (index: Int, random: Random) -> Chromosome<V, F>
    override lateinit var fitnessFunction: (Chromosome<V, F>) -> Unit
    override lateinit var comparator: ChromosomeComparator<V, F>
    override lateinit var clone: Chromosome<V, F>.() -> Chromosome<V, F>

    override fun ClusterBuilder<V, F>.lifecycle(lifecycle: ClusterLifecycle<V, F>.() -> Unit) {
        this@ClusterInstance.lifecycle = lifecycle
    }

    override suspend fun start() {
        state = ClusterState.STARTED
        coroutineScope {
            clusterJob = launch {
                with(lifecycleScope) {
                    generation++
                    while (generation < maxGeneration) {
                        generation++
                        lifecycle()
                    }
                }
            }
        }
        state = ClusterState.FINISHED
    }
}
