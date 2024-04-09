package genetic.ga.cellular.operators.crossover

import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle
import genetic.ga.base_operators.crossover.ordered.crossoverOrderedIntArray

fun <F> SimpleClusterCellLifecycle<IntArray, F>.crossoverOrderedIntArray(
    cellularGABuilder: CellularGABuilder<IntArray, F>,
    chance: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverOrderedIntArray(chromosome1, chromosome2, random)
}
