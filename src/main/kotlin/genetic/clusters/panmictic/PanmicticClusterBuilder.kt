package genetic.clusters.panmictic

import genetic.clusters.base.builder.ClusterBuilder

interface PanmicticClusterBuilder<V, F> : ClusterBuilder<V, F> {
    var elitism: Int

    fun PanmicticClusterBuilder<V, F>.lifecycle(
        before: (suspend PanmicticLifecycle<V, F>.() -> Unit)? = null,
        after: (suspend PanmicticLifecycle<V, F>.() -> Unit)? = null,
        lifecycle: suspend PanmicticLifecycle<V, F>.() -> Unit,
    )
}

val PanmicticClusterBuilder<*, *>.isSingleRun get() = extraDispatchers?.isEmpty() ?: true
