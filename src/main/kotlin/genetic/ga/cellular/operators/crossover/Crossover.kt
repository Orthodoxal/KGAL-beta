package genetic.ga.cellular.operators.crossover

import genetic.chromosome.Chromosome
import genetic.ga.cellular.lifecycle.CellLifecycle
import genetic.utils.randomByChance

inline fun <V, F> CellLifecycle<V, F>.crossover(
    chance: Double,
    crossover: (chromosome1: Chromosome<V, F>, chromosome2: Chromosome<V, F>) -> Unit,
) = randomByChance(chance) {
    val parent1 = cellChromosome
    val parent2 = neighbours[0]
    if (parent1 != parent2) crossover(parent1, parent2)
}
