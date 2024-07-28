package genetic.clusters.cellular.operators.crossover

import genetic.clusters.base.operators.crossover.simulated_binary.crossoverSimulatedBinary
import genetic.clusters.cellular.lifecycle.CellLifecycle

fun <F> CellLifecycle<DoubleArray, F>.cxSimulatedBinaryDoubleArray(
    chance: Double,
    eta: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverSimulatedBinary(chromosome1.value, chromosome2.value, eta, random)
}
