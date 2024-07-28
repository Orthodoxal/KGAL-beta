package genetic.clusters.panmictic.operators.mutation

import genetic.clusters.base.operators.mutation.flip_bit.mutationFlipBit
import genetic.clusters.panmictic.PanmicticLifecycle

suspend fun <F> PanmicticLifecycle<BooleanArray, F>.mutFlipBitBooleanArray(
    mutationChance: Double,
    mutationFlipBitChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationFlipBit(chromosome.value, mutationFlipBitChance, random)
}

suspend fun <F> PanmicticLifecycle<IntArray, F>.mutFlipBitIntArray(
    mutationChance: Double,
    mutationFlipBitChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationFlipBit(chromosome.value, mutationFlipBitChance, random)
}
