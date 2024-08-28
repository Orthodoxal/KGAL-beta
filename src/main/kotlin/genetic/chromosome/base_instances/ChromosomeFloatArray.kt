package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome
import genetic.ga.core.builder.DEFAULT_POPULATION_NAME
import genetic.ga.core.builder.GABuilder
import genetic.ga.core.builder.population
import kotlin.random.Random

data class ChromosomeFloatArray<F : Comparable<F>>(
    override var value: FloatArray,
    override var fitness: F? = null,
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

    override fun clone(): Chromosome<FloatArray, F> = copy(value = value.copyOf())
}

fun <F : Comparable<F>> floats(size: Int, random: Random) =
    ChromosomeFloatArray<F>(value = FloatArray(size) { random.nextFloat() })

fun <F : Comparable<F>> GABuilder<FloatArray, F, *>.floats(size: Int) = floats<F>(size, random)

fun <F : Comparable<F>> GABuilder<FloatArray, F, *>.population(
    size: Int,
    chrSize: Int,
    name: String = DEFAULT_POPULATION_NAME,
) = population(size, name) { floats(chrSize) }
