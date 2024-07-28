package genetic.clusters.cellular.operators.selection

import genetic.clusters.base.operators.selection.comparable.selectionBest
import genetic.clusters.base.operators.selection.comparable.selectionWorst
import genetic.clusters.cellular.lifecycle.CellLifecycle
import genetic.utils.fillArrayChromosomeBySubArray

fun <V, F> CellLifecycle<V, F>.selBest(
    count: Int,
) = fillArrayChromosomeBySubArray(neighbours, selectionBest(neighbours, count))

fun <V, F> CellLifecycle<V, F>.selWorst(
    count: Int,
) = fillArrayChromosomeBySubArray(neighbours, selectionWorst(neighbours, count))
