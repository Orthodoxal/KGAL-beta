package genetic.ga.base_operators.crossover.one_point

import kotlin.random.Random

fun <T> crossoverOnePointArray(value1: Array<T>, value2: Array<T>, random: Random) =
    geneSwap(value1, value2, random.nextInt(1, value1.lastIndex))

fun crossoverOnePointBooleanArray(value1: BooleanArray, value2: BooleanArray, random: Random) =
    geneSwap(value1, value2, random.nextInt(1, value1.lastIndex))

fun crossoverOnePointByteArray(value1: ByteArray, value2: ByteArray, random: Random) =
    geneSwap(value1, value2, random.nextInt(1, value1.lastIndex))

fun crossoverOnePointCharArray(value1: CharArray, value2: CharArray, random: Random) =
    geneSwap(value1, value2, random.nextInt(1, value1.lastIndex))

fun crossoverOnePointDoubleArray(value1: DoubleArray, value2: DoubleArray, random: Random) =
    geneSwap(value1, value2, random.nextInt(1, value1.lastIndex))

fun crossoverOnePointFloatArray(value1: FloatArray, value2: FloatArray, random: Random) =
    geneSwap(value1, value2, random.nextInt(1, value1.lastIndex))

fun crossoverOnePointIntArray(value1: IntArray, value2: IntArray, random: Random) =
    geneSwap(value1, value2, random.nextInt(1, value1.lastIndex))

fun crossoverOnePointLongArray(value1: LongArray, value2: LongArray, random: Random) =
    geneSwap(value1, value2, random.nextInt(1, value1.lastIndex))

fun <T> crossoverOnePointMutableList(value1: MutableList<T>, value2: MutableList<T>, random: Random) =
    geneSwap(value1, value2, random.nextInt(1, value1.lastIndex))

fun crossoverOnePointShortArray(value1: ShortArray, value2: ShortArray, random: Random) =
    geneSwap(value1, value2, random.nextInt(1, value1.lastIndex))
