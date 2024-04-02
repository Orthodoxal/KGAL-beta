package genetic.ga.cellular.operators.mutation

import genetic.ga.base_operators.mutation.flip_bit.mutationFlipBitBooleanArray
import genetic.ga.base_operators.mutation.flip_bit.mutationFlipBitIntArray
import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle

fun <F> SimpleClusterCellLifecycle<BooleanArray, F>.mutationFlipBitBooleanArray(
    cellularGABuilder: CellularGABuilder<BooleanArray, F>,
    mutationChance: Double,
    mutationFlipBitChance: Double,
) = mutation(cellularGABuilder, mutationChance) { chromosome ->
    mutationFlipBitBooleanArray(chromosome, mutationFlipBitChance, random)
}

fun <F> SimpleClusterCellLifecycle<IntArray, F>.mutationFlipBitIntArray(
    cellularGABuilder: CellularGABuilder<IntArray, F>,
    mutationChance: Double,
    mutationFlipBitChance: Double,
) = mutation(cellularGABuilder, mutationChance) { chromosome ->
    mutationFlipBitIntArray(chromosome, mutationFlipBitChance, random)
}
