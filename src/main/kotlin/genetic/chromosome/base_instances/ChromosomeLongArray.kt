package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome

data class ChromosomeLongArray<F : Comparable<F>>(
    override var value: LongArray,
    override var fitness: F? = null,
    private val clone: (ChromosomeLongArray<F>.() -> ChromosomeLongArray<F>)? = null,
) : Chromosome<LongArray, F> {
    override fun compareTo(other: Chromosome<LongArray, F>): Int = compareValues(fitness, other.fitness)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChromosomeLongArray<*>

        if (!value.contentEquals(other.value)) return false
        return fitness == other.fitness
    }

    override fun hashCode(): Int {
        var result = value.contentHashCode()
        result = 31 * result + (fitness?.hashCode() ?: 0)
        return result
    }

    override fun clone(): Chromosome<LongArray, F> = clone?.let { it() } ?: copy(value = value.copyOf())
}
