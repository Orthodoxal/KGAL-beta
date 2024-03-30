package genetic.ga

import genetic.chromosome.Chromosome
import genetic.clusters.Cluster
import kotlin.random.Random

interface GABuilder<V, F> {
    val iteration: Int
    var maxIteration: Int
    val clusters: List<Cluster<V, F>>
    var populationFactory: (index: Int, random: Random) -> Chromosome<V, F>
    var randomSeed: Int
    var fitnessFunction: (Chromosome<V, F>) -> Unit

    fun addCluster(cluster: Cluster<V, F>)
    operator fun Cluster<V, F>.unaryPlus() = addCluster(this)
}
