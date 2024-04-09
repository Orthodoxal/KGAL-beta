package genetic.ga.panmictic.operators.crossover

import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle
import genetic.ga.base_operators.crossover.ordered.crossoverOrderedIntArray
import genetic.ga.panmictic.builder.PanmicticGABuilder

suspend fun <F> SimpleClusterCellLifecycle<IntArray, F>.crossoverOrderedIntArray(
    panmicticGABuilder: PanmicticGABuilder<IntArray, F>,
    chance: Double,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOrderedIntArray(chromosome1, chromosome2, random)
}
