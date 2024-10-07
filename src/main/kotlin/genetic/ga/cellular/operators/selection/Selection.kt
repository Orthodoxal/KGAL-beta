package genetic.ga.cellular.operators.selection

import genetic.chromosome.Chromosome
import genetic.ga.cellular.lifecycle.CellLifecycle

inline fun <V, F> CellLifecycle<V, F>.selection(
    selection: (source: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) {
    val chromosome = selection(neighbours)
    neighbours[0] = chromosome
}
