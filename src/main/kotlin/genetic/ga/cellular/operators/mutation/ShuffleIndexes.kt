package genetic.ga.cellular.operators.mutation

import genetic.ga.core.operators.mutation.shuffle_indexes.mutationShuffleIndexes
import genetic.ga.cellular.lifecycle.CellLifecycle

@JvmName("mutShuffleIndexesArray")
fun <T, F> CellLifecycle<Array<T>, F>.mutShuffleIndexes(
    chance: Double,
    shuffleIndexesChance: Double,
) = mutation(chance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, shuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesBooleanArray")
fun <F> CellLifecycle<BooleanArray, F>.mutShuffleIndexes(
    chance: Double,
    shuffleIndexesChance: Double,
) = mutation(chance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, shuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesByteArray")
fun <F> CellLifecycle<ByteArray, F>.mutShuffleIndexes(
    chance: Double,
    shuffleIndexesChance: Double,
) = mutation(chance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, shuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesCharArray")
fun <F> CellLifecycle<CharArray, F>.mutShuffleIndexes(
    chance: Double,
    shuffleIndexesChance: Double,
) = mutation(chance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, shuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesDoubleArray")
fun <F> CellLifecycle<DoubleArray, F>.mutShuffleIndexes(
    chance: Double,
    shuffleIndexesChance: Double,
) = mutation(chance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, shuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesFloatArray")
fun <F> CellLifecycle<FloatArray, F>.mutShuffleIndexes(
    chance: Double,
    shuffleIndexesChance: Double,
) = mutation(chance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, shuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesIntArray")
fun <F> CellLifecycle<IntArray, F>.mutShuffleIndexes(
    chance: Double,
    shuffleIndexesChance: Double,
) = mutation(chance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, shuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesLongArray")
fun <F> CellLifecycle<LongArray, F>.mutShuffleIndexes(
    chance: Double,
    shuffleIndexesChance: Double,
) = mutation(chance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, shuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesMutableList")
fun <T, F> CellLifecycle<MutableList<T>, F>.mutShuffleIndexes(
    chance: Double,
    shuffleIndexesChance: Double,
) = mutation(chance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, shuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesShortArray")
fun <F> CellLifecycle<ShortArray, F>.mutShuffleIndexes(
    chance: Double,
    shuffleIndexesChance: Double,
) = mutation(chance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, shuffleIndexesChance, random)
}
