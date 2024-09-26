package genetic.ga.panmictic.operators.crossover

import genetic.ga.core.operators.crossover.ordered.crossoverOrdered
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

@JvmName("cxOrderedIntArray")
suspend fun <F> PanmicticLifecycle<IntArray, F>.cxOrdered(
    chance: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
    crossoverType: CrossoverType = CrossoverType.Iterative,
) = crossover(chance, parallelWorkersLimit, crossoverType) { chromosome1, chromosome2 ->
    crossoverOrdered(chromosome1.value, chromosome2.value, random)
}
