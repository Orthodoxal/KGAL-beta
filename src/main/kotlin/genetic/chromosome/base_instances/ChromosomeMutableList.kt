package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome

data class ChromosomeMutableList<T, F : Comparable<F>>(
    override var value: MutableList<T>,
    override var fitness: F? = null,
    private val clone: (ChromosomeMutableList<T, F>.() -> ChromosomeMutableList<T, F>)? = null,
) : Chromosome<MutableList<T>, F> {
    override fun compareTo(other: Chromosome<MutableList<T>, F>): Int = compareValues(fitness, other.fitness)

    override fun clone(): Chromosome<MutableList<T>, F> = clone?.let { it() } ?: copy(value = value.toMutableList())
}
