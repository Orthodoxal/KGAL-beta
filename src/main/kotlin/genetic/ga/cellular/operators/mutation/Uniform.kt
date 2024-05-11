package genetic.ga.cellular.operators.mutation

import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle
import genetic.ga.base_operators.mutation.uniform.mutationUniform

fun <F> SimpleClusterCellLifecycle<DoubleArray, F>.mutationUniform(
    cellularGABuilder: CellularGABuilder<DoubleArray, F>,
    low: Double,
    up: Double,
    mutationChance: Double,
    mutationUniformChance: Double,
) = mutation(cellularGABuilder, mutationChance) { chromosome ->
    mutationUniform(chromosome.value, low, up, mutationUniformChance, random)
}

fun <F> SimpleClusterCellLifecycle<IntArray, F>.mutationUniform(
    cellularGABuilder: CellularGABuilder<IntArray, F>,
    low: Int,
    up: Int,
    mutationChance: Double,
    mutationUniformChance: Double,
) = mutation(cellularGABuilder, mutationChance) { chromosome ->
    mutationUniform(chromosome.value, low, up, mutationUniformChance, random)
}

fun <F> SimpleClusterCellLifecycle<LongArray, F>.mutationUniform(
    cellularGABuilder: CellularGABuilder<LongArray, F>,
    low: Long,
    up: Long,
    mutationChance: Double,
    mutationUniformChance: Double,
) = mutation(cellularGABuilder, mutationChance) { chromosome ->
    mutationUniform(chromosome.value, low, up, mutationUniformChance, random)
}
