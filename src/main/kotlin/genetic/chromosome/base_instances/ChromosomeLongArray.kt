package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome
import genetic.ga.core.builder.DEFAULT_POPULATION_NAME
import genetic.ga.core.builder.GABuilder
import genetic.ga.core.builder.population
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

        if (!value.contentEquals(other.value)) return false
        return fitness == other.fitness
    }

    override fun hashCode(): Int {
        var result = value.contentHashCode()
        result = 31 * result + (fitness?.hashCode() ?: 0)
        return result
    }

    override fun clone(): Chromosome<LongArray, F> = copy(value = value.copyOf())
}

fun <F : Comparable<F>> longs(
    size: Int,
    random: Random,
    from: Long? = null,
    until: Long? = null,
) = ChromosomeLongArray<F>(
    value = when {
        from != null && until != null -> LongArray(size) { random.nextLong(from, until) }
        from != null -> LongArray(size) { random.nextLong(from, Long.MAX_VALUE) }
        until != null -> LongArray(size) { random.nextLong(until) }
        else -> LongArray(size) { random.nextLong() }
    },
)

fun <F : Comparable<F>> GABuilder<LongArray, F, *>.longs(
    size: Int,
    from: Long? = null,
    until: Long? = null,
) = longs<F>(size, random, from, until)

fun <F : Comparable<F>> GABuilder<LongArray, F, *>.population(
    size: Int,
    chrSize: Int,
    from: Long? = null,
    until: Long? = null,
    name: String = DEFAULT_POPULATION_NAME,
) = population(size, name) { longs(chrSize, from, until) }
