package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome
import genetic.ga.core.builder.DEFAULT_POPULATION_NAME
import genetic.ga.core.builder.GABuilder
import genetic.ga.core.builder.population
import kotlin.random.Random

data class ChromosomeString<F : Comparable<F>>(
    override var value: String,
    override var fitness: F? = null,
) : Chromosome<String, F> {
    override fun compareTo(other: Chromosome<String, F>): Int = compareValues(fitness, other.fitness)

    override fun clone(): Chromosome<String, F> = copy(value = value)
}

fun <F : Comparable<F>> string(size: Int, random: Random, allowedChars: List<Char> = defaultAllowedChars) =
    ChromosomeString<F>(value = String(chars(size, random, allowedChars)))

fun <F : Comparable<F>> GABuilder<String, F, *>.string(size: Int, allowedChars: List<Char> = defaultAllowedChars) =
    string<F>(size, random, allowedChars)

fun <F : Comparable<F>> GABuilder<String, F, *>.population(
    size: Int,
    chrSize: Int,
    allowedChars: List<Char> = defaultAllowedChars,
    name: String = DEFAULT_POPULATION_NAME,
) = population(size, name) { string(chrSize, allowedChars) }
