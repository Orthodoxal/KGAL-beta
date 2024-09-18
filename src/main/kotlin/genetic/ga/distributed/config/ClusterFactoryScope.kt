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
import genetic.ga.panmictic.config.PanmicticConfigScope
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle
import genetic.ga.panmictic.panmicticGA
import genetic.ga.panmictic.population.PanmicticPopulation
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.random.Random

interface ClusterFactoryScope<V, F> {
    val clusters: List<GA<V, F>>
    val random: Random
    val fitnessFunction: (V) -> F
    val distributedExtraDispatchers: Array<CoroutineDispatcher>?
}

inline fun <V, F> ClusterFactoryScope<V, F>.panmicticGA(
    population: PanmicticPopulation<V, F>,
    noinline fitnessFunction: (V) -> F = this.fitnessFunction,
    random: Random = Random(this.random.nextInt()),
    panmicticConfig: PanmicticConfigScope<V, F>.() -> Unit,
): GA<V, F> = panmicticGA(population, fitnessFunction, random, skipValidation = false) {
    panmicticConfig()
    mainDispatcher?.let { mainDispatcher = distributedExtraDispatchers?.getOrNull(clusters.size) }
}

fun <V, F> ClusterFactoryScope<V, F>.pGAs(
    count: Int,
    population: PanmicticPopulation<V, F>,
    fitnessFunction: ((V) -> F) = this.fitnessFunction,
    elitism: Int = 0,
    random: Random = Random(this.random.nextInt()),
    mainDispatcher: CoroutineDispatcher? = null,
    extraDispatchers: Array<CoroutineDispatcher>? = null,
    evolution: suspend PanmicticLifecycle<V, F>.() -> Unit,
): ClusterFactoryScope<V, F>.() -> List<GA<V, F>> = {
    List(size = count) { index ->
        val currentPopulation = if (index == 0) population else population.copy()
        panmicticGA(currentPopulation, fitnessFunction, random) {
            this.mainDispatcher = mainDispatcher
            this.extraDispatchers = extraDispatchers
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
    crossinline mainDispatcher: (index: Int) -> CoroutineDispatcher? = { null },
    crossinline extraDispatchers: (index: Int) -> Array<CoroutineDispatcher>? = { null },
    noinline evolution: suspend PanmicticLifecycle<V, F>.() -> Unit,
): ClusterFactoryScope<V, F>.() -> List<GA<V, F>> = {
    panmicticGAs(count, population, fitnessFunction, random) { index ->
        this.mainDispatcher = mainDispatcher(index)
        this.extraDispatchers = extraDispatchers(index)
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

inline fun <V, F> ClusterFactoryScope<V, F>.cellularGA(
    population: CellularPopulation<V, F>,
    noinline fitnessFunction: (V) -> F = this.fitnessFunction,
    random: Random = Random(this.random.nextInt()),
    cellularConfig: CellularConfigScope<V, F>.() -> Unit,
): GA<V, F> = cellularGA(population, fitnessFunction, random, skipValidation = false) {
    cellularConfig()
    mainDispatcher?.let { mainDispatcher = distributedExtraDispatchers?.getOrNull(clusters.size) }
}

fun <V, F> ClusterFactoryScope<V, F>.cGAs(
    count: Int,
    population: CellularPopulation<V, F>,
    neighborhood: CellularNeighborhood,
    fitnessFunction: ((V) -> F) = this.fitnessFunction,
    elitism: Boolean = false,
    cellularType: CellularType = CellularType.Synchronous,
    random: Random = Random(this.random.nextInt()),
    mainDispatcher: CoroutineDispatcher? = null,
    extraDispatchers: Array<CoroutineDispatcher>? = null,
    beforeLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)? = null,
    afterLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)? = null,
    cellLifecycle: suspend CellLifecycle<V, F>.() -> Unit,
): ClusterFactoryScope<V, F>.() -> List<GA<V, F>> = {
    List(size = count) { index ->
        val currentPopulation = if (index == 0) population else population.copy()
        cellularGA(currentPopulation, fitnessFunction, random) {
            this.mainDispatcher = mainDispatcher
            this.extraDispatchers = extraDispatchers
            this.elitism = elitism
            this.neighborhood = neighborhood
            this.cellularType = cellularType
            evolveCellNoWrap(beforeLifecycleIteration, afterLifecycleIteration) { chromosome, neighbours ->
                with(cellLifecycle(chromosome, neighbours)) { cellLifecycle(); cellChromosome }
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
    crossinline mainDispatcher: (index: Int) -> CoroutineDispatcher? = { null },
    crossinline extraDispatchers: (index: Int) -> Array<CoroutineDispatcher>? = { null },
    noinline beforeLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)? = null,
    noinline afterLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)? = null,
    crossinline cellLifecycle: suspend CellLifecycle<V, F>.() -> Unit,
): ClusterFactoryScope<V, F>.() -> List<GA<V, F>> = {
    cellularGAs(count, population, fitnessFunction, random) { index ->
        this.mainDispatcher = mainDispatcher(index)
        this.extraDispatchers = extraDispatchers(index)
        this.elitism = elitism(index)
        this.neighborhood = neighborhood(index)
        this.cellularType = cellularType(index)
        evolveCellNoWrap(beforeLifecycleIteration, afterLifecycleIteration) { chromosome, neighbours ->
            with(cellLifecycle(chromosome, neighbours)) { cellLifecycle(); cellChromosome }
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
