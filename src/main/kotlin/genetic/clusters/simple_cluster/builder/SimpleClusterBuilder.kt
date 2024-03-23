package genetic.clusters.simple_cluster.builder

import genetic.clusters.ClusterBuilder
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import kotlinx.coroutines.CoroutineDispatcher

interface SimpleClusterBuilder<V, F> : ClusterBuilder<V, F> {
    var mainDispatcher: CoroutineDispatcher?
    var extraDispatchers: Array<CoroutineDispatcher>?
    fun SimpleClusterBuilder<V, F>.lifecycle(
        before: (suspend SimpleClusterLifecycle<V, F>.() -> Unit)? = null,
        after: (suspend SimpleClusterLifecycle<V, F>.() -> Unit)? = null,
        lifecycle: suspend SimpleClusterLifecycle<V, F>.() -> Unit,
    )
}
