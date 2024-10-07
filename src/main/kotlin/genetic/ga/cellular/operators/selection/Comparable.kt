package genetic.ga.cellular.operators.selection

import genetic.ga.core.operators.selection.comparable.selectionBest
import genetic.ga.core.operators.selection.comparable.selectionWorst
import genetic.ga.cellular.lifecycle.CellLifecycle
import genetic.utils.fillArrayChromosomeBySubArray

fun <V, F> CellLifecycle<V, F>.selBest(
    count: Int,
) = fillArrayChromosomeBySubArray(neighbours, selectionBest(neighbours, count))

fun <V, F> CellLifecycle<V, F>.selWorst(
    count: Int,
) = fillArrayChromosomeBySubArray(neighbours, selectionWorst(neighbours, count))
