package genetic.utils.clusters

import genetic.chromosome.Chromosome
import genetic.clusters.ClusterBuilder
import kotlin.random.Random

fun <V, F> ClusterBuilder<V, F>.populationFactory(
    size: Int,
    factory: (index: Int, random: Random) -> Chromosome<V, F>,
) {
    populationSize = size
    populationFactory = factory
    population = Array(size) { factory(it, random) }
}
