package genetic.ga.panmictic.builder

import genetic.ga.GABuilder
import genetic.ga.panmictic.lifecycle.PanmicticGALifecycle

interface PanmicticGABuilder<V, F> : GABuilder<V, F> {

    fun PanmicticGABuilder<V, F>.lifecycle(
        before: (suspend PanmicticGALifecycle<V, F>.() -> Unit)? = null,
        after: (suspend PanmicticGALifecycle<V, F>.() -> Unit)? = null,
        lifecycle: suspend PanmicticGALifecycle<V, F>.() -> Unit,
    )
}
