package genetic.clusters.distributed.lifecycle

import genetic.clusters.base.Cluster
import genetic.clusters.base.builder.isSingleRun
import genetic.clusters.base.lifecycle.AbstractClusterLifecycle
import genetic.clusters.base.lifecycle.ClusterLifecycle
import genetic.clusters.distributed.DistributedCluster
import kotlinx.coroutines.CoroutineDispatcher

interface DistributedLifecycle<V, F> : ClusterLifecycle<V, F> {
    var lifecycleStartOption: LifecycleStartOption
    val clusters: List<Cluster<V, F>>
}

internal class DistributedLifecycleInstance<V, F>(
    private val distributedCluster: DistributedCluster<V, F>,
) : AbstractClusterLifecycle<V, F>(distributedCluster), DistributedLifecycle<V, F> {
    override val extraDispatchers: Array<CoroutineDispatcher>? get() = distributedCluster.extraDispatchers
    override var lifecycleStartOption: LifecycleStartOption = LifecycleStartOption.Start
    override val clusters: List<Cluster<V, F>> = distributedCluster.clusters
}
