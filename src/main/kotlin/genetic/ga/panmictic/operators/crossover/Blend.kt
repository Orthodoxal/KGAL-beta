package genetic.ga.panmictic.operators.crossover

import genetic.ga.core.operators.crossover.blend.crossoverBlend
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

@JvmName("cxBlendDoubleArray")
suspend fun <F> PanmicticLifecycle<DoubleArray, F>.cxBlend(
    chance: Double,
    alpha: Double,
    parallelismLimit: Int = parallelismConfig.workersCount,
    crossoverType: CrossoverType = CrossoverType.Iterative,
) = crossover(chance, parallelismLimit, crossoverType) { chromosome1, chromosome2, random ->
    crossoverBlend(chromosome1.value, chromosome2.value, alpha, random)
}

@JvmName("cxBlendFloatArray")
suspend fun <F> PanmicticLifecycle<FloatArray, F>.cxBlend(
    chance: Double,
    alpha: Float,
    parallelismLimit: Int = parallelismConfig.workersCount,
    crossoverType: CrossoverType = CrossoverType.Iterative,
) = crossover(chance, parallelismLimit, crossoverType) { chromosome1, chromosome2, random ->
    crossoverBlend(chromosome1.value, chromosome2.value, alpha, random)
}
