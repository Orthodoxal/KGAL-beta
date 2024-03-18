package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome

data class ChromosomeIntArray<F : Comparable<F>>(
    override var value: IntArray,
    override var fitness: F? = null,
    private val clone: (ChromosomeIntArray<F>.() -> ChromosomeIntArray<F>)? = null,
) : Chromosome<IntArray, F> {
    override fun compareTo(other: Chromosome<IntArray, F>): Int = compareValues(fitness, other.fitness)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChromosomeIntArray<*>

        if (!value.contentEquals(other.value)) return false
        return fitness == other.fitness
    }

    override fun hashCode(): Int {
        var result = value.contentHashCode()
        result = 31 * result + (fitness?.hashCode() ?: 0)
        return result
    }

    override fun clone(): Chromosome<IntArray, F> = clone?.let { it() } ?: copy(value = value.copyOf())
}
