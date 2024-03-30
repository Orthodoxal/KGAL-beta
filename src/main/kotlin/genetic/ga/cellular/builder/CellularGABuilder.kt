package genetic.ga.cellular.builder

import genetic.ga.GABuilder
import genetic.ga.cellular.lifecycle.CellularGALifecycle
import genetic.ga.cellular.neighbourhood.CellularNeighbourhood
import genetic.ga.cellular.type.CellularType

interface CellularGABuilder<V, F> : GABuilder<V, F> {
    var neighbourhood: CellularNeighbourhood
    var cellularType: CellularType
    //var dimensions: IntArray

    fun CellularGABuilder<V, F>.lifecycle(
        before: (suspend CellularGALifecycle<V, F>.() -> Unit)? = null,
        after: (suspend CellularGALifecycle<V, F>.() -> Unit)? = null,
        lifecycle: suspend CellularGALifecycle<V, F>.() -> Unit,
    )
}
