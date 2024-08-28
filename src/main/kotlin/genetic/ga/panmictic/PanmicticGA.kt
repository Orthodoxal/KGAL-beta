package genetic.ga.panmictic

import genetic.ga.core.AbstractGA
import genetic.ga.distributed.lifecycle.DistributedLifecycle
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle
import genetic.ga.panmictic.lifecycle.PanmicticLifecycleInstance
import genetic.ga.panmictic.operators.evaluation.evaluation
import kotlin.random.Random

class PanmicticGA<V, F>(
    random: Random,
) : AbstractGA<V, F, PanmicticLifecycle<V, F>>(random), PanmicticGABuilder<V, F> {
    override val evolveScope: PanmicticLifecycle<V, F> by lazy { PanmicticLifecycleInstance(this) }

    override val baseBefore: suspend PanmicticLifecycle<V, F>.() -> Unit = { evaluation() }

    override var before: suspend PanmicticLifecycle<V, F>.() -> Unit = baseBefore
    override lateinit var evolve: suspend PanmicticLifecycle<V, F>.() -> Unit
    override var after: suspend PanmicticLifecycle<V, F>.() -> Unit = baseAfter

    override var elitism: Int = 0
}