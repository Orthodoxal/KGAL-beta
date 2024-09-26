package genetic.ga.core.operators.evaluation

import genetic.chromosome.Chromosome
import genetic.ga.core.lifecycle.Lifecycle
import genetic.ga.core.parallelism.processor.execute
import genetic.ga.core.population.get

inline fun <V, F> Chromosome<V, F>.evaluate(fitnessFunction: (V) -> F) {
    fitness = fitnessFunction(value)
}

suspend inline fun <V, F> Lifecycle<V, F>.fitnessAll(
    start: Int,
    end: Int,
    parallelWorkersLimit: Int,
    crossinline fitnessFunction: (V) -> F,
) {
    execute(
        parallelWorkersLimit = parallelWorkersLimit,
        startIteration = start,
        endIteration = end,
        action = { index -> population[index].evaluate(fitnessFunction) },
    )
}
