package genetic.ga.panmictic.lifecycle

import genetic.ga.panmictic.builder.PanmicticGABuilder

interface PanmicticGALifecycle<V, F> : PanmicticGABuilder<V, F>

internal class PanmicticGALifecycleInstance<V, F>(
    builder: PanmicticGABuilder<V, F>
) : PanmicticGALifecycle<V, F>, PanmicticGABuilder<V, F> by builder
