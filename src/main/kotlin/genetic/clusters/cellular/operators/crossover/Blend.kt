package genetic.clusters.cellular.operators.crossover

import genetic.clusters.base.operators.crossover.blend.crossoverBlend
import genetic.clusters.cellular.lifecycle.CellLifecycle

fun <F> CellLifecycle<DoubleArray, F>.cxBlendDoubleArray(
    chance: Double,
    alpha: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverBlend(chromosome1.value, chromosome2.value, alpha, random)
}

fun <F> CellLifecycle<FloatArray, F>.cxBlendFloatArray(
    chance: Double,
    alpha: Float,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverBlend(chromosome1.value, chromosome2.value, alpha, random)
}
