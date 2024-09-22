package genetic.ga.core.lifecycle

import genetic.ga.core.population.Population
import genetic.statistics.note.StatisticNote
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.random.Random

interface Lifecycle<V, F> : MultiRunHelper, LifecycleStore {
    val random: Random
    val population: Population<V, F>
    val iteration: Int
    var fitnessFunction: (V) -> F
    val extraDispatchers: List<CoroutineDispatcher>

    var finishByStopConditions: Boolean
    var finishedByMaxIteration: Boolean

    suspend fun emitStat(value: StatisticNote<Any?>)
}

val Lifecycle<*, *>.isSingleRun: Boolean get() = extraDispatchers.isEmpty()

val Lifecycle<*, *>.name
    get() = population.name

var <V, F> Lifecycle<V, F>.size
    get() = population.size
    set(value) {
        population.size = value
    }

val <V, F> Lifecycle<V, F>.maxSize
    get() = population.maxSize

var <V, F> Lifecycle<V, F>.factory
    get() = population.factory
    set(value) {
        population.factory = value
    }
