package genetic.clusters.panmictic

import genetic.clusters.base.AbstractCluster
import genetic.clusters.panmictic.operators.evaluation.evaluation

class PanmicticCluster<V, F> : AbstractCluster<V, F, PanmicticLifecycle<V, F>>(), PanmicticClusterBuilder<V, F> {
    override lateinit var lifecycle: suspend PanmicticLifecycle<V, F>.() -> Unit
    override val lifecycleScope: PanmicticLifecycle<V, F> by lazy { PanmicticLifecycleInstance(this) }
    override var beforeLifecycle: suspend PanmicticLifecycle<V, F>.() -> Unit = { evaluation() }
    override var elitism: Int = 0
}