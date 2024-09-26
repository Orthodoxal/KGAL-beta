package genetic.ga.distributed.config

import genetic.ga.core.GA
import genetic.ga.core.config.AbstractConfigGAScope
import genetic.ga.core.operators.stopBy
import genetic.ga.distributed.DistributedGA
import genetic.ga.distributed.lifecycle.DistributedLifecycle
import genetic.ga.distributed.operators.children.startChildren
import kotlin.random.Random

class DistributedConfigScope<V, F>(
    override val random: Random,
    override val fitnessFunction: (V) -> F,
    override val maxIteration: Int,
) : DistributedConfig<V, F>, ClusterFactoryScope<V, F>, AbstractConfigGAScope<V, F, DistributedLifecycle<V, F>>() {
    override var clusters: MutableList<GA<V, F>> = mutableListOf()

    override fun addCluster(ga: GA<V, F>): GA<V, F> {
        if (ga is DistributedGA<*, *>) {
            throw IllegalStateException("Adding another distributedGA as a child GA unsupported.")
        }
        return ga.apply(clusters::add)
    }

    override fun AbstractConfigGAScope<V, F, DistributedLifecycle<V, F>>.evolve(
        useDefault: Boolean,
        evolution: suspend DistributedLifecycle<V, F>.() -> Unit,
    ) {
        this.evolution = evolution.takeIf { !useDefault } ?: { baseEvolve(); evolution(); stopBy(maxIteration) }
    }

    override val baseEvolve: suspend DistributedLifecycle<V, F>.() -> Unit = { startChildren(parallelismConfig.count) }
    override var evolution: suspend DistributedLifecycle<V, F>.() -> Unit = { baseEvolve(); stopBy(maxIteration) }
}
