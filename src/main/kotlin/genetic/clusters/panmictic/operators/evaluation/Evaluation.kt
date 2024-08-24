package genetic.clusters.panmictic.operators.evaluation

import genetic.clusters.base.lifecycle.isSingleRun
import genetic.clusters.panmictic.PanmicticLifecycle
import genetic.utils.clusters.forEach
import genetic.utils.clusters.get
import genetic.utils.clusters.popCurrentSize
import genetic.utils.clusters.runWithExtraDispatchersIterative

suspend fun <V, F> PanmicticLifecycle<V, F>.evaluation(
    onlySingleRun: Boolean = false,
    fitnessFunction: (V) -> F = this.fitnessFunction,
) {
    if (isSingleRun || onlySingleRun) {
        singleRunEvaluation(fitnessFunction)
    } else {
        multiRunEvaluation(fitnessFunction)
    }
}

inline fun <V, F> PanmicticLifecycle<V, F>.singleRunEvaluation(fitnessFunction: (V) -> F) {
    population.forEach(elitism, popCurrentSize) { it.fitness = fitnessFunction(it.value) }
}

suspend inline fun <V, F> PanmicticLifecycle<V, F>.multiRunEvaluation(
    crossinline fitnessFunction: (V) -> F,
) = runWithExtraDispatchersIterative(elitism, popCurrentSize) { index ->
    val chromosome = population[index]
    chromosome.fitness = fitnessFunction(chromosome.value)
}
