package genetic.ga.cellular.lifecycle

import genetic.ga.core.lifecycle.AbstractGALifecycle
import genetic.ga.core.lifecycle.GALifecycle
import genetic.ga.cellular.CellularGA
import genetic.ga.cellular.type.CellularType
import kotlinx.coroutines.CoroutineDispatcher

interface CellularLifecycle<V, F> : GALifecycle<V, F> {
    var elitism: Boolean
    var cellularType: CellularType
    var neighboursIndicesCache: Array<IntArray>
}

internal class CellularLifecycleInstance<V, F>(
    private val cellularCluster: CellularGA<V, F>,
) : AbstractGALifecycle<V, F>(cellularCluster), CellularLifecycle<V, F> {

    override var elitism: Boolean
        get() = cellularCluster.elitism
        set(value) {
            cellularCluster.elitism = value
        }

    override var cellularType: CellularType
        get() = cellularCluster.cellularType
        set(value) {
            cellularCluster.cellularType = value
        }

    override var neighboursIndicesCache: Array<IntArray>
        get() = cellularCluster.neighboursIndicesCache
        set(value) {
            cellularCluster.neighboursIndicesCache = value
        }

    override val extraDispatchers: Array<CoroutineDispatcher>? get() = cellularCluster.extraDispatchers
}
