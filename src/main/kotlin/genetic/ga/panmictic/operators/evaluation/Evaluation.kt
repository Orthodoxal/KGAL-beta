package genetic.ga.panmictic.operators.evaluation

import genetic.ga.core.lifecycle.size
import genetic.ga.core.operators.evaluation.fitnessAll
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

suspend fun <V, F> PanmicticLifecycle<V, F>.evaluation(
    parallelWorkersLimit: Int = parallelismConfig.count,
    fitnessFunction: (V) -> F = this.fitnessFunction,
) = fitnessAll(elitism, size, parallelWorkersLimit, fitnessFunction)
