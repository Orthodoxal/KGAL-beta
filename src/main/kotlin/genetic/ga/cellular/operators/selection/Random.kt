package genetic.ga.cellular.operators.selection

import genetic.ga.base_operators.selection.random.selectionRandom
import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle

fun <V, F> SimpleClusterCellLifecycle<V, F>.selectionRandom(
    cellularGABuilder: CellularGABuilder<V, F>,
) = selection(cellularGABuilder) { source -> selectionRandom(source, random) }
