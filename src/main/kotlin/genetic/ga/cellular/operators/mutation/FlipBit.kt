package genetic.ga.cellular.operators.mutation

import genetic.ga.core.operators.mutation.flip_bit.mutationFlipBit
import genetic.ga.cellular.lifecycle.CellLifecycle

@JvmName("mutFlipBitBooleanArray")
fun <F> CellLifecycle<BooleanArray, F>.mutFlipBit(
    chance: Double,
    flipBitChance: Double,
) = mutation(chance) { chromosome ->
    mutationFlipBit(chromosome.value, flipBitChance, random)
}

@JvmName("mutFlipBitIntArray")
fun <F> CellLifecycle<IntArray, F>.mutFlipBit(
    chance: Double,
    flipBitChance: Double,
) = mutation(chance) { chromosome ->
    mutationFlipBit(chromosome.value, flipBitChance, random)
}
