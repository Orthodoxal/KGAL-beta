package genetic.ga.cellular.operators.mutation

import genetic.ga.core.operators.mutation.polynomial_bounded.mutationPolynomialBounded
import genetic.ga.cellular.lifecycle.CellLifecycle

fun <F> CellLifecycle<DoubleArray, F>.mutPolynomialBounded(
    eta: Double,
    low: Double,
    up: Double,
    chance: Double,
    polynomialBoundedChance: Double,
) = mutation(chance) { chromosome ->
    mutationPolynomialBounded(chromosome.value, eta, low, up, polynomialBoundedChance, random)
}
