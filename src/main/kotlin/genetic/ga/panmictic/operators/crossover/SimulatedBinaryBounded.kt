package genetic.ga.panmictic.operators.crossover

import genetic.ga.core.operators.crossover.simulated_binary_bounded.crossoverSimulatedBinaryBounded
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

@JvmName("cxSimulatedBinaryBoundedDoubleArray")
suspend fun <F> PanmicticLifecycle<DoubleArray, F>.cxSimulatedBinaryBounded(
    chance: Double,
    eta: Double,
    low: Double,
    up: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverSimulatedBinaryBounded(chromosome1.value, chromosome2.value, eta, low, up, random)
}
