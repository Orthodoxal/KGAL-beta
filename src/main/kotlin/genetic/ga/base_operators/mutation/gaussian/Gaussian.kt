package genetic.ga.base_operators.mutation.gaussian

import genetic.utils.randomByChance
import kotlin.random.Random

fun mutationGaussian(
    value: DoubleArray,
    mean: Double,
    stddev: Double,
    chance: Double,
    random: Random,
) = value.indices.forEach { i ->
    randomByChance(chance, random) { value[i] = java.util.Random().nextGaussian(mean, stddev) }
}
