package genetic.ga.core.population

import genetic.chromosome.Chromosome
import genetic.utils.forEach
import genetic.utils.forEachIndexed

class Population<V, F>(
    val name: String,
    var population: Array<Chromosome<V, F>>,
    var currentSize: Int,
    var maxSize: Int,
    var factory: (index: Int) -> Chromosome<V, F>,
) : Iterable<Chromosome<V, F>> {
    override fun iterator(): Iterator<Chromosome<V, F>> = population.iterator()
}

fun <V, F> Population<V, F>.get() = population

fun <V, F> Population<V, F>.set(population: Array<Chromosome<V, F>>) =
    apply { this.population = population }

fun <V, F> Population<V, F>.cloneOf(index: Int) = population[index].clone()

fun <V, F> Population<V, F>.copyOf() = population.copyOf()
fun <V, F> Population<V, F>.copyOfRange(fromIndex: Int = 0, toIndex: Int = currentSize) =
    population.copyOfRange(0, currentSize)

fun <V, F> Population<V, F>.max() = population.max()
fun <V, F> Population<V, F>.min() = population.min()

inline fun <V, F> Population<V, F>.forEach(
    start: Int = 0,
    end: Int = currentSize,
    action: (chromosome: Chromosome<V, F>) -> Unit,
) = population.forEach(start, end, action)

inline fun <V, F> Population<V, F>.forEachIndexed(
    start: Int = 0,
    end: Int = currentSize,
    action: (index: Int, chromosome: Chromosome<V, F>) -> Unit,
) = population.forEachIndexed(start, end, action)

operator fun <V, F> Population<V, F>.get(index: Int) = population[index]

operator fun <V, F> Population<V, F>.set(index: Int, chromosome: Chromosome<V, F>) =
    apply { population[index] = chromosome }

val <V, F> Population<V, F>.lastIndex get() = currentSize - 1
val <V, F> Population<V, F>.indices get() = population.indices


internal fun <V, F> getDistributedFullPopulation(
    populations: List<Population<V, F>>,
): Population<V, F> {
    val currentSizes = populations.map { it.currentSize }
    val currentSize = currentSizes.sum()
    var cursor = 0
    var realIndex = 0
    return Population(
        name = "Distributed population of: ${populations.joinToString { it.name }}",
        population = Array(currentSize) { index ->
            if (currentSizes[cursor] == index) {
                realIndex = 0
                cursor++
            }
            populations[cursor][realIndex]
        },
        currentSize = currentSize,
        maxSize = populations.sumOf { it.maxSize },
        factory = populations.first().factory,
    )
}
