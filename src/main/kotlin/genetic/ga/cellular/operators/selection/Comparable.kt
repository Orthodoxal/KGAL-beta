package genetic.ga.cellular.operators.selection

import genetic.ga.base_operators.selection.comparable.selectionBest
import genetic.ga.base_operators.selection.comparable.selectionWorst
import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle
import genetic.utils.fillArrayChromosomeBySubArray

fun <V, F> SimpleClusterCellLifecycle<V, F>.selectionBest(
    cellularGABuilder: CellularGABuilder<V, F>,
    count: Int,
) = fillArrayChromosomeBySubArray(neighbours, selectionBest(neighbours, count))

fun <V, F> SimpleClusterCellLifecycle<V, F>.selectionWorst(
    cellularGABuilder: CellularGABuilder<V, F>,
    count: Int,
) = fillArrayChromosomeBySubArray(neighbours, selectionWorst(neighbours, count))
