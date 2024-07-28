package genetic.clusters.base.lifecycle

import genetic.clusters.base.population.Population
import kotlin.random.Random

interface ClusterLifecycle<V, F> : MultiRunHelper, LifecycleStore {
    val random: Random
    val randomSeed: Int

    var population: Population<V, F>
    var maxGeneration: Int
    val generation: Int
    var fitnessFunction: (V) -> F

    var stopSignal: Boolean
}
