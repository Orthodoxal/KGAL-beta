package genetic.ga.panmictic.operators.mutation

import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.base_operators.mutation.polynomial_bounded.mutationPolynomialBounded
import genetic.ga.panmictic.builder.PanmicticGABuilder

suspend fun <F> SimpleClusterLifecycle<DoubleArray, F>.mutationPolynomialBounded(
    panmicticGABuilder: PanmicticGABuilder<DoubleArray, F>,
    eta: Double,
    low: Double,
    up: Double,
    mutationChance: Double,
    mutationPolynomialBoundedChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(panmicticGABuilder, mutationChance, onlySingleRun) { chromosome ->
    mutationPolynomialBounded(chromosome.value, eta, low, up, mutationPolynomialBoundedChance, random)
}
