package genetic.ga.panmictic

import genetic.chromosome.Chromosome
import genetic.chromosome.ChromosomeComparator
import genetic.clusters.Cluster
import genetic.ga.AbstractGA
import genetic.ga.panmictic.builder.PanmicticGABuilder
import genetic.ga.panmictic.lifecycle.PanmicticGALifecycle
import genetic.ga.panmictic.lifecycle.PanmicticGALifecycleInstance
import genetic.ga.state.GAState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

internal class PanmicticGAInstance<V, F> : AbstractGA<V, F>(), PanmicticGABuilder<V, F> {
    private val lifecycleScope: PanmicticGALifecycle<V, F> by lazy { PanmicticGALifecycleInstance(builder = this) }
    private var lifecycle: suspend PanmicticGALifecycle<V, F>.() -> Unit = BASE_LIFECYCLE
    private var random: Random = Random

    override suspend fun start(coroutineContext: CoroutineContext) {
        state = GAState.STARTED
        coroutineScope {
            gaJob = launch(coroutineContext) {
                with(lifecycleScope) {
                    iteration++
                    while (iteration <= maxIteration) {
                        iteration++
                        lifecycle()
                    }
                }
            }
        }
        state = GAState.FINISHED
    }

    override fun addCluster(cluster: Cluster<V, F>) {
        if (clusters.isNotEmpty()) throw Exception("Panmictic clusters maximum count = 1")
        clusters.add(cluster)
    }

    override fun PanmicticGABuilder<V, F>.lifecycle(lifecycle: suspend PanmicticGALifecycle<V, F>.() -> Unit) {
        this@PanmicticGAInstance.lifecycle = lifecycle
    }

    override var randomSeed: Int = 0
        set(value) {
            random = Random(value)
        }
    override lateinit var populationFactory: (index: Int, random: Random) -> Chromosome<V, F>
    override lateinit var fitnessFunction: (Chromosome<V, F>) -> Unit
    override lateinit var comparator: ChromosomeComparator<V, F>
    override lateinit var clone: Chromosome<V, F>.() -> Chromosome<V, F>

    companion object {
        private val BASE_LIFECYCLE: suspend PanmicticGALifecycle<*, *>.() -> Unit = {
            clusters.forEach { it.start() }
            coroutineContext.job.children.forEach { it.join() }
        }
    }
}
