package genetic.utils.clusters

import genetic.chromosome.Chromosome
import genetic.clusters.base.lifecycle.ClusterLifecycle
import genetic.clusters.base.population.Population
import genetic.utils.forEach
import genetic.utils.forEachIndexed

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


var <V, F> ClusterLifecycle<V, F>.popCurrentSize
    get() = population.currentSize
    set(value) {
        population.currentSize = value
    }

var <V, F> ClusterLifecycle<V, F>.popMaxSize
    get() = population.maxSize
    set(value) {
        population.maxSize = value
    }

var <V, F> ClusterLifecycle<V, F>.popFactory
    get() = population.factory
    set(value) {
        population.factory = value
    }
