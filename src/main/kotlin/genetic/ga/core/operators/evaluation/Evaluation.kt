package genetic.ga.core.operators.evaluation

import genetic.chromosome.Chromosome
import genetic.ga.core.lifecycle.Lifecycle
import genetic.ga.core.population.get
import genetic.ga.core.processor.process

inline fun <V, F> Chromosome<V, F>.evaluate(fitnessFunction: (V) -> F) {
    fitness = fitnessFunction(value)
}

suspend inline fun <V, F> Lifecycle<V, F>.fitnessAll(
    start: Int,
    end: Int,
    parallelismLimit: Int,
    crossinline fitnessFunction: (V) -> F,
) {
    process(
        parallelismLimit = parallelismLimit,
        startIteration = start,
        endIteration = end,
        action = { index, _ -> population[index].evaluate(fitnessFunction) },
    )
}
