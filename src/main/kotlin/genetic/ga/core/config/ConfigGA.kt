package genetic.ga.core.config

import genetic.ga.core.lifecycle.Lifecycle
import genetic.stat.config.StatisticsConfig
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.random.Random

interface ConfigGA<V, F, L : Lifecycle<V, F>> {
    val random: Random
    val fitnessFunction: (V) -> F
    val maxIteration: Int
    val mainDispatcher: CoroutineDispatcher?
    val extraDispatchers: Array<CoroutineDispatcher>?
    val statisticsConfig: StatisticsConfig

    val beforeEvolution: suspend L.() -> Unit
    val evolution: suspend L.() -> Unit
    val afterEvolution: suspend L.() -> Unit
}
