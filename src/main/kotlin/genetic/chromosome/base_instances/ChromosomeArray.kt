package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome
import genetic.ga.core.builder.GABuilder
import genetic.ga.core.builder.population
import kotlin.random.Random

data class ChromosomeArray<T, F : Comparable<F>>(
    override var value: Array<T>,
    override var fitness: F? = null,
    private val clone: (ChromosomeArray<T, F>.() -> ChromosomeArray<T, F>)? = null,
) : Chromosome<Array<T>, F> {
    override fun compareTo(other: Chromosome<Array<T>, F>): Int = compareValues(fitness, other.fitness)

    override fun clone(): Chromosome<Array<T>, F> = clone?.let { it() } ?: copy(value = value.copyOf())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChromosomeArray<*, *>

        if (!value.contentEquals(other.value)) return false
        return fitness == other.fitness
    }

    override fun hashCode(): Int {
        var result = value.contentHashCode()
        result = 31 * result + (fitness?.hashCode() ?: 0)
        return result
    }
}

inline fun <reified T, F : Comparable<F>> array(
    size: Int,
    random: Random,
    factory: (index: Int, random: Random) -> T,
    noinline clone: (ChromosomeArray<T, F>.() -> ChromosomeArray<T, F>)?,
) = ChromosomeArray(Array(size) { factory(it, random) }, clone = clone)

inline fun <reified T, F : Comparable<F>> GABuilder<Array<T>, F, *>.array(
    size: Int,
    factory: (index: Int, random: Random) -> T,
    noinline clone: (ChromosomeArray<T, F>.() -> ChromosomeArray<T, F>)? = null,
) = array<T, F>(size, random, factory, clone)

inline fun <reified T, F : Comparable<F>> GABuilder<Array<T>, F, *>.population(
    size: Int,
    chrSize: Int,
    crossinline factory: (index: Int, random: Random) -> T,
    name: String? = null,
) = name?.let { population(size, name) { array(chrSize, factory) } }
    ?: population(size) { array(chrSize, factory) }
