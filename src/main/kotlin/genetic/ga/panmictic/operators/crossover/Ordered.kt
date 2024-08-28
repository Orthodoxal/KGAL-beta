package genetic.ga.panmictic.operators.crossover

import genetic.ga.core.operators.crossover.ordered.crossoverOrdered
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

@JvmName("cxOrderedIntArray")
suspend fun <F> PanmicticLifecycle<IntArray, F>.cxOrdered(
    chance: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOrdered(chromosome1.value, chromosome2.value, random)
}
