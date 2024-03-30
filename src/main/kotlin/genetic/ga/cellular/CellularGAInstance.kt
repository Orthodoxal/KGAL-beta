package genetic.ga.cellular

import genetic.chromosome.Chromosome
import genetic.clusters.Cluster
import genetic.ga.AbstractGA
import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.cellular.lifecycle.CellularGALifecycle
import genetic.ga.cellular.lifecycle.CellularGALifecycleInstance
import genetic.ga.cellular.neighbourhood.CellularNeighbourhood
import genetic.ga.cellular.type.CellularType
import genetic.ga.state.GAState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

internal class CellularGAInstance<V, F> : AbstractGA<V, F>(), CellularGABuilder<V, F> {
    private val lifecycleScope: CellularGALifecycle<V, F> by lazy { CellularGALifecycleInstance(builder = this) }
    private var beforeLifecycle: suspend CellularGALifecycle<V, F>.() -> Unit = { }
    private var lifecycle: suspend CellularGALifecycle<V, F>.() -> Unit = BASE_LIFECYCLE
    private var afterLifecycle: suspend CellularGALifecycle<V, F>.() -> Unit = { }
    private var random: Random = Random
    private var neighbours: Array<Array<IntArray>>? = null

    override suspend fun start(coroutineContext: CoroutineContext) {
        state = GAState.STARTED
        coroutineScope {
            gaJob = launch(coroutineContext) { startGA() }
        }
        state = GAState.FINISHED
    }

    private suspend fun startGA() {
        if (neighbours == null) {
            /*neighbours = Array(clusters.size) { clusterIndex ->
                val cluster = clusters[clusterIndex]
                Array(cluster.populationSize) { chromosomeIndex ->

                }
            }*/
        }
        with(lifecycleScope) {
            beforeLifecycle()
            while (iteration < maxIteration) {
                lifecycle()
                super.iteration++
            }
            afterLifecycle()
        }
    }

    override fun addCluster(cluster: Cluster<V, F>) {
        if (clusters.isNotEmpty()) throw Exception("Panmictic clusters maximum count = 1")
        clusters.add(cluster)
    }

    override fun CellularGABuilder<V, F>.lifecycle(
        before: (suspend CellularGALifecycle<V, F>.() -> Unit)?,
        after: (suspend CellularGALifecycle<V, F>.() -> Unit)?,
        lifecycle: suspend CellularGALifecycle<V, F>.() -> Unit,
    ) {
        before?.let { beforeLifecycle = before }
        after?.let { afterLifecycle = after }
        this@CellularGAInstance.lifecycle = lifecycle
    }

    override var randomSeed: Int = 0
        set(value) {
            random = Random(value)
        }
    override lateinit var populationFactory: (index: Int, random: Random) -> Chromosome<V, F>
    override lateinit var fitnessFunction: (Chromosome<V, F>) -> Unit
    override var neighbourhood: CellularNeighbourhood = CellularNeighbourhood.VonNeumann(radius = 1)
    override var cellularType: CellularType = CellularType.Synchronous

    companion object {
        private val BASE_LIFECYCLE: suspend CellularGALifecycle<*, *>.() -> Unit = {
            clusters.forEach { it.start() }
            coroutineContext.job.children.forEach { it.join() }
        }
    }
}
