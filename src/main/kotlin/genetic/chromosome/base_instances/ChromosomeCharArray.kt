package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome

data class ChromosomeCharArray<F : Comparable<F>>(
    override var value: CharArray,
    override var fitness: F? = null,
    private val clone: (ChromosomeCharArray<F>.() -> ChromosomeCharArray<F>)? = null,
) : Chromosome<CharArray, F> {
    override fun compareTo(other: Chromosome<CharArray, F>): Int = compareValues(fitness, other.fitness)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChromosomeCharArray<*>

        if (!value.contentEquals(other.value)) return false
        return fitness == other.fitness
    }

    override fun hashCode(): Int {
        var result = value.contentHashCode()
        result = 31 * result + (fitness?.hashCode() ?: 0)
        return result
    }

    override fun clone(): Chromosome<CharArray, F> = clone?.let { it() } ?: copy(value = value.copyOf())
}
