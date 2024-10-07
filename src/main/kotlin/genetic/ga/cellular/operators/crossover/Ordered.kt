package genetic.ga.cellular.operators.crossover

import genetic.ga.core.operators.crossover.ordered.crossoverOrdered
import genetic.ga.cellular.lifecycle.CellLifecycle

@JvmName("cxOrderedIntArray")
fun <F> CellLifecycle<IntArray, F>.cxOrdered(
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverOrdered(chromosome1.value, chromosome2.value, random)
}
