package genetic.ga.panmictic.operators.mutation

import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.panmictic.builder.PanmicticGABuilder
import genetic.ga.base_operators.mutation.uniform.mutationUniform

suspend fun <F> SimpleClusterLifecycle<DoubleArray, F>.mutationUniform(
    panmicticGABuilder: PanmicticGABuilder<DoubleArray, F>,
    low: Double,
    up: Double,
    mutationChance: Double,
    mutationUniformChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(panmicticGABuilder, mutationChance, onlySingleRun) { chromosome ->
    mutationUniform(chromosome.value, low, up, mutationUniformChance, random)
}

suspend fun <F> SimpleClusterLifecycle<IntArray, F>.mutationUniform(
    panmicticGABuilder: PanmicticGABuilder<IntArray, F>,
    low: Int,
    up: Int,
    mutationChance: Double,
    mutationUniformChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(panmicticGABuilder, mutationChance, onlySingleRun) { chromosome ->
    mutationUniform(chromosome.value, low, up, mutationUniformChance, random)
}

suspend fun <F> SimpleClusterLifecycle<LongArray, F>.mutationUniform(
    panmicticGABuilder: PanmicticGABuilder<LongArray, F>,
    low: Long,
    up: Long,
    mutationChance: Double,
    mutationUniformChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(panmicticGABuilder, mutationChance, onlySingleRun) { chromosome ->
    mutationUniform(chromosome.value, low, up, mutationUniformChance, random)
}
