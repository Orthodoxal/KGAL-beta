package genetic.ga.cellular

import genetic.chromosome.Chromosome
import genetic.ga.core.AbstractGA
import genetic.ga.core.builder.GABuilder
import genetic.ga.cellular.lifecycle.*
import genetic.ga.cellular.neighborhood.CellularNeighborhood
import genetic.ga.cellular.neighborhood.moore.Moore
import genetic.ga.cellular.neighborhood.toroidalShapeIndicesFilter
import genetic.ga.cellular.operators.evaluation.evaluation
import genetic.ga.cellular.type.CellularType
import kotlin.random.Random

class CellularGA<V, F>(
    random: Random,
) : AbstractGA<V, F, CellularLifecycle<V, F>>(random), CellularGABuilder<V, F> {
    override val evolveScope: CellularLifecycle<V, F> by lazy { CellularLifecycleInstance(this) }

    override val baseBefore: suspend CellularLifecycle<V, F>.() -> Unit = { checkNeighborhoodChanged(); evaluation() }

    override var before: suspend CellularLifecycle<V, F>.() -> Unit = baseBefore
    override lateinit var evolve: suspend CellularLifecycle<V, F>.() -> Unit
    override var after: suspend CellularLifecycle<V, F>.() -> Unit = baseAfter

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

    override fun CellularGABuilder<V, F>.evolveCell(
        beforeLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        afterLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        cellLifecycle: suspend CellLifecycle<V, F>.() -> Unit,
    ) = evolveCellNoWrap(beforeLifecycleIteration, afterLifecycleIteration) { chromosome, neighbours ->
        with(cellLifecycle(chromosome, neighbours)) { cellLifecycle(); cellChromosome }
    }

    override fun CellularGABuilder<V, F>.evolveCellNoWrap(
        beforeLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        afterLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)?,
        cellLifecycle: suspend CellularLifecycle<V, F>.(chromosome: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
    ) = evolve(
        evolution = buildCellularLifecycle(beforeLifecycleIteration, afterLifecycleIteration, cellLifecycle),
    )

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
