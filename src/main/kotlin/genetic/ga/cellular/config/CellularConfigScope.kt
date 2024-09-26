package genetic.ga.cellular.config

import genetic.chromosome.Chromosome
import genetic.ga.cellular.lifecycle.CellLifecycle
import genetic.ga.cellular.lifecycle.CellularLifecycle
import genetic.ga.cellular.lifecycle.buildCellularLifecycle
import genetic.ga.cellular.lifecycle.cellLifecycle
import genetic.ga.cellular.neighborhood.CellularNeighborhood
import genetic.ga.cellular.neighborhood.moore.Moore
import genetic.ga.cellular.operators.cache.cacheNeighborhood
import genetic.ga.cellular.operators.evaluation.evaluationAll
import genetic.ga.cellular.type.CellularType
import genetic.ga.core.config.AbstractConfigGAScope
import genetic.ga.core.operators.fillPopulationIfEmpty
import kotlin.random.Random

class CellularConfigScope<V, F>(
    override val random: Random,
    override val fitnessFunction: (V) -> F,
) : CellularConfig<V, F>, AbstractConfigGAScope<V, F, CellularLifecycle<V, F>>() {
    override var elitism: Boolean = false
    override var cellularType: CellularType = CellularType.Synchronous
    override var neighborhood: CellularNeighborhood = Moore(radius = 1)

    override val baseBefore: suspend CellularLifecycle<V, F>.() -> Unit = {
        fillPopulationIfEmpty(); cacheNeighborhood(); evaluationAll()
    }
    override var beforeEvolution: suspend CellularLifecycle<V, F>.() -> Unit = baseBefore

    fun CellularConfigScope<V, F>.evolveCell(
        parallelWorkersLimit: Int = parallelismConfig.count,
        beforeLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        afterLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        cellLifecycle: suspend CellLifecycle<V, F>.() -> Unit,
    ) = evolveCellNoWrap(
        parallelWorkersLimit = parallelWorkersLimit,
        beforeLifecycleIteration = beforeLifecycleIteration,
        afterLifecycleIteration = afterLifecycleIteration,
    ) { chromosome, neighbours ->
        with(cellLifecycle(chromosome, neighbours)) { cellLifecycle(); cellChromosome }
    }

    fun CellularConfigScope<V, F>.evolveCellNoWrap(
        parallelWorkersLimit: Int = parallelismConfig.count,
        beforeLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        afterLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        cellLifecycle: suspend CellularLifecycle<V, F>.(chromosome: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
    ) = evolve(
        useDefault = true,
        evolution = buildCellularLifecycle(
            parallelWorkersLimit = parallelWorkersLimit,
            beforeLifecycleIteration = beforeLifecycleIteration,
            afterLifecycleIteration = afterLifecycleIteration,
            cellLifecycle = cellLifecycle,
        ),
    )
}
