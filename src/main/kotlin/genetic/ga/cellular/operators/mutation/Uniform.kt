package genetic.ga.cellular.operators.mutation

import genetic.ga.core.operators.mutation.uniform.mutationUniform
import genetic.ga.cellular.lifecycle.CellLifecycle

@JvmName("mutUniformDoubleArray")
fun <F> CellLifecycle<DoubleArray, F>.mutUniform(
    low: Double,
    up: Double,
    chance: Double,
    uniformChance: Double,
) = mutation(chance) { chromosome ->
    mutationUniform(chromosome.value, low, up, uniformChance, random)
}

@JvmName("mutUniformIntArray")
fun <F> CellLifecycle<IntArray, F>.mutUniform(
    low: Int,
    up: Int,
    chance: Double,
    uniformChance: Double,
) = mutation(chance) { chromosome ->
    mutationUniform(chromosome.value, low, up, uniformChance, random)
}

@JvmName("mutUniformLongArray")
fun <F> CellLifecycle<LongArray, F>.mutUniform(
    low: Long,
    up: Long,
    chance: Double,
    uniformChance: Double,
) = mutation(chance) { chromosome ->
    mutationUniform(chromosome.value, low, up, uniformChance, random)
}
