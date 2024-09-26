package genetic.ga.core.lifecycle

import genetic.ga.core.GA
import genetic.ga.core.population.Population
import genetic.statistics.note.StatisticNote
import kotlin.random.Random

abstract class AbstractLifecycle<V, F>(
    private val ga: GA<V, F>,
) : Lifecycle<V, F> {
    override val random: Random get() = ga.random

    override val iteration: Int
        get() = ga.iteration

    override val population: Population<V, F>
        get() = ga.population

    override var fitnessFunction: (V) -> F
        get() = ga.fitnessFunction
        set(value) {
            ga.fitnessFunction = value
        }

    override val store: MutableMap<Any, Any?> = mutableMapOf()

    override var finishByStopConditions: Boolean = false
    override var finishedByMaxIteration: Boolean = false

    override suspend fun emitStat(value: StatisticNote<Any?>) = ga.statisticsProvider.emit(value)
}
