package genetic.ga

import genetic.clusters.base.state.StopPolicy
import genetic.stat.StatisticNote
import genetic.stat.Statistics
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.FlowCollector
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

interface GA<V, F> {
    fun CoroutineScope.start(coroutineContext: CoroutineContext = EmptyCoroutineContext)
    fun CoroutineScope.resume(coroutineContext: CoroutineContext = EmptyCoroutineContext)
    fun CoroutineScope.restart(coroutineContext: CoroutineContext = EmptyCoroutineContext)
    fun CoroutineScope.stop(
        coroutineContext: CoroutineContext = EmptyCoroutineContext,
        stopPolicy: StopPolicy = StopPolicy.Default,
    )

    fun collectStat(collector: FlowCollector<StatisticNote>): GA<V, F>
    fun statFlow(collector: suspend CoroutineScope.(stat: Statistics) -> Unit): GA<V, F>
}
