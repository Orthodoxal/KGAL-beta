package genetic.ga.cellular.operators.mutation

import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle
import genetic.ga.base_operators.mutation.gaussian.mutationGaussian

fun <F> SimpleClusterCellLifecycle<DoubleArray, F>.mutationGaussian(
    cellularGABuilder: CellularGABuilder<DoubleArray, F>,
    mean: Double,
    stddev: Double,
    mutationChance: Double,
    mutationGaussianChance: Double,
) = mutation(cellularGABuilder, mutationChance) { chromosome ->
    mutationGaussian(chromosome.value, mean, stddev, mutationGaussianChance, random)
}
