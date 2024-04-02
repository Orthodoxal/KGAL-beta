package genetic.ga.cellular.operators.selection

import genetic.chromosome.Chromosome
import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle

inline fun <V, F> SimpleClusterCellLifecycle<V, F>.selection(
    cellularGABuilder: CellularGABuilder<V, F>,
    selection: (source: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) {
    val chromosome = selection(neighbours)
    neighbours[0] = chromosome
}
