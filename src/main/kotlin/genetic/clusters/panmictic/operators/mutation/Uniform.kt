package genetic.clusters.panmictic.operators.mutation

import genetic.clusters.base.operators.mutation.uniform.mutationUniform
import genetic.clusters.panmictic.PanmicticLifecycle

suspend fun <F> PanmicticLifecycle<DoubleArray, F>.mutUniform(
    low: Double,
    up: Double,
    mutationChance: Double,
    mutationUniformChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationUniform(chromosome.value, low, up, mutationUniformChance, random)
}

suspend fun <F> PanmicticLifecycle<IntArray, F>.mutUniform(
    low: Int,
    up: Int,
    mutationChance: Double,
    mutationUniformChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationUniform(chromosome.value, low, up, mutationUniformChance, random)
}

suspend fun <F> PanmicticLifecycle<LongArray, F>.mutUniform(
    low: Long,
    up: Long,
    mutationChance: Double,
    mutationUniformChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationUniform(chromosome.value, low, up, mutationUniformChance, random)
}
