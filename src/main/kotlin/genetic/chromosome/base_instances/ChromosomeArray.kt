package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome

data class ChromosomeArray<T, F : Comparable<F>>(
    override var value: Array<T>,
    override var fitness: F? = null,
    private val clone: (ChromosomeArray<T, F>.() -> ChromosomeArray<T, F>)? = null,
) : Chromosome<Array<T>, F> {
    override fun compareTo(other: Chromosome<Array<T>, F>): Int = compareValues(fitness, other.fitness)

    override fun clone(): Chromosome<Array<T>, F> = clone?.let { it() } ?: copy(value = value.copyOf())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChromosomeArray<*, *>

        if (!value.contentEquals(other.value)) return false
        if (fitness != other.fitness) return false
        return clone == other.clone
    }

    override fun hashCode(): Int {
        var result = value.contentHashCode()
        result = 31 * result + (fitness?.hashCode() ?: 0)
        result = 31 * result + (clone?.hashCode() ?: 0)
        return result
    }
}
