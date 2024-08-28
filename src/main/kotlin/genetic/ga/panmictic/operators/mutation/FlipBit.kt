package genetic.ga.panmictic.operators.mutation

import genetic.ga.core.operators.mutation.flip_bit.mutationFlipBit
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

@JvmName("mutFlipBitBooleanArray")
suspend fun <F> PanmicticLifecycle<BooleanArray, F>.mutFlipBit(
    chance: Double,
    flipBitChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(chance, onlySingleRun) { chromosome ->
    mutationFlipBit(chromosome.value, flipBitChance, random)
}

@JvmName("mutFlipBitIntArray")
suspend fun <F> PanmicticLifecycle<IntArray, F>.mutFlipBit(
    chance: Double,
    flipBitChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(chance, onlySingleRun) { chromosome ->
    mutationFlipBit(chromosome.value, flipBitChance, random)
}
