package genetic.ga.distributed

import genetic.ga.core.GA
import genetic.ga.distributed.config.ClusterFactoryScope
import genetic.ga.distributed.config.DistributedConfig
import genetic.ga.distributed.config.DistributedConfigScope
import genetic.ga.distributed.lifecycle.DistributedLifecycle
import genetic.ga.distributed.population.DistributedPopulation
import genetic.ga.distributed.population.DistributedPopulation.Companion.DEFAULT_NAME_BUILDER
import genetic.ga.distributed.population.population
import genetic.stat.config.StatisticsConfigScope
import genetic.stat.config.statConfig
import kotlinx.coroutines.CoroutineDispatcher
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
    noinline fitnessFunction: (V) -> F = {
        throw IllegalStateException("Fitness function for cluster of distributed GA not implemented")
    },
    random: Random = Random,
    mainDispatcher: CoroutineDispatcher? = null,
    extraDispatchers: Array<CoroutineDispatcher>? = null,
    skipValidation: Boolean = false, // TODO добавить валидатор конфигурации
    statConfig: StatisticsConfigScope.() -> Unit = { },
    noinline nameBuilder: (childNames: List<String>) -> String = DEFAULT_NAME_BUILDER,
    noinline evolution: suspend DistributedLifecycle<V, F>.() -> Unit,
): DistributedGA<V, F> =
    distributedGA(fitnessFunction, random, skipValidation, nameBuilder) {
        this.mainDispatcher = mainDispatcher
        this.extraDispatchers = extraDispatchers

        this.clusters.addAll(clusters())
        evolve(useDefault = true, evolution)

        this.statConfig(statConfig)
    }

inline fun <V, F> distributedGA(
    noinline fitnessFunction: (V) -> F = {
        throw IllegalStateException("Fitness function for cluster of distributed GA not implemented")
    },
    random: Random = Random,
    skipValidation: Boolean = false, // TODO добавить валидатор конфигурации
    noinline nameBuilder: (childNames: List<String>) -> String = DEFAULT_NAME_BUILDER,
    config: DistributedConfigScope<V, F>.() -> Unit,
): DistributedGA<V, F> {
    val configuration: DistributedConfig<V, F> =
        DistributedConfigScope(random, fitnessFunction).apply(config)
    return DistributedGA.create(
        configuration = configuration,
        population = population(
            childPopulations = configuration.clusters.map { it.population },
            nameBuilder = nameBuilder,
        ),
    )
}
