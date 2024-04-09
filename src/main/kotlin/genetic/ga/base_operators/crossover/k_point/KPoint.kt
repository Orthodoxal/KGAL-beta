package genetic.ga.base_operators.crossover.k_point

import genetic.chromosome.Chromosome
import genetic.utils.indicesByRandom
import java.lang.UnsupportedOperationException
import kotlin.random.Random

internal fun multiCrossIndices(size: Int, count: Int, random: Random): IntArray {
    if (count < 1) throw UnsupportedOperationException("Count must be more than 0")
    return IntRange(1, size - 1).indicesByRandom(count, random)
}


internal fun <T, F> crossoverKPointArray(
    chromosome1: Chromosome<Array<T>, F>,
    chromosome2: Chromosome<Array<T>, F>,
    count: Int,
    random: Random,
) = geneSwap(
    chromosome1.value,
    chromosome2.value,
    multiCrossIndices(chromosome1.value.size, count, random),
)

internal fun <F> crossoverKPointBooleanArray(
    chromosome1: Chromosome<BooleanArray, F>,
    chromosome2: Chromosome<BooleanArray, F>,
    count: Int,
    random: Random,
) = geneSwap(
    chromosome1.value,
    chromosome2.value,
    multiCrossIndices(chromosome1.value.size, count, random),
)

internal fun <F> crossoverKPointByteArray(
    chromosome1: Chromosome<ByteArray, F>,
    chromosome2: Chromosome<ByteArray, F>,
    count: Int,
    random: Random,
) = geneSwap(
    chromosome1.value,
    chromosome2.value,
    multiCrossIndices(chromosome1.value.size, count, random),
)

internal fun <F> crossoverKPointCharArray(
    chromosome1: Chromosome<CharArray, F>,
    chromosome2: Chromosome<CharArray, F>,
    count: Int,
    random: Random,
) = geneSwap(
    chromosome1.value,
    chromosome2.value,
    multiCrossIndices(chromosome1.value.size, count, random),
)

internal fun <F> crossoverKPointDoubleArray(
    chromosome1: Chromosome<DoubleArray, F>,
    chromosome2: Chromosome<DoubleArray, F>,
    count: Int,
    random: Random,
) = geneSwap(
    chromosome1.value,
    chromosome2.value,
    multiCrossIndices(chromosome1.value.size, count, random),
)

internal fun <F> crossoverKPointFloatArray(
    chromosome1: Chromosome<FloatArray, F>,
    chromosome2: Chromosome<FloatArray, F>,
    count: Int,
    random: Random,
) = geneSwap(
    chromosome1.value,
    chromosome2.value,
    multiCrossIndices(chromosome1.value.size, count, random),
)

internal fun <F> crossoverKPointIntArray(
    chromosome1: Chromosome<IntArray, F>,
    chromosome2: Chromosome<IntArray, F>,
    count: Int,
    random: Random,
) = geneSwap(
    chromosome1.value,
    chromosome2.value,
    multiCrossIndices(chromosome1.value.size, count, random),
)

internal fun <F> crossoverKPointLongArray(
    chromosome1: Chromosome<LongArray, F>,
    chromosome2: Chromosome<LongArray, F>,
    count: Int,
    random: Random,
) = geneSwap(
    chromosome1.value,
    chromosome2.value,
    multiCrossIndices(chromosome1.value.size, count, random),
)

internal fun <T, F> crossoverKPointMutableList(
    chromosome1: Chromosome<MutableList<T>, F>,
    chromosome2: Chromosome<MutableList<T>, F>,
    count: Int,
    random: Random,
) = geneSwap(
    chromosome1.value,
    chromosome2.value,
    multiCrossIndices(chromosome1.value.size, count, random),
)

internal fun <F> crossoverKPointShortArray(
    chromosome1: Chromosome<ShortArray, F>,
    chromosome2: Chromosome<ShortArray, F>,
    count: Int,
    random: Random,
) = geneSwap(
    chromosome1.value,
    chromosome2.value,
    multiCrossIndices(chromosome1.value.size, count, random),
)
