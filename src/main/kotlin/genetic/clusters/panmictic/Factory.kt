package genetic.clusters.panmictic

import genetic.clusters.base.Cluster

inline fun <V, F> panmicticCluster(builder: PanmicticClusterBuilder<V, F>.() -> Unit): Cluster<V, F> =
    PanmicticCluster<V, F>().apply(builder)
