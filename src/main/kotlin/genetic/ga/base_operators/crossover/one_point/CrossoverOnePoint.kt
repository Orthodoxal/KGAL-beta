package genetic.ga.base_operators.crossover.one_point

import genetic.chromosome.Chromosome
import kotlin.random.Random

internal fun <F> crossoverOnePointBooleanArray(
    chromosome1: Chromosome<BooleanArray, F>,
    chromosome2: Chromosome<BooleanArray, F>,
    random: Random = Random,
) = geneSwap(chromosome1.value, chromosome2.value, random.nextInt(1, chromosome1.value.lastIndex))

internal fun <F> crossoverOnePointIntArray(
    chromosome1: Chromosome<IntArray, F>,
    chromosome2: Chromosome<IntArray, F>,
    random: Random = Random,
) = geneSwap(chromosome1.value, chromosome2.value, random.nextInt(1, chromosome1.value.lastIndex))
