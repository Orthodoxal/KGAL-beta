package genetic.ga

import genetic.clusters.Cluster
import genetic.clusters.state.ClusterStopPolicy
import genetic.stat.StatisticNote
import genetic.stat.Statistics
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.FlowCollector
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

    fun collectStat(collector: FlowCollector<StatisticNote>): GA<V, F>
    fun statFlow(collector: suspend CoroutineScope.(stat: Statistics) -> Unit): GA<V, F>

    val iteration: Int
    val maxIteration: Int
    val clusters: List<Cluster<V, F>>
}
