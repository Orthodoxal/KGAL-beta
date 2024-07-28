package genetic.clusters.cellular.operators.mutation

import genetic.clusters.base.operators.mutation.flip_bit.mutationFlipBit
import genetic.clusters.cellular.lifecycle.CellLifecycle

fun <F> CellLifecycle<BooleanArray, F>.mutFlipBitBooleanArray(
    mutationChance: Double,
    mutationFlipBitChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationFlipBit(chromosome.value, mutationFlipBitChance, random)
}

fun <F> CellLifecycle<IntArray, F>.mutFlipBitIntArray(
    mutationChance: Double,
    mutationFlipBitChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationFlipBit(chromosome.value, mutationFlipBitChance, random)
}
