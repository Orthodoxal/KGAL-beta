package genetic.ga

import genetic.clusters.Cluster
import genetic.clusters.state.ClusterStopPolicy
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

interface GA<V, F> {
    suspend fun start(coroutineContext: CoroutineContext = EmptyCoroutineContext)
    suspend fun resume(coroutineContext: CoroutineContext = EmptyCoroutineContext)
    suspend fun restart(coroutineContext: CoroutineContext = EmptyCoroutineContext)
    suspend fun stop(stopPolicy: ClusterStopPolicy)

    val iteration: Int
    val maxIteration: Int
    val clusters: List<Cluster<V, F>>
}
