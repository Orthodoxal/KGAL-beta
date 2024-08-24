package genetic.clusters.panmictic.operators.mutation

import genetic.clusters.base.operators.mutation.flip_bit.mutationFlipBit
import genetic.clusters.panmictic.PanmicticLifecycle

@JvmName("mutFlipBitBooleanArray")
suspend fun <F> PanmicticLifecycle<BooleanArray, F>.mutFlipBit(
    mutationChance: Double,
    mutationFlipBitChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationFlipBit(chromosome.value, mutationFlipBitChance, random)
}

@JvmName("mutFlipBitIntArray")
suspend fun <F> PanmicticLifecycle<IntArray, F>.mutFlipBit(
    mutationChance: Double,
    mutationFlipBitChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationFlipBit(chromosome.value, mutationFlipBitChance, random)
}
