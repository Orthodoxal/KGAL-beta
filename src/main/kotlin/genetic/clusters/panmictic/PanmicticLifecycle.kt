package genetic.clusters.panmictic

import genetic.clusters.base.lifecycle.AbstractClusterLifecycle
import genetic.clusters.base.lifecycle.ClusterLifecycle
import genetic.clusters.panmictic.operators.evaluation.evaluation

interface PanmicticLifecycle<V, F> : ClusterLifecycle<V, F> {
    var elitism: Int
    val isSingleRun: Boolean

    companion object {
        val BASE_BEFORE_LIFECYCLE: suspend PanmicticLifecycle<*, *>.() -> Unit = { evaluation() }
        val BASE_AFTER_LIFECYCLE: suspend PanmicticLifecycle<*, *>.() -> Unit = { }
    }
}

internal class PanmicticLifecycleInstance<V, F>(
    private val panmicticCluster: PanmicticCluster<V, F>,
) : AbstractClusterLifecycle<V, F>(panmicticCluster), PanmicticLifecycle<V, F> {

    override var elitism: Int
        get() = panmicticCluster.elitism
        set(value) {
            panmicticCluster.elitism = value
        }

    override val isSingleRun: Boolean get() = panmicticCluster.isSingleRun
}
