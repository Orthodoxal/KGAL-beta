package genetic.clusters

import genetic.clusters.base.Cluster
import genetic.clusters.base.builder.ClusterBuilder
import genetic.clusters.cellular.CellularCluster
import genetic.clusters.cellular.CellularClusterBuilder
import genetic.clusters.distributed.DistributedCluster
import genetic.clusters.distributed.DistributedClusterBuilder
import genetic.clusters.panmictic.PanmicticCluster
import genetic.clusters.panmictic.PanmicticClusterBuilder

private const val CLUSTER_DEFAULT_NAME = "POPULATION 1"

fun ClusterBuilder<*, *, *>.setDefaultNameIfNeed() {
    if (name == null) {
        name = CLUSTER_DEFAULT_NAME
    }
}

inline fun <V, F> pGA(builder: PanmicticClusterBuilder<V, F>.() -> Unit): Cluster<V, F> =
    PanmicticCluster<V, F>().apply {
        builder()
        setDefaultNameIfNeed()
    }

inline fun <V, F> cGA(builder: CellularClusterBuilder<V, F>.() -> Unit): Cluster<V, F> =
    CellularCluster<V, F>().apply {
        builder()
        setDefaultNameIfNeed()
    }

inline fun <V, F> dGA(builder: DistributedClusterBuilder<V, F>.() -> Unit): Cluster<V, F> =
    DistributedCluster<V, F>().apply {
        builder()
        setDefaultNameIfNeed()
    }
