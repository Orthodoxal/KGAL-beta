package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome
import kotlin.random.Random

data class ChromosomeString<F : Comparable<F>>(
    override var value: String,
    override var fitness: F? = null,
) : Chromosome<String, F> {
    override fun compareTo(other: Chromosome<String, F>): Int = compareValues(fitness, other.fitness)

    override fun clone(): Chromosome<String, F> = copy(value = value)
}

fun <F : Comparable<F>> Random.string(size: Int, allowedChars: List<Char> = defaultAllowedChars) =
    ChromosomeString<F>(value = String(chars(size, this, allowedChars)))
