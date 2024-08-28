package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome
import genetic.ga.core.builder.DEFAULT_POPULATION_NAME
import genetic.ga.core.builder.GABuilder
import genetic.ga.core.builder.population
import kotlin.random.Random

data class ChromosomeCharArray<F : Comparable<F>>(
    override var value: CharArray,
    override var fitness: F? = null,
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

    override fun clone(): Chromosome<CharArray, F> = copy(value = value.copyOf())
}

internal val defaultAllowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
internal fun chars(size: Int, random: Random, allowedChars: List<Char> = defaultAllowedChars) =
    CharArray(size) { allowedChars.random(random) }

fun <F : Comparable<F>> chars(size: Int, random: Random, allowedChars: List<Char> = defaultAllowedChars) =
    ChromosomeCharArray<F>(value = chars(size, random, allowedChars))

fun <F : Comparable<F>> GABuilder<CharArray, F, *>.chars(size: Int, allowedChars: List<Char> = defaultAllowedChars) =
    chars<F>(size, random, allowedChars)

fun <F : Comparable<F>> GABuilder<CharArray, F, *>.population(
    size: Int,
    chrSize: Int,
    allowedChars: List<Char> = defaultAllowedChars,
    name: String = DEFAULT_POPULATION_NAME,
) = population(size, name) { chars(chrSize, allowedChars) }
