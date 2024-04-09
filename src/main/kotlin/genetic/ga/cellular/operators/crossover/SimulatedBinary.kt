package genetic.ga.cellular.operators.crossover

import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle
import genetic.ga.base_operators.crossover.simulated_binary.crossoverSimulatedBinaryDoubleArray

fun <F> SimpleClusterCellLifecycle<DoubleArray, F>.crossoverSimulatedBinaryDoubleArray(
    cellularGABuilder: CellularGABuilder<DoubleArray, F>,
    chance: Double,
    eta: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverSimulatedBinaryDoubleArray(chromosome1, chromosome2, eta, random)
}
