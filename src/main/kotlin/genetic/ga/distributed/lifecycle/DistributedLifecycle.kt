package genetic.ga.distributed.lifecycle

import genetic.ga.core.GA
import genetic.ga.core.lifecycle.AbstractGALifecycle
import genetic.ga.core.lifecycle.GALifecycle
import genetic.ga.distributed.DistributedGA
import kotlinx.coroutines.CoroutineDispatcher

interface DistributedLifecycle<V, F> : GALifecycle<V, F> {
    var lifecycleStartOption: LifecycleStartOption
    val gas: List<GA<V, F>>
}

internal class DistributedLifecycleInstance<V, F>(
    private val distributedCluster: DistributedGA<V, F>,
) : AbstractGALifecycle<V, F>(distributedCluster), DistributedLifecycle<V, F> {
    override val extraDispatchers: Array<CoroutineDispatcher>? get() = distributedCluster.extraDispatchers
    override var lifecycleStartOption: LifecycleStartOption = LifecycleStartOption.Start
    override val gas: List<GA<V, F>> = distributedCluster.gas
}
