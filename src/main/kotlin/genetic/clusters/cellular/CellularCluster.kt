package genetic.clusters.cellular

import genetic.chromosome.Chromosome
import genetic.clusters.base.AbstractCluster
import genetic.clusters.cellular.lifecycle.*
import genetic.clusters.cellular.neighborhood.CellularNeighborhood
import genetic.clusters.cellular.neighborhood.moore.Moore
import genetic.clusters.cellular.neighborhood.toroidalShapeIndicesFilter
import genetic.clusters.cellular.type.CellularType

class CellularCluster<V, F> : AbstractCluster<V, F, CellularLifecycle<V, F>>(), CellularClusterBuilder<V, F> {
    override lateinit var lifecycle: suspend CellularLifecycle<V, F>.() -> Unit
    override val lifecycleScope: CellularLifecycle<V, F> by lazy { CellularLifecycleInstance(this) }
    override var beforeLifecycle: suspend CellularLifecycle<V, F>.() -> Unit = { } // FIXME добавить evaluation
    override var afterLifecycle: suspend CellularLifecycle<V, F>.() -> Unit = { }
    override var elitism: Boolean = true
    override var cellularType: CellularType = CellularType.Synchronous
    override var dimensions: IntArray = intArrayOf()
    override var neighborhood: CellularNeighborhood = Moore(radius = 1)
        set(value) {
            if (field != value) neighborhoodChanged = true
            field = value
        }
    override var neighboursIndicesCache: Array<IntArray> = arrayOf(intArrayOf())


    private var neighborhoodChanged: Boolean = false

    override fun CellularClusterBuilder<V, F>.lifecycle(
        before: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        after: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        beforeLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        afterLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        cellLifecycle: suspend CellLifecycle<V, F>.() -> Unit,
    ) = lifecycleNoWrap(before, after, beforeLifecycleIteration, afterLifecycleIteration) { chromosome, neighbours ->
        with(cellLifecycle(chromosome, neighbours)) { cellLifecycle(); cellChromosome }
    }

    override fun CellularClusterBuilder<V, F>.lifecycleNoWrap(
        before: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        after: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        beforeLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        afterLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        cellLifecycle: suspend CellularLifecycle<V, F>.(chromosome: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
    ) {
        val beforeCellular: suspend CellularLifecycle<V, F>.() -> Unit = {
            checkNeighborhoodChanged()
            before?.invoke(this) ?: beforeLifecycle()
        }
        lifecycle(
            before = beforeCellular,
            after = after,
            lifecycle = buildCellularLifecycle(beforeLifecycleIteration, afterLifecycleIteration, cellLifecycle),
        )
    }

    private fun checkNeighborhoodChanged() {
        if (neighborhoodChanged) {
            val (indicesOneArray, indicesNArray) = neighborhood.neighboursIndicesMatrix(dimensions)
            val size = dimensions.fold(1) { acc, i -> acc * i }
            neighboursIndicesCache = Array(size) { position ->
                toroidalShapeIndicesFilter(position, dimensions, indicesOneArray, indicesNArray)
            }
            neighborhoodChanged = false
        }
    }
}
