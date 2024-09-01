package genetic.ga.distributed

import genetic.ga.core.AbstractGA
import genetic.ga.core.GA
import genetic.ga.core.builder.GABuilder
import genetic.ga.core.name
import genetic.ga.core.population.Population
import genetic.ga.core.population.getDistributedFullPopulation
import genetic.ga.core.state.GAState
import genetic.ga.distributed.lifecycle.DistributedLifecycle
import genetic.ga.distributed.lifecycle.DistributedLifecycleInstance
import genetic.ga.distributed.lifecycle.LifecycleStartOption
import genetic.ga.distributed.operators.children.startChildren
import kotlinx.coroutines.launch
import kotlin.random.Random

class DistributedGA<V, F>(
    random: Random,
) : AbstractGA<V, F, DistributedLifecycle<V, F>>(random), DistributedGABuilder<V, F> {
    override val evolveScope: DistributedLifecycle<V, F> by lazy { DistributedLifecycleInstance(this) }

    override val baseEvolve: suspend DistributedLifecycle<V, F>.() -> Unit = { startChildren() }

    override var before: suspend DistributedLifecycle<V, F>.() -> Unit = baseBefore
    override var evolve: suspend DistributedLifecycle<V, F>.() -> Unit = baseEvolve
    override var after: suspend DistributedLifecycle<V, F>.() -> Unit = baseAfter

    var gas: MutableList<GA<V, F>> = mutableListOf()
    override var population: Population<V, F>
        get() = getDistributedFullPopulation(gas.map { it.population })
        set(_) {}

    override suspend fun start() {
        if (state == GAState.STARTED) return
        evolveScope.lifecycleStartOption = LifecycleStartOption.Start
        startByOption(iterationFrom = 0)
    }

    override suspend fun resume() {
        if (state != GAState.STOPPED)
            throw IllegalStateException("GA $name state $state incorrect for resuming: State must be STOPPED")

        evolveScope.lifecycleStartOption = LifecycleStartOption.Resume
        startByOption(iterationFrom = iteration)
    }

    override suspend fun restart(resetPopulation: Boolean) {
        evolveScope.lifecycleStartOption = LifecycleStartOption.Restart(resetPopulation)
        startByOption(iterationFrom = 0)
    }

    override fun prepareStatistics() {
        super.prepareStatistics()
        with(statisticsCoroutineScope) {
            gas.forEach {
                launch { it.collectStat { stat.emit(it) } }
            }
        }
    }

    override suspend fun startByOption(iterationFrom: Int) {
        prepareClusters()
        super.startByOption(iterationFrom)
    }

    override fun addCluster(ga: GA<V, F>): GA<V, F> = ga.apply(gas::add)

    private fun prepareClusters() {
        gas
            .mapNotNull { it as? GABuilder<V, F, *> }
            .forEachIndexed { index, cluster ->

                if (cluster.fitnessFunction == null) {
                    cluster.fitnessFunction = fitnessFunction
                }

                if (cluster.mainDispatcher == null) {
                    val extraDispatcher = extraDispatchers?.getOrNull(index)
                    cluster.mainDispatcher = extraDispatcher
                }
            }
    }
}