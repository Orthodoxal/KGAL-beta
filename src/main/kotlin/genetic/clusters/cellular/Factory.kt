package genetic.clusters.cellular

import genetic.clusters.base.Cluster

inline fun <V, F> cellularCluster(builder: CellularClusterBuilder<V, F>.() -> Unit): Cluster<V, F> =
    CellularCluster<V, F>().apply(builder)
