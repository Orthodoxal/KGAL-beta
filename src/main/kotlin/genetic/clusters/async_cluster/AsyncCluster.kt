package genetic.clusters.async_cluster

import genetic.clusters.async_cluster.operator.AsyncOperator
import genetic.clusters.async_cluster.operator.AsyncOperatorResult
import genetic.clusters.async_cluster.operator.AsyncOperatorTask
import genetic.clusters.cluster.Cluster
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel

interface AsyncCluster<V, F> : Cluster<V, F>, AsyncClusterApi<V, F> {
    val taskChannel: Channel<AsyncOperatorTask<V, F>>
    val resultChannel: Channel<AsyncOperatorResult<V, F>>
    var dispatchers: Array<CoroutineDispatcher>
    var operators: Array<AsyncOperator<V, F>>

    override suspend fun sendResult(element: AsyncOperatorResult<V, F>) = resultChannel.send(element)
}
