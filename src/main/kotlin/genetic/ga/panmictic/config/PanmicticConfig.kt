package genetic.ga.panmictic.config

import genetic.ga.core.config.ConfigGA
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

interface PanmicticConfig<V, F> : ConfigGA<V, F, PanmicticLifecycle<V, F>> {
    val elitism: Int
}
