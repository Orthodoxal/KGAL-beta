package genetic.ga.core.operators.mutation.gaussian

import genetic.utils.nextGaussian
import genetic.utils.randomByChance
import kotlin.random.Random

fun mutationGaussian(
    value: DoubleArray,
    mean: Double,
    stddev: Double,
    chance: Double,
    random: Random,
) = value.indices.forEach { i ->
    randomByChance(chance, random) { value[i] = random.nextGaussian(mean, stddev) }
}
