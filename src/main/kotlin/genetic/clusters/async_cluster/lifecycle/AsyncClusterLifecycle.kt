package genetic.clusters.async_cluster.lifecycle

import genetic.clusters.async_cluster.builder.AsyncClusterBuilder
import genetic.clusters.async_cluster.operator.AsyncOperatorResult
import kotlinx.coroutines.channels.Channel

interface AsyncClusterLifecycle<V, F> : AsyncClusterBuilder<V, F> {
    suspend fun sendResult(element: AsyncOperatorResult<V, F>)
}

internal class AsyncClusterLifecycleInstance<V, F>(
    builder: AsyncClusterBuilder<V, F>,
    private val resultChannel: Channel<AsyncOperatorResult<V, F>>,
) : AsyncClusterLifecycle<V, F>, AsyncClusterBuilder<V, F> by builder {
    override suspend fun sendResult(element: AsyncOperatorResult<V, F>) = resultChannel.send(element)
}
