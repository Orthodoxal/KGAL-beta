package genetic.ga.panmictic.operators.crossover

import genetic.ga.core.operators.crossover.ordered.crossoverOrdered
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

@JvmName("cxOrderedIntArray")
suspend fun <F> PanmicticLifecycle<IntArray, F>.cxOrdered(
    chance: Double,
    parallelismLimit: Int = parallelismConfig.workersCount,
    crossoverType: CrossoverType = CrossoverType.Iterative,
) = crossover(chance, parallelismLimit, crossoverType) { chromosome1, chromosome2, random ->
    crossoverOrdered(chromosome1.value, chromosome2.value, random)
}
