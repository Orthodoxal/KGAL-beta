package genetic.ga.panmictic.operators.crossover

import genetic.ga.core.operators.crossover.simulated_binary.crossoverSimulatedBinary
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

@JvmName("cxSimulatedBinaryDoubleArray")
suspend fun <F> PanmicticLifecycle<DoubleArray, F>.cxSimulatedBinary(
    chance: Double,
    eta: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverSimulatedBinary(chromosome1.value, chromosome2.value, eta, random)
}
