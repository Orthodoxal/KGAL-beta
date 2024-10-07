package genetic.ga.core.config

import genetic.ga.core.lifecycle.Lifecycle
import genetic.ga.core.processor.parallelism.config.ParallelismConfig
import genetic.statistics.config.StatisticsConfig
import kotlin.random.Random

interface ConfigGA<V, F, L : Lifecycle<V, F>> {
    val random: Random
    val fitnessFunction: (V) -> F
    val statisticsConfig: StatisticsConfig
    val parallelismConfig: ParallelismConfig

    val beforeEvolution: suspend L.() -> Unit
    val evolution: suspend L.() -> Unit
    val afterEvolution: suspend L.() -> Unit
}
