package genetic.clusters.panmictic.operators.crossover

import genetic.clusters.base.operators.crossover.simulated_binary.crossoverSimulatedBinary
import genetic.clusters.panmictic.PanmicticLifecycle

suspend fun <F> PanmicticLifecycle<DoubleArray, F>.cxSimulatedBinaryDoubleArray(
    chance: Double,
    eta: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverSimulatedBinary(chromosome1.value, chromosome2.value, eta, random)
}
