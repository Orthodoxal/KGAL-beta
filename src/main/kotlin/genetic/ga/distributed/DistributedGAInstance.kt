package genetic.ga.distributed

import genetic.ga.core.AbstractGA
import genetic.ga.core.GA
import genetic.ga.core.name
import genetic.ga.core.state.GAState
import genetic.ga.distributed.config.DistributedConfig
import genetic.ga.distributed.lifecycle.DistributedLifecycle
import genetic.ga.distributed.lifecycle.DistributedLifecycleInstance
import genetic.ga.distributed.lifecycle.LifecycleStartOption
import genetic.ga.distributed.population.DistributedPopulation
import kotlinx.coroutines.launch

internal class DistributedGAInstance<V, F>(
    configuration: DistributedConfig<V, F>,
    override val population: DistributedPopulation<V, F>,
) : DistributedGA<V, F>, AbstractGA<V, F, DistributedLifecycle<V, F>>(configuration) {
    override val lifecycle: DistributedLifecycle<V, F> by lazy { DistributedLifecycleInstance(this) }

    override val clusters: List<GA<V, F>> = configuration.clusters

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
        startByOption(iterationFrom = 0)
    }

    override fun prepareStatistics() {
        super.prepareStatistics()
        with(statisticsCoroutineScope) {
            clusters.forEach {
                launch { it.collectStat { stat.emit(it) } }
            }
        }
    }
}
