package genetic.ga.base_operators.crossover.one_point

import genetic.chromosome.Chromosome
import kotlin.random.Random

internal fun <T, F> crossoverOnePointArray(
    chromosome1: Chromosome<Array<T>, F>,
    chromosome2: Chromosome<Array<T>, F>,
    random: Random,
) = geneSwap(chromosome1.value, chromosome2.value, random.nextInt(1, chromosome1.value.lastIndex))

internal fun <F> crossoverOnePointBooleanArray(
    chromosome1: Chromosome<BooleanArray, F>,
    chromosome2: Chromosome<BooleanArray, F>,
    random: Random,
) = geneSwap(chromosome1.value, chromosome2.value, random.nextInt(1, chromosome1.value.lastIndex))

internal fun <F> crossoverOnePointByteArray(
    chromosome1: Chromosome<ByteArray, F>,
    chromosome2: Chromosome<ByteArray, F>,
    random: Random,
) = geneSwap(chromosome1.value, chromosome2.value, random.nextInt(1, chromosome1.value.lastIndex))

internal fun <F> crossoverOnePointCharArray(
    chromosome1: Chromosome<CharArray, F>,
    chromosome2: Chromosome<CharArray, F>,
    random: Random,
) = geneSwap(chromosome1.value, chromosome2.value, random.nextInt(1, chromosome1.value.lastIndex))

internal fun <F> crossoverOnePointDoubleArray(
    chromosome1: Chromosome<DoubleArray, F>,
    chromosome2: Chromosome<DoubleArray, F>,
    random: Random,
) = geneSwap(chromosome1.value, chromosome2.value, random.nextInt(1, chromosome1.value.lastIndex))

internal fun <F> crossoverOnePointFloatArray(
    chromosome1: Chromosome<FloatArray, F>,
    chromosome2: Chromosome<FloatArray, F>,
    random: Random,
) = geneSwap(chromosome1.value, chromosome2.value, random.nextInt(1, chromosome1.value.lastIndex))

internal fun <F> crossoverOnePointIntArray(
    chromosome1: Chromosome<IntArray, F>,
    chromosome2: Chromosome<IntArray, F>,
    random: Random,
) = geneSwap(chromosome1.value, chromosome2.value, random.nextInt(1, chromosome1.value.lastIndex))

internal fun <F> crossoverOnePointLongArray(
    chromosome1: Chromosome<LongArray, F>,
    chromosome2: Chromosome<LongArray, F>,
    random: Random,
) = geneSwap(chromosome1.value, chromosome2.value, random.nextInt(1, chromosome1.value.lastIndex))

internal fun <T, F> crossoverOnePointMutableList(
    chromosome1: Chromosome<MutableList<T>, F>,
    chromosome2: Chromosome<MutableList<T>, F>,
    random: Random,
) = geneSwap(chromosome1.value, chromosome2.value, random.nextInt(1, chromosome1.value.lastIndex))

internal fun <F> crossoverOnePointShortArray(
    chromosome1: Chromosome<ShortArray, F>,
    chromosome2: Chromosome<ShortArray, F>,
    random: Random,
) = geneSwap(chromosome1.value, chromosome2.value, random.nextInt(1, chromosome1.value.lastIndex))
