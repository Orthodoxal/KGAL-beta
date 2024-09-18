package genetic.ga.distributed.config

import genetic.ga.core.GA
import genetic.ga.core.config.AbstractConfigGAScope
import genetic.ga.distributed.lifecycle.DistributedLifecycle
import genetic.ga.distributed.operators.children.startChildren
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.random.Random

class DistributedConfigScope<V, F>(
    override val random: Random,
    override val fitnessFunction: (V) -> F,
) : DistributedConfig<V, F>, ClusterFactoryScope<V, F>, AbstractConfigGAScope<V, F, DistributedLifecycle<V, F>>() {
    override var clusters: MutableList<GA<V, F>> = mutableListOf()
    override val distributedExtraDispatchers: Array<CoroutineDispatcher>? get() = extraDispatchers

    override fun addCluster(ga: GA<V, F>): GA<V, F> = ga.apply(clusters::add)

    override val baseEvolve: suspend DistributedLifecycle<V, F>.() -> Unit = { startChildren() }
    override var evolution: suspend DistributedLifecycle<V, F>.() -> Unit = baseEvolve
}
