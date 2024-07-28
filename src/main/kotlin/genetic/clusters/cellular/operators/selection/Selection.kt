package genetic.clusters.cellular.operators.selection

import genetic.chromosome.Chromosome
import genetic.clusters.cellular.lifecycle.CellLifecycle

inline fun <V, F> CellLifecycle<V, F>.selection(
    selection: (source: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) {
    val chromosome = selection(neighbours)
    neighbours[0] = chromosome
}
