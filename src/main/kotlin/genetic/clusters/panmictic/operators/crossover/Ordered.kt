package genetic.clusters.panmictic.operators.crossover

import genetic.clusters.base.operators.crossover.ordered.crossoverOrdered
import genetic.clusters.panmictic.PanmicticLifecycle

@JvmName("cxOrderedIntArray")
suspend fun <F> PanmicticLifecycle<IntArray, F>.cxOrdered(
    chance: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOrdered(chromosome1.value, chromosome2.value, random)
}
