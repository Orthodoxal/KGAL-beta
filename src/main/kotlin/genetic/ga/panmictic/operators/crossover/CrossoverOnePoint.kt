package genetic.ga.panmictic.operators.crossover

import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.base_operators.crossover.one_point.crossoverOnePointIntArray
import genetic.ga.base_operators.crossover.one_point.crossoverOnePointBooleanArray
import genetic.ga.panmictic.builder.PanmicticGABuilder

fun <F> SimpleClusterLifecycle<BooleanArray, F>.crossoverOnePointBooleanArray(
    panmicticGABuilder: PanmicticGABuilder<BooleanArray, F>,
    chance: Double = 0.9,
) = crossover(panmicticGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverOnePointBooleanArray(chromosome1, chromosome2)
}

fun <F> SimpleClusterLifecycle<IntArray, F>.crossoverOnePointIntArray(
    panmicticGABuilder: PanmicticGABuilder<IntArray, F>,
    chance: Double = 0.9,
) = crossover(panmicticGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverOnePointIntArray(chromosome1, chromosome2)
}
