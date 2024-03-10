package genetic.clusters.cluster.builder

import genetic.chromosome.Chromosome
import genetic.chromosome.ChromosomeComparator
import genetic.clusters.Cluster
import genetic.clusters.cluster.ClusterInstance
import genetic.clusters.cluster.lifecycle.ClusterLifecycle
import kotlin.random.Random

interface ClusterBuilder<V, F> {
    var clusterId: String
    var population: Array<Chromosome<V, F>>
    var populationSize: Int
    var populationFactory: (index: Int, random: Random) -> Chromosome<V, F>
    var randomSeed: Int
    var fitnessFunction: (Chromosome<V, F>) -> Unit
    var comparator: ChromosomeComparator<V, F>
    var clone: Chromosome<V, F>.() -> Chromosome<V, F>
    var maxGeneration: Int

    fun ClusterBuilder<V, F>.lifecycle(lifecycle: ClusterLifecycle<V, F>.() -> Unit)
}

internal inline fun <V, F> cluster(builder: ClusterBuilder<V, F>.() -> Unit): Cluster<V, F> =
    ClusterInstance<V, F>().apply(builder)
