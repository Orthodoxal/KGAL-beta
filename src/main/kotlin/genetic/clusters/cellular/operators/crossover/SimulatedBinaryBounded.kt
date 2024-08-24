package genetic.clusters.cellular.operators.crossover

import genetic.clusters.base.operators.crossover.simulated_binary_bounded.crossoverSimulatedBinaryBounded
import genetic.clusters.cellular.lifecycle.CellLifecycle

@JvmName("cxSimulatedBinaryBoundedDoubleArray")
fun <F> CellLifecycle<DoubleArray, F>.cxSimulatedBinaryBounded(
    chance: Double,
    eta: Double,
    low: Double,
    up: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverSimulatedBinaryBounded(chromosome1.value, chromosome2.value, eta, low, up, random)
}
