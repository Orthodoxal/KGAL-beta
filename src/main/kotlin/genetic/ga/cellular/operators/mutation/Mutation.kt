package genetic.ga.cellular.operators.mutation

import genetic.chromosome.Chromosome
import genetic.ga.cellular.lifecycle.CellLifecycle
import genetic.utils.randomByChance

inline fun <V, F> CellLifecycle<V, F>.mutation(
    chance: Double,
    mutation: (chromosome: Chromosome<V, F>) -> Unit,
) {
    randomByChance(chance) { mutation(cellChromosome) }
    randomByChance(chance) { mutation(neighbours[0]) }
}
