package genetic.clusters.async_cluster.operator

data class AsyncOperatorTask<V, F>(
    val asyncOperator: AsyncOperator<V, F>,
    val asyncOperatorArgs: AsyncOperatorArgs<V, F>,
)
