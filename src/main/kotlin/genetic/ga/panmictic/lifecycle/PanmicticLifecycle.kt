package genetic.ga.panmictic.lifecycle

import genetic.ga.core.lifecycle.AbstractLifecycle
import genetic.ga.core.lifecycle.Lifecycle
import genetic.ga.core.processor.parallelism.config.ParallelismConfig
import genetic.ga.panmictic.PanmicticGA

interface PanmicticLifecycle<V, F> : Lifecycle<V, F> {
    var elitism: Int
}

internal class PanmicticLifecycleInstance<V, F>(
    private val panmicticGA: PanmicticGA<V, F>,
    override val parallelismConfig: ParallelismConfig,
) : PanmicticLifecycle<V, F>, AbstractLifecycle<V, F>(panmicticGA) {

    override var elitism: Int
        get() = panmicticGA.elitism
        set(value) {
            panmicticGA.elitism = value
        }
}
