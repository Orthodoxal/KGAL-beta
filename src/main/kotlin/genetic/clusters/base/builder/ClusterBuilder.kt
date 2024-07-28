package genetic.clusters.base.builder

import genetic.clusters.base.population.Population
import genetic.stat.StatisticsInstance
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.random.Random

interface ClusterBuilder<V, F> {
    var name: String?
    val random: Random
    var randomSeed: Int

    var population: Population<V, F>
    var maxGeneration: Int
    var fitnessFunction: (V) -> F

    var stat: StatisticsInstance?

    var mainDispatcher: CoroutineDispatcher?
    var extraDispatchers: Array<CoroutineDispatcher>?
}
