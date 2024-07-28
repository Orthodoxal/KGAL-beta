package genetic.clusters.cellular.operators.evaluation

import genetic.clusters.cellular.lifecycle.CellLifecycle

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
