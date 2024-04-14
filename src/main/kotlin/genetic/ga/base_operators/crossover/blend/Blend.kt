package genetic.ga.base_operators.crossover.blend

import kotlin.random.Random

fun crossoverBlendDoubleArray(
    value1: DoubleArray,
    value2: DoubleArray,
    alpha: Double,
    random: Random,
) {
    repeat(value1.size) {
        val gamma = (1.0 + 2.0 * alpha) * random.nextDouble() - alpha
        value1[it] = (1.0 - gamma) * value1[it] + gamma * value2[it]
        value2[it] = gamma * value1[it] + (1.0 - gamma) * value2[it]
    }
}

fun crossoverBlendFloatArray(
    value1: FloatArray,
    value2: FloatArray,
    alpha: Float,
    random: Random,
) {
    repeat(value1.size) {
        val gamma = (1f + 2f * alpha) * random.nextFloat() - alpha
        value1[it] = (1f - gamma) * value1[it] + gamma * value2[it]
        value2[it] = gamma * value1[it] + (1f - gamma) * value2[it]
    }
}
