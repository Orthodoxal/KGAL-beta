package genetic.clusters.base.operators.mutation.uniform

import genetic.utils.randomByChance
import kotlin.random.Random

fun mutationUniform(
    value: DoubleArray,
    low: Double,
    up: Double,
    chance: Double,
    random: Random,
) = value.indices.forEach { i -> randomByChance(chance, random) { value[i] = random.nextDouble(low, up) } }

fun mutationUniform(
    value: IntArray,
    low: Int,
    up: Int,
    chance: Double,
    random: Random,
) = value.indices.forEach { i -> randomByChance(chance, random) { value[i] = random.nextInt(low, up) } }

fun mutationUniform(
    value: LongArray,
    low: Long,
    up: Long,
    chance: Double,
    random: Random,
) = value.indices.forEach { i -> randomByChance(chance, random) { value[i] = random.nextLong(low, up) } }
