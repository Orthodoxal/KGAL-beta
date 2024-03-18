package genetic.ga

import genetic.clusters.Cluster
import genetic.clusters.state.ClusterStopPolicy
import genetic.ga.state.GAState
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.cancellation.CancellationException

abstract class AbstractGA<V, F> : GA<V, F> {
    protected var state: GAState = GAState.INITIALIZE
    override var clusters: MutableList<Cluster<V, F>> = mutableListOf()
    protected var gaJob: Job? = null
    override var iteration: Int = 0
    override var maxIteration: Int = 1

    override suspend fun resume(coroutineContext: CoroutineContext) {
        if (state == GAState.STOPPED) throw IllegalStateException("GA state $state incorrect for resuming: State must be STOPPED or FINISHED")
        start(coroutineContext)
    }

    override suspend fun stop(stopPolicy: ClusterStopPolicy) {
        clusters.forEach { it.stop(stopPolicy) }
    }

    override suspend fun restart(coroutineContext: CoroutineContext) {
        try {
            gaJob?.cancel()
        } catch (_: CancellationException) {
            // nothing to do
        }

        iteration = 0
        start(coroutineContext)
    }
}
