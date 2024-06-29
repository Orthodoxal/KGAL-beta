package genetic.ga.cellular.operators.crossover

import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle
import genetic.ga.base_operators.crossover.simulated_binary.crossoverSimulatedBinaryDoubleArray
import genetic.ga.base_operators.crossover.simulated_binary_bounded.crossoverSimulatedBinaryBounded

fun <F> SimpleClusterCellLifecycle<DoubleArray, F>.crossoverSimulatedBinaryBoundedDoubleArray(
    cellularGABuilder: CellularGABuilder<DoubleArray, F>,
    chance: Double,
    eta: Double,
    low: Double,
    up: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverSimulatedBinaryBounded(chromosome1.value, chromosome2.value, eta, low, up, random)
}
