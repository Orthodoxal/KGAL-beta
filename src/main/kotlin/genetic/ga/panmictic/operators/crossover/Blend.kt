package genetic.ga.panmictic.operators.crossover

import genetic.ga.base_operators.crossover.blend.crossoverBlendDoubleArray
import genetic.ga.base_operators.crossover.blend.crossoverBlendFloatArray
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle
import genetic.ga.panmictic.builder.PanmicticGABuilder

suspend fun <F> SimpleClusterCellLifecycle<DoubleArray, F>.crossoverBlendDoubleArray(
    panmicticGABuilder: PanmicticGABuilder<DoubleArray, F>,
    chance: Double,
    alpha: Double,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverBlendDoubleArray(chromosome1.value, chromosome2.value, alpha, random)
}

suspend fun <F> SimpleClusterCellLifecycle<FloatArray, F>.crossoverBlendFloatArray(
    panmicticGABuilder: PanmicticGABuilder<FloatArray, F>,
    chance: Double,
    alpha: Float,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverBlendFloatArray(chromosome1.value, chromosome2.value, alpha, random)
}
