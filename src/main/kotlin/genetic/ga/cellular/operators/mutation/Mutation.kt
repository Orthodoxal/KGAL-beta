package genetic.ga.cellular.operators.mutation

import genetic.chromosome.Chromosome
import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle
import genetic.utils.randomByChance

inline fun <V, F> SimpleClusterCellLifecycle<V, F>.mutation(
    cellularGABuilder: CellularGABuilder<V, F>,
    chance: Double,
    mutation: (chromosome: Chromosome<V, F>) -> Unit,
) {
    randomByChance(chance) { mutation(cellChromosome) }
    randomByChance(chance) { mutation(neighbours[0]) }
}
