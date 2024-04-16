package genetic.ga.distributed.lifecycle

import genetic.ga.distributed.builder.DistributedGABuilder
import genetic.ga.lifecycle.GALifecycle
import genetic.ga.lifecycle.LifecycleStartOption

interface DistributedGALifecycle<V, F> : DistributedGABuilder<V, F>, GALifecycle

internal class DistributedGALifecycleInstance<V, F>(
    builder: DistributedGABuilder<V, F>,
    override var lifecycleStartOption: LifecycleStartOption,
) : DistributedGALifecycle<V, F>, DistributedGABuilder<V, F> by builder
