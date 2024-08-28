package genetic.ga.core.operators.evaluation

import genetic.ga.core.lifecycle.GALifecycle
import genetic.ga.core.lifecycle.isSingleRun
import genetic.ga.core.population.forEach
import genetic.ga.core.population.get
import genetic.ga.core.lifecycle.runWithExtraDispatchersIterative

suspend inline fun <V, F> GALifecycle<V, F>.fitnessAll(
    start: Int,
    end: Int,
    onlySingleRun: Boolean,
    crossinline fitnessFunction: (V) -> F,
) {
    if (isSingleRun || onlySingleRun) {
        singleRunFitness(start, end, fitnessFunction)
    } else {
        multiRunFitness(start, end, fitnessFunction)
    }
}

inline fun <V, F> GALifecycle<V, F>.singleRunFitness(
    start: Int,
    end: Int,
    fitnessFunction: (V) -> F,
) {
    population.forEach(start, end) { it.fitness = fitnessFunction(it.value) }
}

suspend inline fun <V, F> GALifecycle<V, F>.multiRunFitness(
    start: Int,
    end: Int,
    crossinline fitnessFunction: (V) -> F,
) = runWithExtraDispatchersIterative(start, end) { index ->
    val chromosome = population[index]
    chromosome.fitness = fitnessFunction(chromosome.value)
}
