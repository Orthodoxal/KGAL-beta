package genetic.ga.panmictic.operators.mutation

import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.panmictic.builder.PanmicticGABuilder
import genetic.ga.base_operators.mutation.flip_bit.mutationFlipBitBooleanArray
import genetic.ga.base_operators.mutation.flip_bit.mutationFlipBitIntArray

fun <F> SimpleClusterLifecycle<BooleanArray, F>.mutationFlipBitBooleanArray(
    panmicticGABuilder: PanmicticGABuilder<BooleanArray, F>,
    chance: Double = 0.1,
) = mutation(panmicticGABuilder, 1.0) { chromosome ->
    mutationFlipBitBooleanArray(chromosome, chance)
}

fun <F> SimpleClusterLifecycle<IntArray, F>.mutationFlipBitIntArray(
    panmicticGABuilder: PanmicticGABuilder<IntArray, F>,
    chance: Double = 0.1,
) = mutation(panmicticGABuilder, 1.0) { chromosome ->
    mutationFlipBitIntArray(chromosome, chance)
}
