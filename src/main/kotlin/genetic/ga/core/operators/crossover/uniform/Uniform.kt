package genetic.ga.core.operators.crossover.uniform

import genetic.utils.randomByChance
import kotlin.random.Random

private inline fun swapStrategy(size: Int, chance: Double, random: Random, swapper: (swapIndex: Int) -> Unit) =
    repeat(size) { randomByChance(chance, random) { swapper(it) } }

fun <T> crossoverUniform(value1: Array<T>, value2: Array<T>, chance: Double, random: Random) =
    swapStrategy(value1.size, chance, random) { swapIndex ->
        val temp = value1[swapIndex]
        value1[swapIndex] = value2[swapIndex]
        value2[swapIndex] = temp
    }

fun crossoverUniform(value1: BooleanArray, value2: BooleanArray, chance: Double, random: Random) =
    swapStrategy(value1.size, chance, random) { swapIndex ->
        val temp = value1[swapIndex]
        value1[swapIndex] = value2[swapIndex]
        value2[swapIndex] = temp
    }

fun crossoverUniform(value1: ByteArray, value2: ByteArray, chance: Double, random: Random) =
    swapStrategy(value1.size, chance, random) { swapIndex ->
        val temp = value1[swapIndex]
        value1[swapIndex] = value2[swapIndex]
        value2[swapIndex] = temp
    }

fun crossoverUniform(value1: CharArray, value2: CharArray, chance: Double, random: Random) =
    swapStrategy(value1.size, chance, random) { swapIndex ->
        val temp = value1[swapIndex]
        value1[swapIndex] = value2[swapIndex]
        value2[swapIndex] = temp
    }

fun crossoverUniform(value1: DoubleArray, value2: DoubleArray, chance: Double, random: Random) =
    swapStrategy(value1.size, chance, random) { swapIndex ->
        val temp = value1[swapIndex]
        value1[swapIndex] = value2[swapIndex]
        value2[swapIndex] = temp
    }

fun crossoverUniform(value1: FloatArray, value2: FloatArray, chance: Double, random: Random) =
    swapStrategy(value1.size, chance, random) { swapIndex ->
        val temp = value1[swapIndex]
        value1[swapIndex] = value2[swapIndex]
        value2[swapIndex] = temp
    }

fun crossoverUniform(value1: IntArray, value2: IntArray, chance: Double, random: Random) =
    swapStrategy(value1.size, chance, random) { swapIndex ->
        val temp = value1[swapIndex]
        value1[swapIndex] = value2[swapIndex]
        value2[swapIndex] = temp
    }

fun crossoverUniform(value1: LongArray, value2: LongArray, chance: Double, random: Random) =
    swapStrategy(value1.size, chance, random) { swapIndex ->
        val temp = value1[swapIndex]
        value1[swapIndex] = value2[swapIndex]
        value2[swapIndex] = temp
    }

fun <T> crossoverUniform(value1: MutableList<T>, value2: MutableList<T>, chance: Double, random: Random) =
    swapStrategy(value1.size, chance, random) { swapIndex ->
        val temp = value1[swapIndex]
        value1[swapIndex] = value2[swapIndex]
        value2[swapIndex] = temp
    }

fun crossoverUniform(value1: ShortArray, value2: ShortArray, chance: Double, random: Random) =
    swapStrategy(value1.size, chance, random) { swapIndex ->
        val temp = value1[swapIndex]
        value1[swapIndex] = value2[swapIndex]
        value2[swapIndex] = temp
    }
