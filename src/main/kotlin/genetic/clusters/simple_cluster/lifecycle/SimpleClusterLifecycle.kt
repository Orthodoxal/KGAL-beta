package genetic.clusters.simple_cluster.lifecycle

import genetic.clusters.simple_cluster.SimpleClusterInstance
import genetic.clusters.simple_cluster.builder.SimpleClusterBuilder

interface SimpleClusterLifecycle<V, F> : SimpleClusterBuilder<V, F>, MultiRunHelper {
    val generation: Int
    val isSingleRun: Boolean
    var elitism: Int
}

internal class SimpleClusterLifecycleInstance<V, F>(
    private val simpleClusterInstance: SimpleClusterInstance<V, F>,
    multiRunHelper: MultiRunHelper = MultiRunHelperInstance(),
) : SimpleClusterLifecycle<V, F>, SimpleClusterBuilder<V, F> by simpleClusterInstance, MultiRunHelper by multiRunHelper {
    override val generation get() = simpleClusterInstance.generation
    override val isSingleRun get() = this.extraDispatchers?.isEmpty() ?: true
    override var elitism: Int = 0
}
