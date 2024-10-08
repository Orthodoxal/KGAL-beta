package genetic.ga.distributed

import genetic.ga.core.GA
import genetic.ga.core.processor.parallelism.config.ParallelismConfig
import genetic.ga.distributed.config.ClusterFactoryScope
import genetic.ga.distributed.config.DistributedConfig
import genetic.ga.distributed.config.DistributedConfigScope
import genetic.ga.distributed.lifecycle.DistributedLifecycle
import genetic.ga.distributed.population.DistributedPopulation
import genetic.ga.distributed.population.DistributedPopulation.Companion.DEFAULT_NAME_BUILDER
import genetic.ga.distributed.population.population
import genetic.statistics.config.StatisticsConfig
import kotlin.random.Random

interface DistributedGA<V, F> : GA<V, F> {
    override val population: DistributedPopulation<V, F>
    val clusters: List<GA<V, F>>

    companion object {
        fun <V, F> create(
            configuration: DistributedConfig<V, F>,
            population: DistributedPopulation<V, F>,
        ): DistributedGA<V, F> = DistributedGAInstance(configuration, population)
    }
}

inline fun <V, F> dGA(
    clusters: ClusterFactoryScope<V, F>.() -> List<GA<V, F>>,
    maxIteration: Int = 1,
    noinline fitnessFunction: (V) -> F = {
        throw IllegalStateException("Fitness function for cluster of distributed GA not implemented")
    },
    random: Random = Random,
    parallelismConfig: ParallelismConfig = ParallelismConfig(),
    statisticsConfig: StatisticsConfig = StatisticsConfig(),
    noinline nameBuilder: (childNames: List<String>) -> String = DEFAULT_NAME_BUILDER,
    noinline evolution: (suspend DistributedLifecycle<V, F>.() -> Unit)? = null,
): DistributedGA<V, F> =
    distributedGA(maxIteration, fitnessFunction, random, nameBuilder) {
        this.parallelismConfig = parallelismConfig
        this.clusters.addAll(clusters())
        evolution?.let { evolve(useDefault = true, evolution) }
        this.statisticsConfig = statisticsConfig
    }

inline fun <V, F> distributedGA(
    maxIteration: Int,
    noinline fitnessFunction: (V) -> F = {
        throw IllegalStateException("Fitness function for cluster of distributed GA not implemented")
    },
    random: Random = Random,
    noinline nameBuilder: (childNames: List<String>) -> String = DEFAULT_NAME_BUILDER,
    config: DistributedConfigScope<V, F>.() -> Unit,
): DistributedGA<V, F> {
    val configuration: DistributedConfig<V, F> =
        DistributedConfigScope(random, fitnessFunction, maxIteration).apply(config)
    return DistributedGA.create(
        configuration = configuration,
        population = population(
            childPopulations = configuration.clusters.map { it.population },
            nameBuilder = nameBuilder,
        ),
    )
}
