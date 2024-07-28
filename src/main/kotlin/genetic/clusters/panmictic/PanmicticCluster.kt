package genetic.clusters.panmictic

import genetic.clusters.base.AbstractCluster
import genetic.clusters.panmictic.PanmicticLifecycle.Companion.BASE_AFTER_LIFECYCLE
import genetic.clusters.panmictic.PanmicticLifecycle.Companion.BASE_BEFORE_LIFECYCLE

class PanmicticCluster<V, F> : AbstractCluster<V, F, PanmicticLifecycle<V, F>>(), PanmicticClusterBuilder<V, F> {
    override lateinit var lifecycle: suspend PanmicticLifecycle<V, F>.() -> Unit
    override val lifecycleScope: PanmicticLifecycle<V, F> by lazy { PanmicticLifecycleInstance(this) }
    override var beforeLifecycle: suspend PanmicticLifecycle<V, F>.() -> Unit = BASE_BEFORE_LIFECYCLE
    override var afterLifecycle: suspend PanmicticLifecycle<V, F>.() -> Unit = BASE_AFTER_LIFECYCLE
    override var elitism: Int = 0

    override fun PanmicticClusterBuilder<V, F>.lifecycle(
        before: (suspend PanmicticLifecycle<V, F>.() -> Unit)?,
        after: (suspend PanmicticLifecycle<V, F>.() -> Unit)?,
        lifecycle: suspend PanmicticLifecycle<V, F>.() -> Unit
    ) {
        before?.let { beforeLifecycle = before }
        after?.let { afterLifecycle = after }
        this@PanmicticCluster.lifecycle = lifecycle
    }

}