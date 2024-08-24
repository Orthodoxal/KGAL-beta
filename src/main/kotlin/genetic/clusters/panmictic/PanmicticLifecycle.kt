package genetic.clusters.panmictic

import genetic.clusters.base.lifecycle.AbstractClusterLifecycle
import genetic.clusters.base.lifecycle.ClusterLifecycle
import kotlinx.coroutines.CoroutineDispatcher

interface PanmicticLifecycle<V, F> : ClusterLifecycle<V, F> {
    var elitism: Int
}

internal class PanmicticLifecycleInstance<V, F>(
    private val panmicticCluster: PanmicticCluster<V, F>,
) : AbstractClusterLifecycle<V, F>(panmicticCluster), PanmicticLifecycle<V, F> {

    override var elitism: Int
        get() = panmicticCluster.elitism
        set(value) {
            panmicticCluster.elitism = value
        }

    override val extraDispatchers: Array<CoroutineDispatcher>? get() = panmicticCluster.extraDispatchers
}
