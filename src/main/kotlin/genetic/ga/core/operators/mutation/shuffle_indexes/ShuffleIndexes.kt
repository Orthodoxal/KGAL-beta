package genetic.ga.core.operators.mutation.shuffle_indexes

import genetic.utils.moreOrEquals
import kotlin.random.Random

inline fun shuffleIndexesHelper(
    size: Int,
    chance: Double,
    random: Random,
    mutateAll: () -> Unit,
    mutateSwapper: (swapIndex: Int, currentIndex: Int) -> Unit,
) {
    if (chance moreOrEquals 1.0) {
        mutateAll()
    } else {
        for (i in 0..<size) {
            var swapIndex = random.nextInt(0, size - 2)
            if (swapIndex == i) {
                swapIndex += 1
            }
            mutateSwapper(swapIndex, i)
        }
    }
}

fun <T> mutationShuffleIndexes(
    value: Array<T>,
    chance: Double,
    random: Random,
) = shuffleIndexesHelper(value.size, chance, random, { value.shuffle(random) }) { swapIndex, currentIndex ->
    val temp = value[currentIndex]
    value[currentIndex] = value[swapIndex]
    value[swapIndex] = temp
}

fun mutationShuffleIndexes(
    value: BooleanArray,
    chance: Double,
    random: Random,
) = shuffleIndexesHelper(value.size, chance, random, { value.shuffle(random) }) { swapIndex, currentIndex ->
    val temp = value[currentIndex]
    value[currentIndex] = value[swapIndex]
    value[swapIndex] = temp
}

fun mutationShuffleIndexes(
    value: ByteArray,
    chance: Double,
    random: Random,
) = shuffleIndexesHelper(value.size, chance, random, { value.shuffle(random) }) { swapIndex, currentIndex ->
    val temp = value[currentIndex]
    value[currentIndex] = value[swapIndex]
    value[swapIndex] = temp
}

fun mutationShuffleIndexes(
    value: CharArray,
    chance: Double,
    random: Random,
) = shuffleIndexesHelper(value.size, chance, random, { value.shuffle(random) }) { swapIndex, currentIndex ->
    val temp = value[currentIndex]
    value[currentIndex] = value[swapIndex]
    value[swapIndex] = temp
}

fun mutationShuffleIndexes(
    value: DoubleArray,
    chance: Double,
    random: Random,
) = shuffleIndexesHelper(value.size, chance, random, { value.shuffle(random) }) { swapIndex, currentIndex ->
    val temp = value[currentIndex]
    value[currentIndex] = value[swapIndex]
    value[swapIndex] = temp
}

fun mutationShuffleIndexes(
    value: FloatArray,
    chance: Double,
    random: Random,
) = shuffleIndexesHelper(value.size, chance, random, { value.shuffle(random) }) { swapIndex, currentIndex ->
    val temp = value[currentIndex]
    value[currentIndex] = value[swapIndex]
    value[swapIndex] = temp
}

fun mutationShuffleIndexes(
    value: IntArray,
    chance: Double,
    random: Random,
) = shuffleIndexesHelper(value.size, chance, random, { value.shuffle(random) }) { swapIndex, currentIndex ->
    val temp = value[currentIndex]
    value[currentIndex] = value[swapIndex]
    value[swapIndex] = temp
}

fun mutationShuffleIndexes(
    value: LongArray,
    chance: Double,
    random: Random,
) = shuffleIndexesHelper(value.size, chance, random, { value.shuffle(random) }) { swapIndex, currentIndex ->
    val temp = value[currentIndex]
    value[currentIndex] = value[swapIndex]
    value[swapIndex] = temp
}

fun <T> mutationShuffleIndexes(
    value: MutableList<T>,
    chance: Double,
    random: Random,
) = shuffleIndexesHelper(value.size, chance, random, { value.shuffle(random) }) { swapIndex, currentIndex ->
    val temp = value[currentIndex]
    value[currentIndex] = value[swapIndex]
    value[swapIndex] = temp
}

fun mutationShuffleIndexes(
    value: ShortArray,
    chance: Double,
    random: Random,
) = shuffleIndexesHelper(value.size, chance, random, { value.shuffle(random) }) { swapIndex, currentIndex ->
    val temp = value[currentIndex]
    value[currentIndex] = value[swapIndex]
    value[swapIndex] = temp
}
