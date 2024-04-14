package genetic.ga.panmictic.operators.crossover

import genetic.ga.base_operators.crossover.simulated_binary.crossoverSimulatedBinaryDoubleArray
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle
import genetic.ga.panmictic.builder.PanmicticGABuilder

suspend fun <F> SimpleClusterCellLifecycle<DoubleArray, F>.crossoverSimulatedBinaryDoubleArray(
    panmicticGABuilder: PanmicticGABuilder<DoubleArray, F>,
    chance: Double,
    eta: Double,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverSimulatedBinaryDoubleArray(chromosome1.value, chromosome2.value, eta, random)
}
