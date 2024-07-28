package genetic.clusters.cellular.operators.crossover

import genetic.clusters.base.operators.crossover.simulated_binary_bounded.crossoverSimulatedBinaryBounded
import genetic.clusters.cellular.lifecycle.CellLifecycle

fun <F> CellLifecycle<DoubleArray, F>.cxSimulatedBinaryBoundedDoubleArray(
    chance: Double,
    eta: Double,
    low: Double,
    up: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverSimulatedBinaryBounded(chromosome1.value, chromosome2.value, eta, low, up, random)
}
