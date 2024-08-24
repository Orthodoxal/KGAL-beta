package genetic.clusters.cellular.operators.selection

import genetic.clusters.base.operators.selection.random.selectionRandom
import genetic.clusters.cellular.lifecycle.CellLifecycle

fun <V, F> CellLifecycle<V, F>.selRandom() = selection { source -> selectionRandom(source, random) }
