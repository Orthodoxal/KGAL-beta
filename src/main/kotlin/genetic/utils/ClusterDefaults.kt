package genetic.utils

import genetic.clusters.Cluster
import genetic.clusters.ClusterBuilder

fun <V, F> checkClusterNameOrTrySetDefaultName(cluster: Cluster<V, F>, clusters: List<Cluster<V, F>>) {
    cluster.name?.let { name ->
        if (clusters.any { it.name == name }) {
            throw IllegalStateException("Cluster with name - $name has already exist, cluster name must be unique in GA")
        }
    } ?: trySetClusterDefaultName(cluster, clusters)
}

fun <V, F> trySetClusterDefaultName(cluster: Cluster<V, F>, clusters: List<Cluster<V, F>>) {
    val asClusterBuilder = (cluster as? ClusterBuilder<*, *>) ?: throw IllegalStateException("Cluster must have a name")
    val defaultName = "Population ${clusters.size}"
    if (clusters.any { it.name == defaultName }) {
        throw RuntimeException("Set default cluster name FAILED, cluster with $defaultName has already exist")
    }
    asClusterBuilder.name = defaultName
}
