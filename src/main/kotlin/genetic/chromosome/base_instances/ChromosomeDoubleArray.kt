package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome
import kotlin.random.Random

data class ChromosomeDoubleArray<F : Comparable<F>>(
    override var value: DoubleArray,
    override var fitness: F? = null,
) : Chromosome<DoubleArray, F> {
    override fun compareTo(other: Chromosome<DoubleArray, F>): Int = compareValues(fitness, other.fitness)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChromosomeDoubleArray<*>

        if (fitness != other.fitness) return false
        return value.contentEquals(other.value)
    }

    override fun hashCode(): Int {
        var result = value.contentHashCode()
        result = 31 * result + (fitness?.hashCode() ?: 0)
        return result
    }

    override fun clone(): Chromosome<DoubleArray, F> = copy(value = value.copyOf())
}

fun <F : Comparable<F>> Random.doubles(
    size: Int,
    from: Double? = null,
    until: Double? = null,
) = ChromosomeDoubleArray<F>(
    value = when {
        from != null && until != null -> DoubleArray(size) { nextDouble(from, until) }
        from != null -> DoubleArray(size) { nextDouble(from, Double.MAX_VALUE) }
        until != null -> DoubleArray(size) { nextDouble(until) }
        else -> DoubleArray(size) { nextDouble() }
    },
)
