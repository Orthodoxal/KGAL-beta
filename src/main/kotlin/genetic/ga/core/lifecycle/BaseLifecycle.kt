package genetic.ga.core.lifecycle

import genetic.ga.core.GA
import genetic.ga.core.population.Population
import genetic.stat.note.StatisticNote
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.random.Random

internal class BaseLifecycle<V, F>(
    private val ga: GA<V, F>,
    multiRunHelper: MultiRunHelper = MultiRunHelperInstance(),
    lifecycleStore: LifecycleStore = LifecycleStoreInstance(),
) : Lifecycle<V, F>, MultiRunHelper by multiRunHelper, LifecycleStore by lifecycleStore {
    override val random: Random get() = ga.random

    override val iteration: Int
        get() = ga.iteration

    override val population: Population<V, F>
        get() = ga.population

    override val maxIteration: Int
        get() = ga.maxIteration

    override var fitnessFunction: (V) -> F
        get() = ga.fitnessFunction
        set(value) {
            ga.fitnessFunction = value
        }

    override val extraDispatchers: Array<CoroutineDispatcher>? get() = ga.extraDispatchers

    override var stopSignal: Boolean = false

    override suspend fun emitStat(value: StatisticNote<Any?>) = ga.emitStat(value)
}
