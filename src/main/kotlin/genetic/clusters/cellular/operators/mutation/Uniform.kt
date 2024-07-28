package genetic.clusters.cellular.operators.mutation

import genetic.clusters.base.operators.mutation.uniform.mutationUniform
import genetic.clusters.cellular.lifecycle.CellLifecycle

fun <F> CellLifecycle<DoubleArray, F>.mutUniform(
    low: Double,
    up: Double,
    mutationChance: Double,
    mutationUniformChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationUniform(chromosome.value, low, up, mutationUniformChance, random)
}

fun <F> CellLifecycle<IntArray, F>.mutUniform(
    low: Int,
    up: Int,
    mutationChance: Double,
    mutationUniformChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationUniform(chromosome.value, low, up, mutationUniformChance, random)
}

fun <F> CellLifecycle<LongArray, F>.mutUniform(
    low: Long,
    up: Long,
    mutationChance: Double,
    mutationUniformChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationUniform(chromosome.value, low, up, mutationUniformChance, random)
}
