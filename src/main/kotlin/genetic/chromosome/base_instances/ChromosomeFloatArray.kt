package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome

data class ChromosomeFloatArray<F : Comparable<F>>(
    override var value: FloatArray,
    override var fitness: F? = null,
    private val clone: (ChromosomeFloatArray<F>.() -> ChromosomeFloatArray<F>)? = null,
) : Chromosome<FloatArray, F> {
    override fun compareTo(other: Chromosome<FloatArray, F>): Int = compareValues(fitness, other.fitness)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChromosomeFloatArray<*>

        if (!value.contentEquals(other.value)) return false
        return fitness == other.fitness
    }

    override fun hashCode(): Int {
        var result = value.contentHashCode()
        result = 31 * result + (fitness?.hashCode() ?: 0)
        return result
    }

    override fun clone(): Chromosome<FloatArray, F> = clone?.let { it() } ?: copy(value = value.copyOf())
}
