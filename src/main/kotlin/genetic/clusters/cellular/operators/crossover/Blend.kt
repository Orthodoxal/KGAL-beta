package genetic.clusters.cellular.operators.crossover

import genetic.clusters.base.operators.crossover.blend.crossoverBlend
import genetic.clusters.cellular.lifecycle.CellLifecycle

@JvmName("cxBlendDoubleArray")
fun <F> CellLifecycle<DoubleArray, F>.cxBlend(
    chance: Double,
    alpha: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverBlend(chromosome1.value, chromosome2.value, alpha, random)
}

@JvmName("cxBlendFloatArray")
fun <F> CellLifecycle<FloatArray, F>.cxBlend(
    chance: Double,
    alpha: Float,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverBlend(chromosome1.value, chromosome2.value, alpha, random)
}
