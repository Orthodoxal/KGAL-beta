package genetic.clusters.panmictic.operators.crossover

import genetic.clusters.base.operators.crossover.blend.crossoverBlend
import genetic.clusters.panmictic.PanmicticLifecycle

@JvmName("cxBlendDoubleArray")
suspend fun <F> PanmicticLifecycle<DoubleArray, F>.cxBlend(
    chance: Double,
    alpha: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverBlend(chromosome1.value, chromosome2.value, alpha, random)
}

@JvmName("cxBlendFloatArray")
suspend fun <F> PanmicticLifecycle<FloatArray, F>.cxBlend(
    chance: Double,
    alpha: Float,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverBlend(chromosome1.value, chromosome2.value, alpha, random)
}
