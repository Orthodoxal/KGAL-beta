package genetic.clusters.cellular.operators.crossover

import genetic.clusters.base.operators.crossover.ordered.crossoverOrdered
import genetic.clusters.cellular.lifecycle.CellLifecycle

@JvmName("cxOrderedIntArray")
fun <F> CellLifecycle<IntArray, F>.cxOrdered(
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverOrdered(chromosome1.value, chromosome2.value, random)
}
