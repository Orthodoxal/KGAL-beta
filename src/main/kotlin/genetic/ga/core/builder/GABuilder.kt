package genetic.ga.core.builder

import genetic.chromosome.Chromosome
import genetic.ga.core.lifecycle.GALifecycle
import genetic.ga.core.population.Population
import genetic.stat.config.StatisticsConfig
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.random.Random

internal const val DEFAULT_POPULATION_NAME = "POPULATION 1"

interface GABuilder<V, F, L : GALifecycle<V, F>> {
    val random: Random

    var population: Population<V, F>
    var maxIteration: Int
    var fitnessFunction: ((V) -> F)?

    var mainDispatcher: CoroutineDispatcher?
    var extraDispatchers: Array<CoroutineDispatcher>?

    fun GABuilder<V, F, L>.before(useDefault: Boolean = true, beforeEvolution: suspend L.() -> Unit)
    fun GABuilder<V, F, L>.evolve(evolution: suspend L.() -> Unit)
    fun GABuilder<V, F, L>.after(useDefault: Boolean = true, afterEvolution: suspend L.() -> Unit)

    fun StatisticsConfig.applyToGA()
}

val GABuilder<*, *, *>.name get() = population.name
val GABuilder<*, *, *>.currentSize get() = population.currentSize
val GABuilder<*, *, *>.maxSize get() = population.maxSize
val GABuilder<*, *, *>.factory get() = population.factory

private fun getDefaultName(index: Int) = "POPULATION $index"

fun <V, F> GABuilder<V, F, *>.population(
    size: Int,
    name: String = DEFAULT_POPULATION_NAME,
    factory: (index: Int) -> Chromosome<V, F>,
) {
    population = Population(
        name = name,
        population = Array(size) { factory(it) },
        currentSize = size,
        maxSize = size,
        factory = factory,
    )
}

fun <V, F> GABuilder<V, F, *>.population(
    population: Array<Chromosome<V, F>>,
    currentSize: Int? = null,
    name: String = DEFAULT_POPULATION_NAME,
    factory: (index: Int) -> Chromosome<V, F> = { throw Exception("Factory of population not initialized") },
) {
    this.population = Population(
        name = name,
        population = population,
        currentSize = currentSize ?: population.size,
        maxSize = population.size,
        factory = factory,
    )
}

fun <V, F> GABuilder<V, F, *>.population(
    index: Int,
    size: Int,
    name: String = getDefaultName(index),
    factory: (index: Int) -> Chromosome<V, F>,
) {
    population = Population(
        name = name,
        population = Array(size) { factory(it) },
        currentSize = size,
        maxSize = size,
        factory = factory,
    )
}
