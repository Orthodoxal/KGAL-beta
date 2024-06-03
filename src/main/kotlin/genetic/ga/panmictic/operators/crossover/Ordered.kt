package genetic.ga.panmictic.operators.crossover

import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.base_operators.crossover.ordered.crossoverOrderedIntArray
import genetic.ga.panmictic.builder.PanmicticGABuilder

suspend fun <F> SimpleClusterLifecycle<IntArray, F>.crossoverOrderedIntArray(
    panmicticGABuilder: PanmicticGABuilder<IntArray, F>,
    chance: Double,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOrderedIntArray(chromosome1.value, chromosome2.value, random)
}
