package genetic.clusters.distributed

import genetic.clusters.base.Cluster
import genetic.clusters.base.builder.ClusterBuilder
import genetic.clusters.cellular.CellularCluster
import genetic.clusters.cellular.CellularClusterBuilder
import genetic.clusters.distributed.lifecycle.DistributedLifecycle
import genetic.clusters.panmictic.PanmicticCluster
import genetic.clusters.panmictic.PanmicticClusterBuilder
import genetic.utils.loop

interface DistributedClusterBuilder<V, F> : ClusterBuilder<V, F, DistributedLifecycle<V, F>> {
    fun addCluster(cluster: Cluster<V, F>): Cluster<V, F>
    operator fun Cluster<V, F>.unaryPlus(): Cluster<V, F> = addCluster(this)
}

inline fun <V, F> DistributedClusterBuilder<V, F>.pGA(
    builder: PanmicticClusterBuilder<V, F>.() -> Unit
) = addCluster(PanmicticCluster<V, F>().apply(builder))

inline fun <V, F> DistributedClusterBuilder<V, F>.pGAs(
    count: Int,
    builder: PanmicticClusterBuilder<V, F>.(index: Int) -> Unit
) = loop(start = 0, end = count) { index ->
    addCluster(PanmicticCluster<V, F>().apply { builder(index) })
}

inline fun <V, F> DistributedClusterBuilder<V, F>.cGA(
    builder: CellularClusterBuilder<V, F>.() -> Unit
) = addCluster(CellularCluster<V, F>().apply(builder))

inline fun <V, F> DistributedClusterBuilder<V, F>.cGAs(
    count: Int,
    builder: CellularClusterBuilder<V, F>.(index: Int) -> Unit
) = loop(start = 0, end = count) { index ->
    addCluster(CellularCluster<V, F>().apply { builder(index) })
}
