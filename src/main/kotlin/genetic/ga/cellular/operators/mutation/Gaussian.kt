package genetic.ga.cellular.operators.mutation

import genetic.ga.core.operators.mutation.gaussian.mutationGaussian
import genetic.ga.cellular.lifecycle.CellLifecycle

fun <F> CellLifecycle<DoubleArray, F>.mutGaussian(
    mean: Double,
    stddev: Double,
    chance: Double,
    gaussianChance: Double,
) = mutation(chance) { chromosome ->
    mutationGaussian(chromosome.value, mean, stddev, gaussianChance, random)
}
