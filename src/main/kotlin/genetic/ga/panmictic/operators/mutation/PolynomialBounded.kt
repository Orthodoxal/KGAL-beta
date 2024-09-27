package genetic.ga.panmictic.operators.mutation

import genetic.ga.core.operators.mutation.polynomial_bounded.mutationPolynomialBounded
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

suspend fun <F> PanmicticLifecycle<DoubleArray, F>.mutPolynomialBounded(
    eta: Double,
    low: Double,
    up: Double,
    chance: Double,
    polynomialBoundedChance: Double,
    parallelismLimit: Int = parallelismConfig.workersCount,
) = mutation(chance, parallelismLimit) { chromosome, random ->
    mutationPolynomialBounded(chromosome.value, eta, low, up, polynomialBoundedChance, random)
}
