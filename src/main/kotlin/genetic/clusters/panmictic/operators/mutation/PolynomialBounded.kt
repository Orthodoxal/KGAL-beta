package genetic.clusters.panmictic.operators.mutation

import genetic.clusters.base.operators.mutation.polynomial_bounded.mutationPolynomialBounded
import genetic.clusters.panmictic.PanmicticLifecycle

suspend fun <F> PanmicticLifecycle<DoubleArray, F>.mutPolynomialBounded(
    eta: Double,
    low: Double,
    up: Double,
    mutationChance: Double,
    mutationPolynomialBoundedChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationPolynomialBounded(chromosome.value, eta, low, up, mutationPolynomialBoundedChance, random)
}
