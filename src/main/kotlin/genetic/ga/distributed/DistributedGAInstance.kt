package genetic.ga.distributed

import genetic.ga.core.*
import genetic.ga.core.state.GAState
import genetic.ga.distributed.config.DistributedConfig
import genetic.ga.distributed.lifecycle.DistributedLifecycle
import genetic.ga.distributed.lifecycle.DistributedLifecycleInstance
import genetic.ga.distributed.lifecycle.LifecycleStartOption
import genetic.ga.distributed.population.DistributedPopulation

internal class DistributedGAInstance<V, F>(
    configuration: DistributedConfig<V, F>,
    override val population: DistributedPopulation<V, F>,
) : DistributedGA<V, F>, AbstractGA<V, F, DistributedLifecycle<V, F>>(configuration) {
    override val lifecycle: DistributedLifecycle<V, F> by lazy {
        DistributedLifecycleInstance(this, configuration.parallelismConfig)
    }

    override val clusters: List<GA<V, F>> = configuration.clusters

    init {
        clusters.forEach {
            it.collect(id = DISTRIBUTED_COLLECTOR_ID) { stat -> it.statisticsProvider.emit(stat) }
        }
    }

    override suspend fun start() {
        if (state == GAState.STARTED) return
        lifecycle.startOption = LifecycleStartOption.Start
        startByOption(iterationFrom = 0)
    }

    override suspend fun resume() {
        if (state != GAState.STOPPED)
            throw IllegalStateException("GA $name state $state incorrect for resuming: State must be STOPPED")

        lifecycle.startOption = LifecycleStartOption.Resume
        startByOption(iterationFrom = iteration)
    }

    override suspend fun restart(resetPopulation: Boolean) {
        lifecycle.startOption = LifecycleStartOption.Restart(resetPopulation)
        super.restart(resetPopulation = false)
    }

    private companion object {
        const val DISTRIBUTED_COLLECTOR_ID = "DISTRIBUTED_COLLECTOR_ID"
    }
}
