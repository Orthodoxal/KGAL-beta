package genetic.clusters.base.operators.crossover.one_point

import kotlin.random.Random

fun <T> crossoverOnePoint(value1: Array<T>, value2: Array<T>, random: Random) =
    geneSwap(value1, value2, random.nextInt(1, value1.lastIndex))

fun crossoverOnePoint(value1: BooleanArray, value2: BooleanArray, random: Random) =
    geneSwap(value1, value2, random.nextInt(1, value1.lastIndex))

fun crossoverOnePoint(value1: ByteArray, value2: ByteArray, random: Random) =
    geneSwap(value1, value2, random.nextInt(1, value1.lastIndex))

fun crossoverOnePoint(value1: CharArray, value2: CharArray, random: Random) =
    geneSwap(value1, value2, random.nextInt(1, value1.lastIndex))

fun crossoverOnePoint(value1: DoubleArray, value2: DoubleArray, random: Random) =
    geneSwap(value1, value2, random.nextInt(1, value1.lastIndex))

fun crossoverOnePoint(value1: FloatArray, value2: FloatArray, random: Random) =
    geneSwap(value1, value2, random.nextInt(1, value1.lastIndex))

fun crossoverOnePoint(value1: IntArray, value2: IntArray, random: Random) =
    geneSwap(value1, value2, random.nextInt(1, value1.lastIndex))

fun crossoverOnePoint(value1: LongArray, value2: LongArray, random: Random) =
    geneSwap(value1, value2, random.nextInt(1, value1.lastIndex))

fun <T> crossoverOnePoint(value1: MutableList<T>, value2: MutableList<T>, random: Random) =
    geneSwap(value1, value2, random.nextInt(1, value1.lastIndex))

fun crossoverOnePoint(value1: ShortArray, value2: ShortArray, random: Random) =
    geneSwap(value1, value2, random.nextInt(1, value1.lastIndex))
