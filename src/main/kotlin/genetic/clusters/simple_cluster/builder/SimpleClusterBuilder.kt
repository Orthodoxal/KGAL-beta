package genetic.clusters.simple_cluster.builder

import genetic.clusters.ClusterBuilder
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import kotlinx.coroutines.CoroutineDispatcher

interface SimpleClusterBuilder<V, F> : ClusterBuilder<V, F> {
    var dispatcher: CoroutineDispatcher?
    fun SimpleClusterBuilder<V, F>.lifecycle(lifecycle: SimpleClusterLifecycle<V, F>.() -> Unit)
}
