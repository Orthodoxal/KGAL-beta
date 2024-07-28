package genetic.clusters.base.operators.mutation.polynomial_bounded

import genetic.utils.randomByChance
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.random.Random

fun mutationPolynomialBounded(
    value: DoubleArray,
    eta: Double,
    low: Double,
    up: Double,
    chance: Double,
    random: Random,
) {
    val size = value.size
    val lowArray = DoubleArray(size) { low }
    val upArray = DoubleArray(size) { up }

    value.forEachIndexed { index, _ ->
        randomByChance(chance, random) {
            var x = value[index]
            val delta1 = (x - lowArray[index]) / (upArray[index] - lowArray[index])
            val delta2 = (upArray[index] - x) / (upArray[index] - lowArray[index])
            val rand = random.nextDouble()
            val mutPow = 1.0 / (eta + 1.0)

            val deltaQ = if (rand < 0.5) {
                val xy = 1.0 - delta1
                val valueR = 2.0 * rand + (1.0 - 2.0 * rand) * xy.pow(eta + 1.0)
                valueR.pow(mutPow) - 1.0
            } else {
                val xy = 1.0 - delta2
                val valueR = 2.0 * (1.0 - rand) + 2.0 * (rand - 0.5) * xy.pow(eta + 1.0)
                1.0 - valueR.pow(mutPow)
            }

            x += deltaQ * (upArray[index] - lowArray[index])
            x = min(max(x, lowArray[index]), upArray[index])
            value[index] = x
        }
    }
}
