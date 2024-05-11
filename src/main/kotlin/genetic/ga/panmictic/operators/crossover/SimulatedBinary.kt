package genetic.ga.panmictic.operators.crossover

import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.base_operators.crossover.simulated_binary.crossoverSimulatedBinaryDoubleArray
import genetic.ga.panmictic.builder.PanmicticGABuilder

suspend fun <F> SimpleClusterLifecycle<DoubleArray, F>.crossoverSimulatedBinaryDoubleArray(
    panmicticGABuilder: PanmicticGABuilder<DoubleArray, F>,
    chance: Double,
    eta: Double,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverSimulatedBinaryDoubleArray(chromosome1.value, chromosome2.value, eta, random)
}
