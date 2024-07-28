package genetic.clusters.panmictic.operators.crossover

import genetic.clusters.base.operators.crossover.blend.crossoverBlend
import genetic.clusters.panmictic.PanmicticLifecycle

suspend fun <F> PanmicticLifecycle<DoubleArray, F>.cxBlendDoubleArray(
    chance: Double,
    alpha: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverBlend(chromosome1.value, chromosome2.value, alpha, random)
}

suspend fun <F> PanmicticLifecycle<FloatArray, F>.cxBlendFloatArray(
    chance: Double,
    alpha: Float,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverBlend(chromosome1.value, chromosome2.value, alpha, random)
}
