package genetic.ga.distributed.config

import genetic.ga.core.GA
import genetic.ga.core.config.ConfigGA
import genetic.ga.distributed.lifecycle.DistributedLifecycle

interface DistributedConfig<V, F> : ConfigGA<V, F, DistributedLifecycle<V, F>> {
    val clusters: List<GA<V, F>>
    fun addCluster(ga: GA<V, F>): GA<V, F>
    operator fun GA<V, F>.unaryPlus(): GA<V, F> = addCluster(this)
}
