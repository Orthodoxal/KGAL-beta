package genetic.clusters.base.population

import genetic.chromosome.Chromosome
import kotlin.random.Random

class Population<V, F>(
    var population: Array<Chromosome<V, F>>,
    var currentSize: Int,
    var maxSize: Int,
    var factory: (index: Int, random: Random) -> Chromosome<V, F>,
) : Iterable<Chromosome<V, F>> {
    override fun iterator(): Iterator<Chromosome<V, F>> = population.iterator()
}
