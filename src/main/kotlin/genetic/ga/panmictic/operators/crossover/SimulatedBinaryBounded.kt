package genetic.ga.panmictic.operators.crossover

import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.base_operators.crossover.simulated_binary.crossoverSimulatedBinaryDoubleArray
import genetic.ga.base_operators.crossover.simulated_binary_bounded.crossoverSimulatedBinaryBounded
import genetic.ga.panmictic.builder.PanmicticGABuilder

suspend fun <F> SimpleClusterLifecycle<DoubleArray, F>.crossoverSimulatedBinaryBoundedDoubleArray(
    panmicticGABuilder: PanmicticGABuilder<DoubleArray, F>,
    chance: Double,
    eta: Double,
    low: Double,
    up: Double,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverSimulatedBinaryBounded(chromosome1.value, chromosome2.value, eta, low, up, random)
}
