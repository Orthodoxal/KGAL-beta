package genetic.ga.distributed.lifecycle

import genetic.ga.core.GA
import genetic.ga.core.lifecycle.BaseLifecycle
import genetic.ga.core.lifecycle.Lifecycle
import genetic.ga.distributed.DistributedGA

interface DistributedLifecycle<V, F> : Lifecycle<V, F> {
    var startOption: LifecycleStartOption
    val clusters: List<GA<V, F>>
}

internal class DistributedLifecycleInstance<V, F>(
    private val distributedGA: DistributedGA<V, F>,
) : DistributedLifecycle<V, F>, Lifecycle<V, F> by BaseLifecycle(distributedGA) {
    override var startOption: LifecycleStartOption = LifecycleStartOption.Start
    override val clusters: List<GA<V, F>> get() = distributedGA.clusters
}
