package genetic.ga.panmictic.lifecycle

import genetic.ga.lifecycle.GALifecycle
import genetic.ga.lifecycle.LifecycleStartOption
import genetic.ga.panmictic.builder.PanmicticGABuilder

interface PanmicticGALifecycle<V, F> : PanmicticGABuilder<V, F>, GALifecycle<V, F>

internal class PanmicticGALifecycleInstance<V, F>(
    builder: PanmicticGABuilder<V, F>,
    override var lifecycleStartOption: LifecycleStartOption,
) : PanmicticGALifecycle<V, F>, PanmicticGABuilder<V, F> by builder
