package genetic.ga.panmictic.operators.mutation

import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.base_operators.mutation.gaussian.mutationGaussian
import genetic.ga.panmictic.builder.PanmicticGABuilder

suspend fun <F> SimpleClusterLifecycle<DoubleArray, F>.mutationGaussian(
    panmicticGABuilder: PanmicticGABuilder<DoubleArray, F>,
    mean: Double,
    stddev: Double,
    mutationChance: Double,
    mutationGaussianChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(panmicticGABuilder, mutationChance, onlySingleRun) { chromosome ->
    mutationGaussian(chromosome.value, mean, stddev, mutationGaussianChance, random)
}
