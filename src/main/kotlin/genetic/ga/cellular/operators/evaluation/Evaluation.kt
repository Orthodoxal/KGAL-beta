package genetic.ga.cellular.operators.evaluation

import genetic.ga.cellular.lifecycle.CellLifecycle
import genetic.ga.cellular.lifecycle.CellularLifecycle
import genetic.ga.core.lifecycle.size
import genetic.ga.core.operators.evaluation.evaluate
import genetic.ga.core.operators.evaluation.fitnessAll

fun <V, F> CellLifecycle<V, F>.evaluation(
    compareWithSecondChild: Boolean = true,
    fitnessFunction: (V) -> F = this.fitnessFunction,
) {
    cellChromosome.evaluate(fitnessFunction)
    if (compareWithSecondChild) {
        val neighbour = neighbours[0]
        neighbour.evaluate(fitnessFunction)
        if (neighbour > cellChromosome) cellChromosome = neighbour
    }
}

/**
 * NOTE! DO NOT USE IT IN [CellLifecycle]! Use [evaluation]
 */
suspend fun <V, F> CellularLifecycle<V, F>.evaluationAll(
    parallelWorkersLimit: Int = parallelismConfig.count,
    fitnessFunction: (V) -> F = this.fitnessFunction,
) = fitnessAll(0, size, parallelWorkersLimit, fitnessFunction)
