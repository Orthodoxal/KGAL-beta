package genetic.clusters.cellular.operators.mutation

import genetic.clusters.base.operators.mutation.gaussian.mutationGaussian
import genetic.clusters.cellular.lifecycle.CellLifecycle

fun <F> CellLifecycle<DoubleArray, F>.mutGaussian(
    mean: Double,
    stddev: Double,
    mutationChance: Double,
    mutationGaussianChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationGaussian(chromosome.value, mean, stddev, mutationGaussianChance, random)
}
