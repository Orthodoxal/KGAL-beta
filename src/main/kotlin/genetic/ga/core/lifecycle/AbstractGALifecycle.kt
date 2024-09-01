package genetic.ga.core.lifecycle

import genetic.ga.core.AbstractGA
import genetic.ga.core.population.Population
import genetic.stat.note.StatisticNote
import kotlin.random.Random

abstract class AbstractGALifecycle<V, F>(
    private val abstractCluster: AbstractGA<V, F, *>,
    multiRunHelper: MultiRunHelper = MultiRunHelperInstance(),
    lifecycleStore: LifecycleStore = LifecycleStoreInstance(),
) : GALifecycle<V, F>, MultiRunHelper by multiRunHelper, LifecycleStore by lifecycleStore {
    override val random: Random get() = abstractCluster.random

    override var iteration: Int
        get() = abstractCluster.iteration
        set(value) {
            abstractCluster.iteration = value
        }

    override var population: Population<V, F>
        get() = abstractCluster.population
        set(value) {
            abstractCluster.population = value
        }

    override var maxGeneration: Int
        get() = abstractCluster.maxIteration
        set(value) {
            abstractCluster.maxIteration = value
        }

    override var fitnessFunction: (V) -> F
        get() = abstractCluster.fitnessFunction
            ?: throw IllegalStateException("Fitness function is null for cluster: $name")
        set(value) {
            abstractCluster.fitnessFunction = value
        }

    override var stopSignal: Boolean = false

    override suspend fun emitStat(value: StatisticNote<Any?>) = abstractCluster.emitStat(value)
}
