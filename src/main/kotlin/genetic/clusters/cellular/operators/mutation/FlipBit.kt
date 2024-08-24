package genetic.clusters.cellular.operators.mutation

import genetic.clusters.base.operators.mutation.flip_bit.mutationFlipBit
import genetic.clusters.cellular.lifecycle.CellLifecycle

@JvmName("mutFlipBitBooleanArray")
fun <F> CellLifecycle<BooleanArray, F>.mutFlipBit(
    mutationChance: Double,
    mutationFlipBitChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationFlipBit(chromosome.value, mutationFlipBitChance, random)
}

@JvmName("mutFlipBitIntArray")
fun <F> CellLifecycle<IntArray, F>.mutFlipBit(
    mutationChance: Double,
    mutationFlipBitChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationFlipBit(chromosome.value, mutationFlipBitChance, random)
}
