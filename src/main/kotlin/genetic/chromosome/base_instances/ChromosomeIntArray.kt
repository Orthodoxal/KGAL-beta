package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome
import genetic.ga.core.builder.DEFAULT_POPULATION_NAME
import genetic.ga.core.builder.GABuilder
import genetic.ga.core.builder.population
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

        if (!value.contentEquals(other.value)) return false
        return fitness == other.fitness
    }

    override fun hashCode(): Int {
        var result = value.contentHashCode()
        result = 31 * result + (fitness?.hashCode() ?: 0)
        return result
    }

    override fun clone(): Chromosome<IntArray, F> = copy(value = value.copyOf())
}

fun <F : Comparable<F>> ints(
    size: Int,
    random: Random,
    from: Int? = null,
    until: Int? = null,
) = ChromosomeIntArray<F>(
    value = when {
        from != null && until != null -> IntArray(size) { random.nextInt(from, until) }
        from != null -> IntArray(size) { random.nextInt(from, Int.MAX_VALUE) }
        until != null -> IntArray(size) { random.nextInt(until) }
        else -> IntArray(size) { random.nextInt() }
    },
)

fun <F : Comparable<F>> GABuilder<IntArray, F, *>.ints(
    size: Int,
    from: Int? = null,
    until: Int? = null,
) = ints<F>(size, random, from, until)

fun <F : Comparable<F>> GABuilder<IntArray, F, *>.population(
    size: Int,
    chrSize: Int,
    from: Int? = null,
    until: Int? = null,
    name: String = DEFAULT_POPULATION_NAME,
) = population(size, name) { ints(chrSize, from, until) }
