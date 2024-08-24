package genetic.clusters.base.lifecycle

import genetic.clusters.base.AbstractCluster
import genetic.clusters.base.population.Population
import kotlin.random.Random

abstract class AbstractClusterLifecycle<V, F>(
    private val abstractCluster: AbstractCluster<V, F, *>,
    multiRunHelper: MultiRunHelper = MultiRunHelperInstance(),
    lifecycleStore: LifecycleStore = LifecycleStoreInstance(),
) : ClusterLifecycle<V, F>, MultiRunHelper by multiRunHelper, LifecycleStore by lifecycleStore {
    override val random: Random get() = abstractCluster.random
    override val randomSeed: Int get() = abstractCluster.randomSeed

    override val iteration: Int get() = abstractCluster.iteration

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
            ?: throw IllegalStateException("Fitness function is null for cluster: ${abstractCluster.name}")
        set(value) {
            abstractCluster.fitnessFunction = value
        }

    override var stopSignal: Boolean = false
}
