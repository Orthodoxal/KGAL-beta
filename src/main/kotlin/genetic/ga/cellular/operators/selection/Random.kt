package genetic.ga.cellular.operators.selection

import genetic.ga.core.operators.selection.random.selectionRandom
import genetic.ga.cellular.lifecycle.CellLifecycle

fun <V, F> CellLifecycle<V, F>.selRandom() = selection { source -> selectionRandom(source, random) }
