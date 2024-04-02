package genetic.ga.cellular.builder

import genetic.chromosome.Chromosome
import genetic.clusters.simple_cluster.builder.SimpleClusterBuilder
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.GABuilder
import genetic.ga.cellular.lifecycle.CellularGALifecycle
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle
import genetic.ga.cellular.neighborhood.CellularNeighborhood
import genetic.ga.cellular.type.CellularType

interface CellularGABuilder<V, F> : GABuilder<V, F> {
    var neighborhood: CellularNeighborhood
    var cellularType: CellularType
    var dimensions: IntArray
    val neighboursIndicesCache: Array<IntArray>
    val customClusterConfigs: List<CellularClusterConfig>

    fun CellularGABuilder<V, F>.lifecycle(
        before: (suspend CellularGALifecycle<V, F>.() -> Unit)? = null,
        after: (suspend CellularGALifecycle<V, F>.() -> Unit)? = null,
        lifecycle: suspend CellularGALifecycle<V, F>.() -> Unit,
    )

    fun SimpleClusterBuilder<V, F>.lifecycleCellularNoWrap(
        elitism: Boolean = true,
        before: (suspend SimpleClusterLifecycle<V, F>.() -> Unit)? = null,
        after: (suspend SimpleClusterLifecycle<V, F>.() -> Unit)? = null,
        afterLifecycleIteration: (suspend SimpleClusterLifecycle<V, F>.() -> Unit)? = null,
        lifecycle: suspend SimpleClusterLifecycle<V, F>.(chromosome1: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
    )

    fun SimpleClusterBuilder<V, F>.lifecycleCellular(
        elitism: Boolean = true,
        before: (suspend SimpleClusterLifecycle<V, F>.() -> Unit)? = null,
        after: (suspend SimpleClusterLifecycle<V, F>.() -> Unit)? = null,
        afterLifecycleIteration: (suspend SimpleClusterLifecycle<V, F>.() -> Unit)? = null,
        lifecycle: suspend SimpleClusterCellLifecycle<V, F>.() -> Unit,
    )

    fun applyCustomClusterConfig(cellularClusterConfig: CellularClusterConfig)
}
