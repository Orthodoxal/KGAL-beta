package genetic.clusters.cellular.lifecycle

import genetic.clusters.base.lifecycle.AbstractClusterLifecycle
import genetic.clusters.base.lifecycle.ClusterLifecycle
import genetic.clusters.cellular.CellularCluster
import genetic.clusters.cellular.isSingleRun
import genetic.ga.cellular.type.CellularType

interface CellularLifecycle<V, F> : ClusterLifecycle<V, F> {
    var elitism: Boolean
    var cellularType: CellularType
    var neighboursIndicesCache: Array<IntArray>
    val isSingleRun: Boolean

    companion object {
        // FIXME добавить evaluation
        val BASE_BEFORE_LIFECYCLE: suspend CellularLifecycle<*, *>.() -> Unit = { }
        val BASE_AFTER_LIFECYCLE: suspend CellularLifecycle<*, *>.() -> Unit = { }
    }
}

internal class CellularLifecycleInstance<V, F>(
    private val cellularCluster: CellularCluster<V, F>,
) : AbstractClusterLifecycle<V, F>(cellularCluster), CellularLifecycle<V, F> {

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

    override val isSingleRun: Boolean get() = cellularCluster.isSingleRun
}
