package genetic.ga.cellular.operators.crossover

import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle
import genetic.ga.base_operators.crossover.blend.crossoverBlendDoubleArray
import genetic.ga.base_operators.crossover.blend.crossoverBlendFloatArray

fun <F> SimpleClusterCellLifecycle<DoubleArray, F>.crossoverBlendDoubleArray(
    cellularGABuilder: CellularGABuilder<DoubleArray, F>,
    chance: Double,
    alpha: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverBlendDoubleArray(chromosome1.value, chromosome2.value, alpha, random)
}

fun <F> SimpleClusterCellLifecycle<FloatArray, F>.crossoverBlendFloatArray(
    cellularGABuilder: CellularGABuilder<FloatArray, F>,
    chance: Double,
    alpha: Float,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverBlendFloatArray(chromosome1.value, chromosome2.value, alpha, random)
}
