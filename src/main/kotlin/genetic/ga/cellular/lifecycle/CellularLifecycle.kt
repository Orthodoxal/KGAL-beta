package genetic.ga.cellular.lifecycle

import genetic.ga.cellular.CellularGA
import genetic.ga.cellular.neighborhood.CellularNeighborhood
import genetic.ga.cellular.type.CellularType
import genetic.ga.cellular.utils.Dimens
import genetic.ga.core.lifecycle.BaseLifecycle
import genetic.ga.core.lifecycle.Lifecycle

interface CellularLifecycle<V, F> : Lifecycle<V, F> {
    var elitism: Boolean
    var cellularType: CellularType
    var neighborhood: CellularNeighborhood
    val dimens: Dimens
    var neighboursIndicesCache: Array<IntArray>
}

internal class CellularLifecycleInstance<V, F>(
    private val cellularGA: CellularGA<V, F>,
) : CellularLifecycle<V, F>, Lifecycle<V, F> by BaseLifecycle(cellularGA) {

    override var elitism: Boolean
        get() = cellularGA.elitism
        set(value) {
            cellularGA.elitism = value
        }

    override var cellularType: CellularType
        get() = cellularGA.cellularType
        set(value) {
            cellularGA.cellularType = value
        }

    override var neighborhood: CellularNeighborhood
        get() = cellularGA.neighborhood
        set(value) {
            cellularGA.neighborhood = value
        }

    override val dimens: Dimens
        get() = cellularGA.population.dimens

    override var neighboursIndicesCache: Array<IntArray> = emptyArray()
}
