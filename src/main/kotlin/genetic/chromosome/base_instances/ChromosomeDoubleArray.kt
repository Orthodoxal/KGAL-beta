package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome
import genetic.ga.core.builder.DEFAULT_POPULATION_NAME
import genetic.ga.core.builder.GABuilder
import genetic.ga.core.builder.population
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

        if (!value.contentEquals(other.value)) return false
        return fitness == other.fitness
    }

    override fun hashCode(): Int {
        var result = value.contentHashCode()
        result = 31 * result + (fitness?.hashCode() ?: 0)
        return result
    }

    override fun clone(): Chromosome<DoubleArray, F> = copy(value = value.copyOf())
}

fun <F : Comparable<F>> doubles(
    size: Int,
    random: Random,
    from: Double? = null,
    until: Double? = null,
) = ChromosomeDoubleArray<F>(
    value = when {
        from != null && until != null -> DoubleArray(size) { random.nextDouble(from, until) }
        from != null -> DoubleArray(size) { random.nextDouble(from, Double.MAX_VALUE) }
        until != null -> DoubleArray(size) { random.nextDouble(until) }
        else -> DoubleArray(size) { random.nextDouble() }
    },
)

fun <F : Comparable<F>> GABuilder<DoubleArray, F, *>.doubles(
    size: Int,
    from: Double? = null,
    until: Double? = null,
) = doubles<F>(size, random, from, until)

fun <F : Comparable<F>> GABuilder<DoubleArray, F, *>.population(
    size: Int,
    chrSize: Int,
    from: Double? = null,
    until: Double? = null,
    name: String = DEFAULT_POPULATION_NAME,
) = population(size, name) { doubles(chrSize, from, until) }
