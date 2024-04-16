package genetic.ga

import genetic.clusters.Cluster
import genetic.clusters.state.ClusterStopPolicy
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

interface GA<V, F> {
    fun CoroutineScope.start(iterationFrom: Int = 0, coroutineContext: CoroutineContext = EmptyCoroutineContext)
    fun CoroutineScope.resume(coroutineContext: CoroutineContext = EmptyCoroutineContext)
    fun CoroutineScope.restart(iterationFrom: Int = 0, coroutineContext: CoroutineContext = EmptyCoroutineContext)
    fun CoroutineScope.stop(
        stopPolicy: ClusterStopPolicy = ClusterStopPolicy.Default,
        coroutineContext: CoroutineContext = EmptyCoroutineContext,
    )

    val iteration: Int
    val maxIteration: Int
    val clusters: List<Cluster<V, F>>
}
