package genetic.ga.cellular.operators.evaluation

import genetic.ga.cellular.lifecycle.CellLifecycle
import genetic.ga.cellular.lifecycle.CellularLifecycle
import genetic.ga.core.lifecycle.size
import genetic.ga.core.operators.evaluation.fitnessAll

fun <V, F> CellLifecycle<V, F>.evaluation(
    compareWithSecondChild: Boolean = true,
    fitnessFunction: (V) -> F = this.fitnessFunction,
) {
    cellChromosome.fitness = fitnessFunction(cellChromosome.value)
    if (compareWithSecondChild) {
        neighbours[0].fitness = fitnessFunction(neighbours[0].value)
        if (neighbours[0] > cellChromosome) cellChromosome = neighbours[0]
    }
}

suspend fun <V, F> CellularLifecycle<V, F>.evaluation(
    onlySingleRun: Boolean = false,
    fitnessFunction: (V) -> F = this.fitnessFunction,
) = fitnessAll(0, size, onlySingleRun, fitnessFunction)
