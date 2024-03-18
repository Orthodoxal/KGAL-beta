package genetic.clusters

import genetic.clusters.async_cluster.AsyncClusterInstance
import genetic.clusters.async_cluster.builder.AsyncClusterBuilder
import genetic.clusters.simple_cluster.SimpleClusterInstance
import genetic.clusters.simple_cluster.builder.SimpleClusterBuilder
import kotlinx.coroutines.channels.Channel

fun <V, F> simpleCluster(): Pair<Cluster<V, F>, SimpleClusterBuilder<V, F>> =
    SimpleClusterInstance<V, F>().let { it to it }

fun <V, F> asyncCluster(): Pair<Cluster<V, F>, AsyncClusterBuilder<V, F>> =
    AsyncClusterInstance<V, F>(
        taskChannel = Channel(Channel.UNLIMITED),
        resultChannel = Channel(Channel.UNLIMITED),
    ).let { it to it }

inline fun <V, F> simpleCluster(builder: SimpleClusterBuilder<V, F>.() -> Unit): Cluster<V, F> {
    val (cluster, clusterBuilder) = simpleCluster<V, F>()
    clusterBuilder.apply(builder)
    return cluster
}

inline fun <V, F> asyncCluster(builder: AsyncClusterBuilder<V, F>.() -> Unit): Cluster<V, F> {
    val (cluster, clusterBuilder) = asyncCluster<V, F>()
    clusterBuilder.apply(builder)
    return cluster
}
