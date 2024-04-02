package genetic.ga.cellular.operators.fitness

import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle

fun <V, F> SimpleClusterCellLifecycle<V, F>.fitness(
    cellularGABuilder: CellularGABuilder<V, F>,
    compareWithSecondChild: Boolean = false,
) {
    cellChromosome.fitness = fitnessFunction(cellChromosome.value)
    if (compareWithSecondChild) {
        neighbours[0].fitness = fitnessFunction(neighbours[0].value)
        if (neighbours[0] > cellChromosome) cellChromosome = neighbours[0]
    }
}
