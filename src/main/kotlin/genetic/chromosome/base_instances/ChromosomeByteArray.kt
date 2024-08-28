package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome
import genetic.ga.core.builder.DEFAULT_POPULATION_NAME
import genetic.ga.core.builder.GABuilder
import genetic.ga.core.builder.population
import kotlin.random.Random

data class ChromosomeByteArray<F : Comparable<F>>(
    override var value: ByteArray,
    override var fitness: F? = null,
) : Chromosome<ByteArray, F> {
    override fun compareTo(other: Chromosome<ByteArray, F>): Int = compareValues(fitness, other.fitness)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChromosomeByteArray<*>

        if (!value.contentEquals(other.value)) return false
        return fitness == other.fitness
    }

    override fun hashCode(): Int {
        var result = value.contentHashCode()
        result = 31 * result + (fitness?.hashCode() ?: 0)
        return result
    }

    override fun clone(): Chromosome<ByteArray, F> = copy(value = value.copyOf())
}

fun <F : Comparable<F>> bytes(size: Int, random: Random) =
    ChromosomeByteArray<F>(value = random.nextBytes(size))

fun <F : Comparable<F>> GABuilder<ByteArray, F, *>.bytes(size: Int) =
    bytes<F>(size, random)

fun <F : Comparable<F>> GABuilder<ByteArray, F, *>.population(
    size: Int,
    chrSize: Int,
    name: String = DEFAULT_POPULATION_NAME,
) = population(size, name) { bytes(chrSize) }
