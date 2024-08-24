package genetic.utils.clusters

import genetic.chromosome.Chromosome
import genetic.clusters.base.population.Population
import genetic.clusters.base.builder.ClusterBuilder
import kotlin.random.Random

fun <V, F> ClusterBuilder<V, F, *>.populationFactory(
    size: Int,
    factory: (index: Int, random: Random) -> Chromosome<V, F>,
) {
    population = Population(
        population = Array(size) { factory(it, random) },
        currentSize = size,
        maxSize = size,
        factory = factory,
    )
}
