package genetic.clusters

import genetic.chromosome.Chromosome
import genetic.clusters.state.ClusterState
import genetic.clusters.state.ClusterStopPolicy
import kotlinx.coroutines.Job
import kotlinx.coroutines.withTimeout
import kotlin.coroutines.cancellation.CancellationException
import kotlin.random.Random

abstract class AbstractCluster<V, F> : Cluster<V, F> {
    protected var stopSignal: Boolean = false
    protected var state: ClusterState = ClusterState.INITIALIZE
    protected var clusterJob: Job? = null

    var random: Random = Random
    override lateinit var population: Array<Chromosome<V, F>>
    override var name: String? = null
    override var populationSize: Int = 0
    override var generation: Int = 0
    override var maxGeneration: Int = 0

    override suspend fun resume() {
        if (state == ClusterState.STOPPED) throw IllegalStateException("Cluster $name state $state incorrect for resuming: State must be STOPPED or FINISHED")
        start()
    }

    override suspend fun stop(stopPolicy: ClusterStopPolicy) {
        when (stopPolicy) {
            is ClusterStopPolicy.Default -> stopSignal = true
            is ClusterStopPolicy.Immediately -> {
                clusterJob?.cancel(
                    cause = CancellationException(
                        message = "Cluster $name stop cause force $stopPolicy policy",
                        cause = null
                    )
                )
                state = ClusterState.STOPPED
            }

            is ClusterStopPolicy.Timeout -> {
                stopSignal = true
                withTimeout(stopPolicy.millis) {
                    if (state != ClusterState.STOPPED) {
                        clusterJob?.cancel(
                            cause = CancellationException(
                                message = "Cluster $name stop cause force $stopPolicy policy",
                                cause = null
                            )
                        )
                    }
                }
            }
        }
    }

    override suspend fun restart() {
        try {
            clusterJob?.cancel()
        } catch (_: CancellationException) {
            // nothing to do
        }

        generation = 0
        start()
    }
}
