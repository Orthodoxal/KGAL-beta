package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome

data class ChromosomeDoubleArray<F : Comparable<F>>(
    override var value: DoubleArray,
    override var fitness: F? = null,
    private val clone: (ChromosomeDoubleArray<F>.() -> ChromosomeDoubleArray<F>)? = null,
) : Chromosome<DoubleArray, F> {
    override fun compareTo(other: Chromosome<DoubleArray, F>): Int = compareValues(fitness, other.fitness)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChromosomeDoubleArray<*>

        if (!value.contentEquals(other.value)) return false
        return fitness == other.fitness
    }

    override fun hashCode(): Int {
        var result = value.contentHashCode()
        result = 31 * result + (fitness?.hashCode() ?: 0)
        return result
    }

    override fun clone(): Chromosome<DoubleArray, F> = clone?.let { it() } ?: copy(value = value.copyOf())
}
