package genetic.clusters.panmictic.operators.selection

import genetic.clusters.base.operators.selection.comparable.selectionBest
import genetic.clusters.base.operators.selection.comparable.selectionWorst
import genetic.clusters.panmictic.PanmicticLifecycle
import genetic.utils.clusters.get
import genetic.utils.fillArrayChromosomeBySubArray

fun <V, F> PanmicticLifecycle<V, F>.selBest(
    count: Int,
) = fillArrayChromosomeBySubArray(population.get(), selectionBest(population.get(), count))

fun <V, F> PanmicticLifecycle<V, F>.selWorst(
    count: Int,
) = fillArrayChromosomeBySubArray(population.get(), selectionWorst(population.get(), count))
