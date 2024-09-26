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
import genetic.ga.core.parallelism.config.ParallelismConfigScope
import genetic.ga.core.parallelism.config.parallelismConfig
import genetic.statistics.config.StatisticsConfigScope
import genetic.statistics.config.statConfig
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
    statConfig: StatisticsConfigScope.() -> Unit = { },
    parallelismConfig: ParallelismConfigScope.() -> Unit = { },
    noinline beforeLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)? = null,
    noinline afterLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)? = null,
    crossinline cellLifecycle: suspend CellLifecycle<V, F>.() -> Unit,
): GA<V, F> = cellularGA(population, fitnessFunction, random) {
    this.parallelismConfig(parallelismConfig)
    this.elitism = elitism
    this.cellularType = cellularType
    this.neighborhood = neighborhood
    evolveCellNoWrap(
        parallelWorkersLimit = this.parallelismConfig.count,
        beforeLifecycleIteration = beforeLifecycleIteration,
        afterLifecycleIteration = afterLifecycleIteration,
    ) { chromosome, neighbours ->
        with(cellLifecycle(chromosome, neighbours)) { cellLifecycle(); cellChromosome }
    }

    this.statConfig(statConfig)
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
