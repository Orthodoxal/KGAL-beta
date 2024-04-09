package genetic.ga.base_operators.crossover.blend

import genetic.chromosome.Chromosome
import kotlin.random.Random

internal fun crossoverBlendDoubleArray(
    chromosome1: Chromosome<DoubleArray, *>,
    chromosome2: Chromosome<DoubleArray, *>,
    alpha: Double,
    random: Random,
) {
    repeat(chromosome1.value.size) {
        val gamma = (1.0 + 2.0 * alpha) * random.nextDouble() - alpha
        chromosome1.value[it] = (1.0 - gamma) * chromosome1.value[it] + gamma * chromosome2.value[it]
        chromosome2.value[it] = gamma * chromosome1.value[it] + (1.0 - gamma) * chromosome2.value[it]
    }
}

internal fun crossoverBlendFloatArray(
    chromosome1: Chromosome<FloatArray, *>,
    chromosome2: Chromosome<FloatArray, *>,
    alpha: Float,
    random: Random,
) {
    repeat(chromosome1.value.size) {
        val gamma = (1f + 2f * alpha) * random.nextFloat() - alpha
        chromosome1.value[it] = (1f - gamma) * chromosome1.value[it] + gamma * chromosome2.value[it]
        chromosome2.value[it] = gamma * chromosome1.value[it] + (1f - gamma) * chromosome2.value[it]
    }
}
