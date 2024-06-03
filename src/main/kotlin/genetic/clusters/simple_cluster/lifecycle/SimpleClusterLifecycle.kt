package genetic.clusters.simple_cluster.lifecycle

import genetic.clusters.simple_cluster.SimpleClusterInstance
import genetic.clusters.simple_cluster.builder.SimpleClusterBuilder
import genetic.clusters.simple_cluster.lifecycle.operators.fitnessAll

interface SimpleClusterLifecycle<V, F> : SimpleClusterBuilder<V, F>, MultiRunHelper, LifecycleStore {
    val generation: Int
    val isSingleRun: Boolean
    var elitism: Int
    var stopSignal: Boolean

    companion object {
        val BASE_BEFORE_LIFECYCLE: suspend SimpleClusterLifecycle<*, *>.() -> Unit = { fitnessAll() }
        val BASE_AFTER_LIFECYCLE: suspend SimpleClusterLifecycle<*, *>.() -> Unit = { }
    }
}

internal class SimpleClusterLifecycleInstance<V, F>(
    private val simpleClusterInstance: SimpleClusterInstance<V, F>,
    multiRunHelper: MultiRunHelper = MultiRunHelperInstance(),
    lifecycleStore: LifecycleStore = LifecycleStoreInstance(),
) : SimpleClusterLifecycle<V, F>,
    SimpleClusterBuilder<V, F> by simpleClusterInstance,
    MultiRunHelper by multiRunHelper,
    LifecycleStore by lifecycleStore {
    override val generation get() = simpleClusterInstance.generation
    override val isSingleRun get() = this.extraDispatchers?.isEmpty() ?: true
    override var elitism: Int = 0
    override var stopSignal: Boolean = false
}
