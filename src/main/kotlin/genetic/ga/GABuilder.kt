package genetic.ga

import genetic.chromosome.Chromosome
import genetic.chromosome.ChromosomeComparator
import genetic.clusters.Cluster
import kotlin.random.Random

interface GABuilder<V, F> {
    var iteration: Int
    var maxIteration: Int
    val clusters: List<Cluster<V, F>>
    var populationFactory: (index: Int, random: Random) -> Chromosome<V, F>
    var randomSeed: Int
    var fitnessFunction: (Chromosome<V, F>) -> Unit
    var comparator: ChromosomeComparator<V, F>
    var clone: Chromosome<V, F>.() -> Chromosome<V, F>

    fun addCluster(cluster: Cluster<V, F>)
    operator fun Cluster<V, F>.unaryPlus() = addCluster(this)
}
