package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome
import kotlin.random.Random

data class ChromosomeLongArray<F : Comparable<F>>(
    override var value: LongArray,
    override var fitness: F? = null,
) : Chromosome<LongArray, F> {
    override fun compareTo(other: Chromosome<LongArray, F>): Int = compareValues(fitness, other.fitness)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChromosomeLongArray<*>

        if (fitness != other.fitness) return false
        return value.contentEquals(other.value)
    }

    override fun hashCode(): Int {
        var result = value.contentHashCode()
        result = 31 * result + (fitness?.hashCode() ?: 0)
        return result
    }

    override fun clone(): Chromosome<LongArray, F> = copy(value = value.copyOf())
}

fun <F : Comparable<F>> Random.longs(
    size: Int,
    from: Long? = null,
    until: Long? = null,
) = ChromosomeLongArray<F>(
    value = when {
        from != null && until != null -> LongArray(size) { nextLong(from, until) }
        from != null -> LongArray(size) { nextLong(from, Long.MAX_VALUE) }
        until != null -> LongArray(size) { nextLong(until) }
        else -> LongArray(size) { nextLong() }
    },
)
