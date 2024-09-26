package genetic.ga.panmictic.operators.mutation

import genetic.ga.core.operators.mutation.uniform.mutationUniform
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

@JvmName("DoubleArray")
suspend fun <F> PanmicticLifecycle<DoubleArray, F>.mutUniform(
    low: Double,
    up: Double,
    chance: Double,
    uniformChance: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
) = mutation(chance, parallelWorkersLimit) { chromosome ->
    mutationUniform(chromosome.value, low, up, uniformChance, random)
}

@JvmName("IntArray")
suspend fun <F> PanmicticLifecycle<IntArray, F>.mutUniform(
    low: Int,
    up: Int,
    chance: Double,
    uniformChance: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
) = mutation(chance, parallelWorkersLimit) { chromosome ->
    mutationUniform(chromosome.value, low, up, uniformChance, random)
}

@JvmName("LongArray")
suspend fun <F> PanmicticLifecycle<LongArray, F>.mutUniform(
    low: Long,
    up: Long,
    chance: Double,
    uniformChance: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
) = mutation(chance, parallelWorkersLimit) { chromosome ->
    mutationUniform(chromosome.value, low, up, uniformChance, random)
}
