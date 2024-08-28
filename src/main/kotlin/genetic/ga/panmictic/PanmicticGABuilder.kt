package genetic.ga.panmictic

import genetic.ga.core.builder.GABuilder
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

interface PanmicticGABuilder<V, F> : GABuilder<V, F, PanmicticLifecycle<V, F>> {
    var elitism: Int
}
