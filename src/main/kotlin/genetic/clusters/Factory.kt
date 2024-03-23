package genetic.clusters

import genetic.clusters.simple_cluster.SimpleClusterInstance
import genetic.clusters.simple_cluster.builder.SimpleClusterBuilder

fun <V, F> simpleCluster(): Pair<Cluster<V, F>, SimpleClusterBuilder<V, F>> =
    SimpleClusterInstance<V, F>().let { it to it }

inline fun <V, F> simpleCluster(builder: SimpleClusterBuilder<V, F>.() -> Unit): Cluster<V, F> {
    val (cluster, clusterBuilder) = simpleCluster<V, F>()
    clusterBuilder.apply(builder)
    return cluster
}
