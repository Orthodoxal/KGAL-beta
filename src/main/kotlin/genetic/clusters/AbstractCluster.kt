package genetic.clusters

import genetic.chromosome.Chromosome
import genetic.clusters.state.ClusterState
import genetic.clusters.state.ClusterStopPolicy
import genetic.clusters.state.clusterStateMachine
import genetic.stat.Statistics
import genetic.stat.StatisticsInstance
import kotlinx.coroutines.Job
import kotlinx.coroutines.withTimeout
import kotlin.coroutines.cancellation.CancellationException
import kotlin.random.Random

abstract class AbstractCluster<V, F> : Cluster<V, F> {
    protected var stopSignal: Boolean = false
    protected var state: ClusterState = ClusterState.INITIALIZE
        set(value) {
            field = clusterStateMachine(field, value)
        }
    protected var clusterJob: Job? = null

    var random: Random = Random
    override lateinit var population: Array<Chromosome<V, F>>
    override var name: String? = null
    override var populationSize: Int = 0
    override var generation: Int = 0
    override var maxGeneration: Int = 0

    protected var statisticsInstance: StatisticsInstance? = null
    override val stat: Statistics? get() = statisticsInstance

    protected abstract suspend fun startByOption(generationFrom: Int)

    override suspend fun start(generationFrom: Int) {
        if (state == ClusterState.STARTED) return

        startByOption(generationFrom)
    }

    override suspend fun resume() {
        if (state != ClusterState.STOPPED)
            throw IllegalStateException("Cluster $name state $state incorrect for resuming: State must be STOPPED")

        startByOption(generationFrom = generation)
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
                                message = "Cluster $name stop cause $stopPolicy policy",
                                cause = null
                            )
                        )
                        stopSignal = false
                        state = ClusterState.STOPPED
                    }
                }
            }
        }
    }
}
