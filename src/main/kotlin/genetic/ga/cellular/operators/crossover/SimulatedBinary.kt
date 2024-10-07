package genetic.ga.cellular.operators.crossover

import genetic.ga.core.operators.crossover.simulated_binary.crossoverSimulatedBinary
import genetic.ga.cellular.lifecycle.CellLifecycle

@JvmName("cxSimulatedBinaryDoubleArray")
fun <F> CellLifecycle<DoubleArray, F>.cxSimulatedBinary(
    chance: Double,
    eta: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverSimulatedBinary(chromosome1.value, chromosome2.value, eta, random)
}
