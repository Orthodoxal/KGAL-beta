package genetic.ga.panmictic.lifecycle

import genetic.ga.core.lifecycle.AbstractGALifecycle
import genetic.ga.core.lifecycle.GALifecycle
import genetic.ga.panmictic.PanmicticGA
import kotlinx.coroutines.CoroutineDispatcher

interface PanmicticLifecycle<V, F> : GALifecycle<V, F> {
    var elitism: Int
}

internal class PanmicticLifecycleInstance<V, F>(
    private val panmicticCluster: PanmicticGA<V, F>,
) : AbstractGALifecycle<V, F>(panmicticCluster), PanmicticLifecycle<V, F> {

    override var elitism: Int
        get() = panmicticCluster.elitism
        set(value) {
            panmicticCluster.elitism = value
        }

    override val extraDispatchers: Array<CoroutineDispatcher>? get() = panmicticCluster.extraDispatchers
}
