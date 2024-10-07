package genetic.ga.panmictic.operators.selection

import genetic.ga.core.operators.selection.comparable.selectionBest
import genetic.ga.core.operators.selection.comparable.selectionWorst
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle
import genetic.ga.core.population.get
import genetic.utils.fillArrayChromosomeBySubArray

fun <V, F> PanmicticLifecycle<V, F>.selBest(
    count: Int,
) = fillArrayChromosomeBySubArray(population.get(), selectionBest(population.get(), count))

fun <V, F> PanmicticLifecycle<V, F>.selWorst(
    count: Int,
) = fillArrayChromosomeBySubArray(population.get(), selectionWorst(population.get(), count))
