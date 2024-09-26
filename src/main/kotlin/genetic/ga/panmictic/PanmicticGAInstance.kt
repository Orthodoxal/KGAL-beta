package genetic.ga.panmictic

import genetic.ga.core.AbstractGA
import genetic.ga.panmictic.config.PanmicticConfig
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle
import genetic.ga.panmictic.lifecycle.PanmicticLifecycleInstance
import genetic.ga.panmictic.population.PanmicticPopulation

internal class PanmicticGAInstance<V, F>(
    configuration: PanmicticConfig<V, F>,
    override val population: PanmicticPopulation<V, F>,
) : PanmicticGA<V, F>, AbstractGA<V, F, PanmicticLifecycle<V, F>>(configuration) {
    override val lifecycle: PanmicticLifecycle<V, F> by lazy {
        PanmicticLifecycleInstance(this, configuration.parallelismConfig)
    }

    override var elitism: Int = configuration.elitism
}
