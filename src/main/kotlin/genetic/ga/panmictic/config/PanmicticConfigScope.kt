package genetic.ga.panmictic.config

import genetic.ga.core.config.AbstractConfigGAScope
import genetic.ga.core.operators.fillPopulationIfEmpty
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle
import genetic.ga.panmictic.operators.evaluation.evaluation
import kotlin.random.Random

class PanmicticConfigScope<V, F>(
    override val random: Random,
    override val fitnessFunction: (V) -> F,
) : PanmicticConfig<V, F>, AbstractConfigGAScope<V, F, PanmicticLifecycle<V, F>>() {
    override var elitism: Int = 0

    override val baseBefore: suspend PanmicticLifecycle<V, F>.() -> Unit = { fillPopulationIfEmpty(); evaluation() }
    override var beforeEvolution: suspend PanmicticLifecycle<V, F>.() -> Unit = baseBefore
}
