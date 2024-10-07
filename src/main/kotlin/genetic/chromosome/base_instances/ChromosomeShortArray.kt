package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome

data class ChromosomeShortArray<F : Comparable<F>>(
    override var value: ShortArray,
    override var fitness: F? = null,
    private val clone: (ChromosomeShortArray<F>.() -> ChromosomeShortArray<F>)? = null,
) : Chromosome<ShortArray, F> {
    override fun compareTo(other: Chromosome<ShortArray, F>): Int = compareValues(fitness, other.fitness)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChromosomeShortArray<*>

        if (fitness != other.fitness) return false
        return value.contentEquals(other.value)
    }

    override fun hashCode(): Int {
        var result = value.contentHashCode()
        result = 31 * result + (fitness?.hashCode() ?: 0)
        return result
    }

    override fun clone(): Chromosome<ShortArray, F> = clone?.let { it() } ?: copy(value = value.copyOf())
}
