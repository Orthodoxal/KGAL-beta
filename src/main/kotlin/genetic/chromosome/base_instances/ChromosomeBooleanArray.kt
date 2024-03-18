package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome

data class ChromosomeBooleanArray<F : Comparable<F>>(
    override var value: BooleanArray,
    override var fitness: F? = null,
    private val clone: (ChromosomeBooleanArray<F>.() -> ChromosomeBooleanArray<F>)? = null,
) : Chromosome<BooleanArray, F> {
    override fun compareTo(other: Chromosome<BooleanArray, F>): Int = compareValues(fitness, other.fitness)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChromosomeBooleanArray<*>

        if (!value.contentEquals(other.value)) return false
        return fitness == other.fitness
    }

    override fun hashCode(): Int {
        var result = value.contentHashCode()
        result = 31 * result + (fitness?.hashCode() ?: 0)
        return result
    }

    override fun clone(): Chromosome<BooleanArray, F> = clone?.let { it() } ?: copy(value = value.copyOf())
}
