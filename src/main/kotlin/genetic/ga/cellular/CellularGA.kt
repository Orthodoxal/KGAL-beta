package genetic.ga.cellular

import genetic.ga.cellular.config.CellularConfig
import genetic.ga.cellular.config.CellularConfigScope
import genetic.ga.cellular.lifecycle.CellLifecycle
import genetic.ga.cellular.lifecycle.CellularLifecycle
import genetic.ga.cellular.lifecycle.cellLifecycle
import genetic.ga.cellular.neighborhood.CellularNeighborhood
import genetic.ga.cellular.population.CellularPopulation
import genetic.ga.cellular.type.CellularType
import genetic.ga.core.GA
import genetic.ga.core.processor.parallelism.config.ParallelismConfig
import genetic.statistics.config.StatisticsConfig
import kotlin.random.Random

interface CellularGA<V, F> : GA<V, F> {
    override val population: CellularPopulation<V, F>
    var elitism: Boolean
    var cellularType: CellularType
    var neighborhood: CellularNeighborhood

    companion object {
        fun <V, F> create(
            configuration: CellularConfig<V, F>,
            population: CellularPopulation<V, F>,
        ): CellularGA<V, F> = CellularGAInstance(configuration, population)
    }
}

inline fun <V, F> cGA(
    population: CellularPopulation<V, F>,
    noinline fitnessFunction: (V) -> F,
    neighborhood: CellularNeighborhood,
    elitism: Boolean = false,
    cellularType: CellularType = CellularType.Synchronous,
    random: Random = Random,
    parallelismConfig: ParallelismConfig = ParallelismConfig(),
    statisticsConfig: StatisticsConfig = StatisticsConfig(),
    noinline beforeLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)? = null,
    noinline afterLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)? = null,
    crossinline cellLifecycle: suspend CellLifecycle<V, F>.() -> Unit,
): GA<V, F> = cellularGA(population, fitnessFunction, random) {
    this.parallelismConfig = parallelismConfig
    this.elitism = elitism
    this.cellularType = cellularType
    this.neighborhood = neighborhood
    evolveCellNoWrap(
        parallelismLimit = this.parallelismConfig.workersCount,
        beforeLifecycleIteration = beforeLifecycleIteration,
        afterLifecycleIteration = afterLifecycleIteration,
    ) { chromosome, neighbours, random ->
        with(cellLifecycle(chromosome, neighbours, random)) { cellLifecycle(); cellChromosome }
    }
    this.statisticsConfig = statisticsConfig
}

inline fun <V, F> cellularGA(
    population: CellularPopulation<V, F>,
    noinline fitnessFunction: (V) -> F,
    random: Random = Random,
    config: CellularConfigScope<V, F>.() -> Unit,
): GA<V, F> {
    val configuration: CellularConfig<V, F> =
        CellularConfigScope(random, fitnessFunction).apply(config)
    return CellularGA.create(configuration, population)
}
