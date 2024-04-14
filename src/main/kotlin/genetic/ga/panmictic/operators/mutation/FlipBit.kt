package genetic.ga.panmictic.operators.mutation

import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.panmictic.builder.PanmicticGABuilder
import genetic.ga.base_operators.mutation.flip_bit.mutationFlipBitBooleanArray
import genetic.ga.base_operators.mutation.flip_bit.mutationFlipBitIntArray

suspend fun <F> SimpleClusterLifecycle<BooleanArray, F>.mutationFlipBitBooleanArray(
    panmicticGABuilder: PanmicticGABuilder<BooleanArray, F>,
    mutationChance: Double,
    mutationFlipBitChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(panmicticGABuilder, mutationChance, onlySingleRun) { chromosome ->
    mutationFlipBitBooleanArray(chromosome.value, mutationFlipBitChance, random)
}

suspend fun <F> SimpleClusterLifecycle<IntArray, F>.mutationFlipBitIntArray(
    panmicticGABuilder: PanmicticGABuilder<IntArray, F>,
    mutationChance: Double,
    mutationFlipBitChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(panmicticGABuilder, mutationChance, onlySingleRun) { chromosome ->
    mutationFlipBitIntArray(chromosome.value, mutationFlipBitChance, random)
}
