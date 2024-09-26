package genetic.ga.panmictic.operators.crossover

import genetic.ga.core.operators.crossover.blend.crossoverBlend
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

@JvmName("cxBlendDoubleArray")
suspend fun <F> PanmicticLifecycle<DoubleArray, F>.cxBlend(
    chance: Double,
    alpha: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
    crossoverType: CrossoverType = CrossoverType.Iterative,
) = crossover(chance, parallelWorkersLimit, crossoverType) { chromosome1, chromosome2 ->
    crossoverBlend(chromosome1.value, chromosome2.value, alpha, random)
}

@JvmName("cxBlendFloatArray")
suspend fun <F> PanmicticLifecycle<FloatArray, F>.cxBlend(
    chance: Double,
    alpha: Float,
    parallelWorkersLimit: Int = parallelismConfig.count,
    crossoverType: CrossoverType = CrossoverType.Iterative,
) = crossover(chance, parallelWorkersLimit, crossoverType) { chromosome1, chromosome2 ->
    crossoverBlend(chromosome1.value, chromosome2.value, alpha, random)
}
