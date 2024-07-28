package genetic.clusters.panmictic.operators.crossover

import genetic.clusters.base.operators.crossover.simulated_binary_bounded.crossoverSimulatedBinaryBounded
import genetic.clusters.panmictic.PanmicticLifecycle

suspend fun <F> PanmicticLifecycle<DoubleArray, F>.cxSimulatedBinaryBoundedDoubleArray(
    chance: Double,
    eta: Double,
    low: Double,
    up: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverSimulatedBinaryBounded(chromosome1.value, chromosome2.value, eta, low, up, random)
}
