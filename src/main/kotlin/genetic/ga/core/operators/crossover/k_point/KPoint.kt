package genetic.ga.core.operators.crossover.k_point

import genetic.utils.indicesByRandom
import kotlin.random.Random

fun multiCrossIndices(size: Int, count: Int, random: Random): IntArray {
    if (count < 1) throw UnsupportedOperationException("Count must be more than 0")
    return IntRange(1, size - 1).indicesByRandom(count, random)
}

fun <T> crossoverKPoint(value1: Array<T>, value2: Array<T>, count: Int, random: Random) =
    geneSwap(value1, value2, multiCrossIndices(value1.size, count, random))

fun crossoverKPoint(value1: BooleanArray, value2: BooleanArray, count: Int, random: Random) =
    geneSwap(value1, value2, multiCrossIndices(value1.size, count, random))

fun crossoverKPoint(value1: ByteArray, value2: ByteArray, count: Int, random: Random) =
    geneSwap(value1, value2, multiCrossIndices(value1.size, count, random))

fun crossoverKPoint(value1: CharArray, value2: CharArray, count: Int, random: Random) =
    geneSwap(value1, value2, multiCrossIndices(value1.size, count, random))

fun crossoverKPoint(value1: DoubleArray, value2: DoubleArray, count: Int, random: Random) =
    geneSwap(value1, value2, multiCrossIndices(value1.size, count, random))

fun crossoverKPoint(value1: FloatArray, value2: FloatArray, count: Int, random: Random) =
    geneSwap(value1, value2, multiCrossIndices(value1.size, count, random))

fun crossoverKPoint(value1: IntArray, value2: IntArray, count: Int, random: Random) =
    geneSwap(value1, value2, multiCrossIndices(value1.size, count, random))

fun crossoverKPoint(value1: LongArray, value2: LongArray, count: Int, random: Random) =
    geneSwap(value1, value2, multiCrossIndices(value1.size, count, random))

fun <T> crossoverKPoint(value1: MutableList<T>, value2: MutableList<T>, count: Int, random: Random) =
    geneSwap(value1, value2, multiCrossIndices(value1.size, count, random))

fun crossoverKPoint(value1: ShortArray, value2: ShortArray, count: Int, random: Random) =
    geneSwap(value1, value2, multiCrossIndices(value1.size, count, random))
