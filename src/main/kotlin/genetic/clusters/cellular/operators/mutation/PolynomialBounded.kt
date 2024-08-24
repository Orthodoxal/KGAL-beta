package genetic.clusters.cellular.operators.mutation

import genetic.clusters.base.operators.mutation.polynomial_bounded.mutationPolynomialBounded
import genetic.clusters.cellular.lifecycle.CellLifecycle

fun <F> CellLifecycle<DoubleArray, F>.mutPolynomialBounded(
    eta: Double,
    low: Double,
    up: Double,
    mutationChance: Double,
    mutationPolynomialBoundedChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationPolynomialBounded(chromosome.value, eta, low, up, mutationPolynomialBoundedChance, random)
}
