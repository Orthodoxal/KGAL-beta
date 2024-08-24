package genetic.clusters.base.lifecycle

import genetic.clusters.base.population.Population
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.random.Random

interface ClusterLifecycle<V, F> : MultiRunHelper, LifecycleStore {
    val random: Random
    val randomSeed: Int

    var population: Population<V, F>
    var maxGeneration: Int
    val iteration: Int
    var fitnessFunction: (V) -> F

    var stopSignal: Boolean

    val extraDispatchers: Array<CoroutineDispatcher>?
}

val ClusterLifecycle<*, *>.isSingleRun: Boolean get() =  extraDispatchers?.isEmpty() ?: true
