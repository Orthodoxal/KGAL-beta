package genetic.ga.panmictic

import genetic.ga.core.GA
import genetic.ga.core.parallelism.config.ParallelismConfigScope
import genetic.ga.core.parallelism.config.parallelismConfig
import genetic.ga.panmictic.config.PanmicticConfig
import genetic.ga.panmictic.config.PanmicticConfigScope
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle
import genetic.ga.panmictic.population.PanmicticPopulation
import genetic.statistics.config.StatisticsConfigScope
import genetic.statistics.config.statConfig
import kotlin.random.Random

interface PanmicticGA<V, F> : GA<V, F> {
    override val population: PanmicticPopulation<V, F>
    var elitism: Int

    companion object {
        fun <V, F> create(
            configuration: PanmicticConfig<V, F>,
            population: PanmicticPopulation<V, F>,
        ): PanmicticGA<V, F> = PanmicticGAInstance(configuration, population)
    }
}

fun <V, F> pGA(
    population: PanmicticPopulation<V, F>,
    fitnessFunction: (V) -> F,
    elitism: Int = 0,
    random: Random = Random,
    statConfig: StatisticsConfigScope.() -> Unit = { },
    parallelismConfig: ParallelismConfigScope.() -> Unit = { },
    evolution: suspend PanmicticLifecycle<V, F>.() -> Unit,
): GA<V, F> = panmicticGA(population, fitnessFunction, random) {
    this.parallelismConfig(parallelismConfig)
    this.elitism = elitism
    evolve(useDefault = true, evolution)
    this.statConfig(statConfig)
}

inline fun <V, F> panmicticGA(
    population: PanmicticPopulation<V, F>,
    noinline fitnessFunction: (V) -> F,
    random: Random = Random,
    config: PanmicticConfigScope<V, F>.() -> Unit,
): GA<V, F> {
    val configuration: PanmicticConfig<V, F> =
        PanmicticConfigScope(random, fitnessFunction).apply(config)
    return PanmicticGA.create(configuration, population)
}
