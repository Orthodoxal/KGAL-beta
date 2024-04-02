package genetic.ga.cellular.operators.crossover

import genetic.chromosome.Chromosome
import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle
import genetic.utils.randomByChance

inline fun <V, F> SimpleClusterCellLifecycle<V, F>.crossover(
    cellularGABuilder: CellularGABuilder<V, F>,
    chance: Double,
    crossover: (chromosome1: Chromosome<V, F>, chromosome2: Chromosome<V, F>) -> Unit,
) = randomByChance(chance) {
    val parent1 = cellChromosome
    val parent2 = neighbours[0]
    if (parent1 != parent2) crossover(parent1, parent2)
}
