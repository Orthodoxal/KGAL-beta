package genetic.ga.core.lifecycle

import genetic.ga.core.population.Population
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.random.Random

interface GALifecycle<V, F> : MultiRunHelper, LifecycleStore {
    val random: Random

    var population: Population<V, F>
    var maxGeneration: Int
    var iteration: Int
    var fitnessFunction: (V) -> F

    var stopSignal: Boolean

    val extraDispatchers: Array<CoroutineDispatcher>?
}

val GALifecycle<*, *>.isSingleRun: Boolean get() = extraDispatchers?.isEmpty() ?: true

val GALifecycle<*, *>.name get() = population.name

var <V, F> GALifecycle<V, F>.currentSize
    get() = population.currentSize
    set(value) {
        population.currentSize = value
    }

var <V, F> GALifecycle<V, F>.maxSize
    get() = population.maxSize
    set(value) {
        population.maxSize = value
    }

var <V, F> GALifecycle<V, F>.factory
    get() = population.factory
    set(value) {
        population.factory = value
    }
