package genetic.ga.core.population

import genetic.chromosome.Chromosome
import genetic.utils.forEach
import genetic.utils.forEachIndexed
import kotlin.random.Random

typealias PopulationFactory<V, F> = Random.() -> Chromosome<V, F>

interface Population<V, F> : Iterable<Chromosome<V, F>> {
    val name: String
    var size: Int
    val maxSize: Int
    var factory: PopulationFactory<V, F>
    var population: Array<Chromosome<V, F>>

    override fun iterator(): Iterator<Chromosome<V, F>> = population.iterator()

    fun clone(newName: String = this.name): Population<V, F>

    companion object {
        const val DEFAULT_POPULATION_NAME = "POPULATION 1"
    }
}

inline val Population<*, *>.isEmpty: Boolean get() = population.isEmpty()

fun <V, F> Population<V, F>.get() = population

fun <V, F> Population<V, F>.set(population: Array<Chromosome<V, F>>) =
    apply { this.population = population }

fun <V, F> Population<V, F>.cloneOf(index: Int) = population[index].clone()

fun <V, F> Population<V, F>.copyOf() = population.copyOf()
fun <V, F> Population<V, F>.copyOfRange(fromIndex: Int = 0, toIndex: Int = size) =
    population.copyOfRange(0, size)

val <V, F> Population<V, F>.best get() = fold { acc, candidate -> acc < candidate }
val <V, F> Population<V, F>.worst get() = fold { acc, candidate -> acc > candidate }

inline fun <V, F> Population<V, F>.forEach(
    start: Int,
    end: Int,
    action: (chromosome: Chromosome<V, F>) -> Unit,
) = population.forEach(start, end, action)

inline fun <V, F> Population<V, F>.forEachIndexed(
    start: Int = 0,
    end: Int = size,
    action: (index: Int, chromosome: Chromosome<V, F>) -> Unit,
) = population.forEachIndexed(start, end, action)

private inline fun <V, F> Population<V, F>.fold(
    operation: (acc: Chromosome<V, F>, candidate: Chromosome<V, F>) -> Boolean,
): Chromosome<V, F> {
    var accumulator = population.first()
    forEach(1, size) {
        if (operation(accumulator, it)) {
            accumulator = it
        }
    }
    return accumulator
}

inline fun <V, F, R> Population<V, F>.fold(initial: R, operation: (acc: R, Chromosome<V, F>) -> R): R {
    var accumulator = initial
    this.forEach(0, size) { accumulator = operation(accumulator, it) }
    return accumulator
}

inline fun <V, F> Population<V, F>.sort() = population.sort()
inline fun <V, F> Population<V, F>.sort(start: Int, end: Int) = population.sort(start, end)

operator fun <V, F> Population<V, F>.get(index: Int) = population[index]

operator fun <V, F> Population<V, F>.set(index: Int, chromosome: Chromosome<V, F>) =
    apply { population[index] = chromosome }

val <V, F> Population<V, F>.lastIndex get() = size - 1
val <V, F> Population<V, F>.indices get() = population.indices
