package genetic.ga.base_operators.crossover.simulated_binary

import kotlin.math.pow
import kotlin.random.Random

fun crossoverSimulatedBinaryDoubleArray(
    value1: DoubleArray,
    value2: DoubleArray,
    eta: Double,
    random: Random
) {
    for (i in value1.indices) {
        val rand = random.nextDouble()
        var beta = if (rand <= 0.5) {
            2.0 * rand
        } else {
            1.0 / (2.0 * (1.0 - rand))
        }
        beta = beta.pow(1.0 / (eta + 1.0))

        value1[i] = 0.5 * (((1 + beta) * value1[i]) + ((1 - beta) * value2[i]))
        value2[i] = 0.5 * (((1 - beta) * value1[i]) + ((1 + beta) * value2[i]))
    }
}
