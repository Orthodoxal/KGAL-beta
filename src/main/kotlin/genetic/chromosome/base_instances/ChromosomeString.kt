package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome

data class ChromosomeString<F : Comparable<F>>(
    override var value: String,
    override var fitness: F? = null,
    private val clone: (ChromosomeString<F>.() -> ChromosomeString<F>)? = null,
) : Chromosome<String, F> {
    override fun compareTo(other: Chromosome<String, F>): Int = compareValues(fitness, other.fitness)

    override fun clone(): Chromosome<String, F> = clone?.let { it() } ?: copy(value = value)
}
