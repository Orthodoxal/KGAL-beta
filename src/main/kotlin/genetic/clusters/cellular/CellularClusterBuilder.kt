package genetic.clusters.cellular

import genetic.chromosome.Chromosome
import genetic.clusters.base.builder.ClusterBuilder
import genetic.clusters.cellular.lifecycle.CellLifecycle
import genetic.clusters.cellular.lifecycle.CellularLifecycle
import genetic.clusters.cellular.neighborhood.CellularNeighborhood
import genetic.clusters.cellular.type.CellularType

interface CellularClusterBuilder<V, F> : ClusterBuilder<V, F, CellularLifecycle<V, F>> {
    var elitism: Boolean
    var cellularType: CellularType
    var dimensions: IntArray
    var neighborhood: CellularNeighborhood
    var neighboursIndicesCache: Array<IntArray>

    fun CellularClusterBuilder<V, F>.lifecycle(
        before: (suspend CellularLifecycle<V, F>.() -> Unit)? = null,
        after: (suspend CellularLifecycle<V, F>.() -> Unit)? = null,
        beforeLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        afterLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        cellLifecycle: suspend CellLifecycle<V, F>.() -> Unit,
    )

    fun CellularClusterBuilder<V, F>.lifecycleNoWrap(
        before: (suspend CellularLifecycle<V, F>.() -> Unit)? = null,
        after: (suspend CellularLifecycle<V, F>.() -> Unit)? = null,
        beforeLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        afterLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        cellLifecycle: suspend CellularLifecycle<V, F>.(chromosome: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
    )
}
