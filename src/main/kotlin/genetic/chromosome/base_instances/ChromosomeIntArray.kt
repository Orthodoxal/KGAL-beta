package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome
import kotlin.random.Random

data class ChromosomeIntArray<F : Comparable<F>>(
    override var value: IntArray,
    override var fitness: F? = null,
) : Chromosome<IntArray, F> {
    override fun compareTo(other: Chromosome<IntArray, F>): Int = compareValues(fitness, other.fitness)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChromosomeIntArray<*>

        if (fitness != other.fitness) return false
        return value.contentEquals(other.value)
    }

    override fun hashCode(): Int {
        var result = value.contentHashCode()
        result = 31 * result + (fitness?.hashCode() ?: 0)
        return result
    }

    override fun clone(): Chromosome<IntArray, F> = copy(value = value.copyOf())
}

fun <F : Comparable<F>> Random.ints(
    size: Int,
    from: Int? = null,
    until: Int? = null,
) = ChromosomeIntArray<F>(
    value = when {
        from != null && until != null -> IntArray(size) { nextInt(from, until) }
        from != null -> IntArray(size) { nextInt(from, Int.MAX_VALUE) }
        until != null -> IntArray(size) { nextInt(until) }
        else -> IntArray(size) { nextInt() }
    },
)
