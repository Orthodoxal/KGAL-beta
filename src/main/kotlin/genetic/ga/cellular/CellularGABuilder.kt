package genetic.ga.cellular

import genetic.chromosome.Chromosome
import genetic.ga.core.builder.GABuilder
import genetic.ga.cellular.lifecycle.CellLifecycle
import genetic.ga.cellular.lifecycle.CellularLifecycle
import genetic.ga.cellular.neighborhood.CellularNeighborhood
import genetic.ga.cellular.type.CellularType

interface CellularGABuilder<V, F> : GABuilder<V, F, CellularLifecycle<V, F>> {
    var elitism: Boolean
    var cellularType: CellularType
    var dimensions: IntArray
    var neighborhood: CellularNeighborhood
    var neighboursIndicesCache: Array<IntArray>

    fun CellularGABuilder<V, F>.evolveCell(
        beforeLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        afterLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        cellLifecycle: suspend CellLifecycle<V, F>.() -> Unit,
    )

    fun CellularGABuilder<V, F>.evolveCellNoWrap(
        beforeLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        afterLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        cellLifecycle: suspend CellularLifecycle<V, F>.(chromosome: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
    )
}
