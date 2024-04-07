package genetic.ga.distributed.lifecycle

import genetic.ga.distributed.builder.DistributedGABuilder

interface DistributedGALifecycle<V, F> : DistributedGABuilder<V, F>

internal class DistributedGALifecycleInstance<V, F>(
    builder: DistributedGABuilder<V, F>
) : DistributedGALifecycle<V, F>, DistributedGABuilder<V, F> by builder
