package genetic.ga.panmictic.operators.mutation

import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.panmictic.builder.PanmicticGABuilder
import genetic.ga.base_operators.mutation.flip_bit.mutationFlipBitBooleanArray
import genetic.ga.base_operators.mutation.flip_bit.mutationFlipBitIntArray

suspend fun <F> SimpleClusterLifecycle<BooleanArray, F>.mutationFlipBitBooleanArray(
    panmicticGABuilder: PanmicticGABuilder<BooleanArray, F>,
    chance: Double = 0.01,
    onlySingleRun: Boolean = false,
) = mutation(panmicticGABuilder, 0.1, onlySingleRun) { chromosome ->
    mutationFlipBitBooleanArray(chromosome, chance, random)
}

suspend fun <F> SimpleClusterLifecycle<IntArray, F>.mutationFlipBitIntArray(
    panmicticGABuilder: PanmicticGABuilder<IntArray, F>,
    chance: Double = 0.01,
    onlySingleRun: Boolean = false,
) = mutation(panmicticGABuilder, 0.1, onlySingleRun) { chromosome ->
    mutationFlipBitIntArray(chromosome, chance, random)
}
