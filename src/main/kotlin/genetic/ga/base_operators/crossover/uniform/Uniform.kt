package genetic.ga.base_operators.crossover.uniform

import genetic.chromosome.Chromosome
import genetic.utils.randomByChance
import kotlin.random.Random

private inline fun swapStrategy(size: Int, chance: Double, random: Random, swapper: (swapIndex: Int) -> Unit) =
    repeat(size) { randomByChance(chance, random) { swapper(it) } }

internal fun <T, F> crossoverUniformArray(
    chromosome1: Chromosome<Array<T>, F>,
    chromosome2: Chromosome<Array<T>, F>,
    chance: Double,
    random: Random,
) = swapStrategy(chromosome1.value.size, chance, random) { swapIndex ->
    val temp = chromosome1.value[swapIndex]
    chromosome1.value[swapIndex] = chromosome2.value[swapIndex]
    chromosome2.value[swapIndex] = temp
}

internal fun <F> crossoverUniformBooleanArray(
    chromosome1: Chromosome<BooleanArray, F>,
    chromosome2: Chromosome<BooleanArray, F>,
    chance: Double,
    random: Random,
) = swapStrategy(chromosome1.value.size, chance, random) { swapIndex ->
    val temp = chromosome1.value[swapIndex]
    chromosome1.value[swapIndex] = chromosome2.value[swapIndex]
    chromosome2.value[swapIndex] = temp
}

internal fun <F> crossoverUniformByteArray(
    chromosome1: Chromosome<ByteArray, F>,
    chromosome2: Chromosome<ByteArray, F>,
    chance: Double,
    random: Random,
) = swapStrategy(chromosome1.value.size, chance, random) { swapIndex ->
    val temp = chromosome1.value[swapIndex]
    chromosome1.value[swapIndex] = chromosome2.value[swapIndex]
    chromosome2.value[swapIndex] = temp
}

internal fun <F> crossoverUniformCharArray(
    chromosome1: Chromosome<CharArray, F>,
    chromosome2: Chromosome<CharArray, F>,
    chance: Double,
    random: Random,
) = swapStrategy(chromosome1.value.size, chance, random) { swapIndex ->
    val temp = chromosome1.value[swapIndex]
    chromosome1.value[swapIndex] = chromosome2.value[swapIndex]
    chromosome2.value[swapIndex] = temp
}

internal fun <F> crossoverUniformDoubleArray(
    chromosome1: Chromosome<DoubleArray, F>,
    chromosome2: Chromosome<DoubleArray, F>,
    chance: Double,
    random: Random,
) = swapStrategy(chromosome1.value.size, chance, random) { swapIndex ->
    val temp = chromosome1.value[swapIndex]
    chromosome1.value[swapIndex] = chromosome2.value[swapIndex]
    chromosome2.value[swapIndex] = temp
}

internal fun <F> crossoverUniformFloatArray(
    chromosome1: Chromosome<FloatArray, F>,
    chromosome2: Chromosome<FloatArray, F>,
    chance: Double,
    random: Random,
) = swapStrategy(chromosome1.value.size, chance, random) { swapIndex ->
    val temp = chromosome1.value[swapIndex]
    chromosome1.value[swapIndex] = chromosome2.value[swapIndex]
    chromosome2.value[swapIndex] = temp
}

internal fun <F> crossoverUniformIntArray(
    chromosome1: Chromosome<IntArray, F>,
    chromosome2: Chromosome<IntArray, F>,
    chance: Double,
    random: Random,
) = swapStrategy(chromosome1.value.size, chance, random) { swapIndex ->
    val temp = chromosome1.value[swapIndex]
    chromosome1.value[swapIndex] = chromosome2.value[swapIndex]
    chromosome2.value[swapIndex] = temp
}

internal fun <F> crossoverUniformLongArray(
    chromosome1: Chromosome<LongArray, F>,
    chromosome2: Chromosome<LongArray, F>,
    chance: Double,
    random: Random,
) = swapStrategy(chromosome1.value.size, chance, random) { swapIndex ->
    val temp = chromosome1.value[swapIndex]
    chromosome1.value[swapIndex] = chromosome2.value[swapIndex]
    chromosome2.value[swapIndex] = temp
}

internal fun <T, F> crossoverUniformMutableList(
    chromosome1: Chromosome<MutableList<T>, F>,
    chromosome2: Chromosome<MutableList<T>, F>,
    chance: Double,
    random: Random,
) = swapStrategy(chromosome1.value.size, chance, random) { swapIndex ->
    val temp = chromosome1.value[swapIndex]
    chromosome1.value[swapIndex] = chromosome2.value[swapIndex]
    chromosome2.value[swapIndex] = temp
}

internal fun <F> crossoverUniformShortArray(
    chromosome1: Chromosome<ShortArray, F>,
    chromosome2: Chromosome<ShortArray, F>,
    chance: Double,
    random: Random,
) = swapStrategy(chromosome1.value.size, chance, random) { swapIndex ->
    val temp = chromosome1.value[swapIndex]
    chromosome1.value[swapIndex] = chromosome2.value[swapIndex]
    chromosome2.value[swapIndex] = temp
}
