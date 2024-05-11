package genetic.ga.cellular.operators.mutation

import genetic.ga.base_operators.mutation.polynomial_bounded.mutationPolynomialBounded
import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle

fun <F> SimpleClusterCellLifecycle<DoubleArray, F>.mutationPolynomialBounded(
    cellularGABuilder: CellularGABuilder<DoubleArray, F>,
    eta: Double,
    low: Double,
    up: Double,
    mutationChance: Double,
    mutationPolynomialBoundedChance: Double,
) = mutation(cellularGABuilder, mutationChance) { chromosome ->
    mutationPolynomialBounded(chromosome.value, eta, low, up, mutationPolynomialBoundedChance, random)
}
