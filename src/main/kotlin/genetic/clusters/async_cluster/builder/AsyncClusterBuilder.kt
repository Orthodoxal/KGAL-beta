package genetic.clusters.async_cluster.builder

import genetic.clusters.ClusterBuilder
import genetic.clusters.async_cluster.lifecycle.AsyncClusterLifecycle
import genetic.clusters.async_cluster.operator.AsyncOperator
import kotlinx.coroutines.CoroutineDispatcher

interface AsyncClusterBuilder<V, F> : ClusterBuilder<V, F> {
    var dispatchers: Array<CoroutineDispatcher>
    var operators: Array<AsyncOperator<V, F>>

    fun AsyncClusterBuilder<V, F>.lifecycle(lifecycle: AsyncClusterLifecycle<V, F>.() -> Unit)
}
