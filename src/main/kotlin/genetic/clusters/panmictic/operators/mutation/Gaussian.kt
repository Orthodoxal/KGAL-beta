package genetic.clusters.panmictic.operators.mutation

import genetic.clusters.base.operators.mutation.gaussian.mutationGaussian
import genetic.clusters.panmictic.PanmicticLifecycle

suspend fun <F> PanmicticLifecycle<DoubleArray, F>.mutGaussian(
    mean: Double,
    stddev: Double,
    mutationChance: Double,
    mutationGaussianChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationGaussian(chromosome.value, mean, stddev, mutationGaussianChance, random)
}
