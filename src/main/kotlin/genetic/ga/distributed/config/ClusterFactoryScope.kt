package genetic.ga.distributed.config

import genetic.ga.cellular.cellularGA
import genetic.ga.cellular.config.CellularConfigScope
import genetic.ga.cellular.lifecycle.CellLifecycle
import genetic.ga.cellular.lifecycle.CellularLifecycle
import genetic.ga.cellular.lifecycle.cellLifecycle
import genetic.ga.cellular.neighborhood.CellularNeighborhood
import genetic.ga.cellular.population.CellularPopulation
import genetic.ga.cellular.type.CellularType
import genetic.ga.core.GA
import genetic.ga.core.processor.parallelism.config.ParallelismConfig
import genetic.ga.core.processor.parallelism.config.clone
import genetic.ga.core.population.Population
import genetic.ga.core.population.Population.Companion.DEFAULT_POPULATION_NAME
import genetic.ga.panmictic.config.PanmicticConfigScope
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle
import genetic.ga.panmictic.panmicticGA
import genetic.ga.panmictic.population.PanmicticPopulation
import kotlin.random.Random

interface ClusterFactoryScope<V, F> {
    val clusters: List<GA<V, F>>
    val random: Random
    val fitnessFunction: (V) -> F
}

fun <V, F> ClusterFactoryScope<V, F>.pGAs(
    count: Int,
    population: PanmicticPopulation<V, F>,
    fitnessFunction: ((V) -> F) = this.fitnessFunction,
    elitism: Int = 0,
    random: Random = Random(this.random.nextInt()),
    parallelismConfig: ParallelismConfig = ParallelismConfig(),
    evolution: suspend PanmicticLifecycle<V, F>.() -> Unit,
): ClusterFactoryScope<V, F>.() -> List<GA<V, F>> = {
    List(size = count) { index ->
        val currentPopulation = population.getPopulationWithNewNameByIndex(index) { newName -> clone(newName) }
        val currentParallelismConfig = if (index == 0) parallelismConfig else parallelismConfig.clone()
        panmicticGA(currentPopulation, fitnessFunction, random) {
            this.parallelismConfig = currentParallelismConfig
            this.elitism = elitism
            evolve(useDefault = true, evolution)
        }
    }
}

inline fun <V, F> ClusterFactoryScope<V, F>.pGAs(
    count: Int,
    crossinline population: (index: Int) -> PanmicticPopulation<V, F>,
    crossinline fitnessFunction: (index: Int) -> ((V) -> F) = { this.fitnessFunction },
    crossinline elitism: (index: Int) -> Int = { 0 },
    crossinline random: (index: Int) -> Random = { Random(this.random.nextInt()) },
    crossinline parallelismConfig: (index: Int) -> ParallelismConfig = { ParallelismConfig() },
    noinline evolution: suspend PanmicticLifecycle<V, F>.() -> Unit,
): ClusterFactoryScope<V, F>.() -> List<GA<V, F>> = {
    panmicticGAs(count, population, fitnessFunction, random) { index ->
        this.parallelismConfig = parallelismConfig(index)
        this.elitism = elitism(index)
        evolve(useDefault = true, evolution)
    }
}

inline fun <V, F> ClusterFactoryScope<V, F>.panmicticGAs(
    count: Int,
    population: (index: Int) -> PanmicticPopulation<V, F>,
    fitnessFunction: (index: Int) -> ((V) -> F) = { this.fitnessFunction },
    random: (index: Int) -> Random = { Random(this.random.nextInt()) },
    panmicticConfig: PanmicticConfigScope<V, F>.(index: Int) -> Unit,
): List<GA<V, F>> = List(size = count) { index ->
    panmicticGA(population(index), fitnessFunction(index), random(index)) { panmicticConfig(index) }
}

fun <V, F> ClusterFactoryScope<V, F>.cGAs(
    count: Int,
    population: CellularPopulation<V, F>,
    neighborhood: CellularNeighborhood,
    fitnessFunction: ((V) -> F) = this.fitnessFunction,
    elitism: Boolean = false,
    cellularType: CellularType = CellularType.Synchronous,
    random: Random = Random(this.random.nextInt()),
    parallelismConfig: ParallelismConfig = ParallelismConfig(),
    beforeLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)? = null,
    afterLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)? = null,
    cellLifecycle: suspend CellLifecycle<V, F>.() -> Unit,
): ClusterFactoryScope<V, F>.() -> List<GA<V, F>> = {
    List(size = count) { index ->
        val currentPopulation = population.getPopulationWithNewNameByIndex(index) { newName -> clone(newName) }
        val currentParallelismConfig = if (index == 0) parallelismConfig else parallelismConfig.clone()
        cellularGA(currentPopulation, fitnessFunction, random) {
            this.parallelismConfig = currentParallelismConfig
            this.elitism = elitism
            this.neighborhood = neighborhood
            this.cellularType = cellularType
            evolveCellNoWrap(
                parallelismConfig.workersCount,
                beforeLifecycleIteration,
                afterLifecycleIteration,
            ) { chromosome, neighbours, random ->
                with(cellLifecycle(chromosome, neighbours, random)) { cellLifecycle(); cellChromosome }
            }
        }
    }
}

inline fun <V, F> ClusterFactoryScope<V, F>.cGAs(
    count: Int,
    crossinline population: (index: Int) -> CellularPopulation<V, F>,
    crossinline neighborhood: (index: Int) -> CellularNeighborhood,
    crossinline fitnessFunction: (index: Int) -> ((V) -> F) = { this.fitnessFunction },
    crossinline elitism: (index: Int) -> Boolean = { false },
    crossinline cellularType: (index: Int) -> CellularType = { CellularType.Synchronous },
    crossinline random: (index: Int) -> Random = { Random(this.random.nextInt()) },
    crossinline parallelismConfig: (index: Int) -> ParallelismConfig = { ParallelismConfig() },
    noinline beforeLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)? = null,
    noinline afterLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)? = null,
    crossinline cellLifecycle: suspend CellLifecycle<V, F>.() -> Unit,
): ClusterFactoryScope<V, F>.() -> List<GA<V, F>> = {
    cellularGAs(count, population, fitnessFunction, random) { index ->
        this.parallelismConfig = parallelismConfig(index)
        this.elitism = elitism(index)
        this.neighborhood = neighborhood(index)
        this.cellularType = cellularType(index)
        evolveCellNoWrap(
            this.parallelismConfig.workersCount,
            beforeLifecycleIteration,
            afterLifecycleIteration,
        ) { chromosome, neighbours, random ->
            with(cellLifecycle(chromosome, neighbours, random)) { cellLifecycle(); cellChromosome }
        }
    }
}

inline fun <V, F> ClusterFactoryScope<V, F>.cellularGAs(
    count: Int,
    population: (index: Int) -> CellularPopulation<V, F>,
    fitnessFunction: (index: Int) -> ((V) -> F) = { this.fitnessFunction },
    random: (index: Int) -> Random = { Random(this.random.nextInt()) },
    cellularConfig: CellularConfigScope<V, F>.(index: Int) -> Unit,
): List<GA<V, F>> = List(size = count) { index ->
    cellularGA(population(index), fitnessFunction(index), random(index)) { cellularConfig(index) }
}

private inline fun <V, F, reified T : Population<V, F>> T.getPopulationWithNewNameByIndex(
    index: Int,
    factory: T.(newName: String) -> T,
): T {
    if (index <= 0) return this

    val newName = name.generateNewPopulationName(index)
    return factory(newName)
}

private fun String.generateNewPopulationName(index: Int): String =
    if (this == DEFAULT_POPULATION_NAME) "POPULATION ${index + 1}" else this
