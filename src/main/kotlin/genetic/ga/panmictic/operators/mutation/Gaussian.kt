package genetic.ga.panmictic.operators.mutation

import genetic.ga.core.operators.mutation.gaussian.mutationGaussian
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

suspend fun <F> PanmicticLifecycle<DoubleArray, F>.mutGaussian(
    mean: Double,
    stddev: Double,
    chance: Double,
    gaussianChance: Double,
    parallelismLimit: Int = parallelismConfig.workersCount,
) = mutation(chance, parallelismLimit) { chromosome, random ->
    mutationGaussian(chromosome.value, mean, stddev, gaussianChance, random)
}
