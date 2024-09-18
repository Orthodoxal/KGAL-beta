package genetic.ga.panmictic.lifecycle

import genetic.ga.core.lifecycle.BaseLifecycle
import genetic.ga.core.lifecycle.Lifecycle
import genetic.ga.panmictic.PanmicticGA

interface PanmicticLifecycle<V, F> : Lifecycle<V, F> {
    var elitism: Int
}

internal class PanmicticLifecycleInstance<V, F>(
    private val panmicticGA: PanmicticGA<V, F>,
) : PanmicticLifecycle<V, F>, Lifecycle<V, F> by BaseLifecycle(panmicticGA) {

    override var elitism: Int
        get() = panmicticGA.elitism
        set(value) {
            panmicticGA.elitism = value
        }
}
