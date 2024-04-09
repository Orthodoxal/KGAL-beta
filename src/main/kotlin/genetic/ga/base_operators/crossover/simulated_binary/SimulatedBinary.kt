package genetic.ga.base_operators.crossover.simulated_binary

import genetic.chromosome.Chromosome
import kotlin.math.pow
import kotlin.random.Random

internal fun crossoverSimulatedBinaryDoubleArray(
    chromosome1: Chromosome<DoubleArray, *>,
    chromosome2: Chromosome<DoubleArray, *>,
    eta: Double,
    random: Random,
) {
    for (i in chromosome1.value.indices) {
        val rand = random.nextDouble()
        var beta = if (rand <= 0.5) {
            2.0 * rand
        } else {
            1.0 / (2.0 * (1.0 - rand))
        }
        beta = beta.pow(1.0 / (eta + 1.0))

        chromosome1.value[i] = 0.5 * (((1 + beta) * chromosome1.value[i]) + ((1 - beta) * chromosome2.value[i]))
        chromosome2.value[i] = 0.5 * (((1 - beta) * chromosome1.value[i]) + ((1 + beta) * chromosome2.value[i]))
    }
}
