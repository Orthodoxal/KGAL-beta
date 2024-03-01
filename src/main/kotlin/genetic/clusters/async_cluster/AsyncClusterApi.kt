package genetic.clusters.async_cluster

import genetic.clusters.async_cluster.operator.AsyncOperatorResult

interface AsyncClusterApi<V, F> {
    suspend fun sendResult(element: AsyncOperatorResult<V, F>)
}
