package genetic.clusters.cellular.operators.mutation

import genetic.clusters.base.operators.mutation.shuffle_indexes.mutationShuffleIndexes
import genetic.clusters.cellular.lifecycle.CellLifecycle

fun <T, F> CellLifecycle<Array<T>, F>.mutShuffleIndexesArray(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

fun <F> CellLifecycle<BooleanArray, F>.mutShuffleIndexesBooleanArray(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

fun <F> CellLifecycle<ByteArray, F>.mutShuffleIndexesByteArray(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

fun <F> CellLifecycle<CharArray, F>.mutShuffleIndexesCharArray(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

fun <F> CellLifecycle<DoubleArray, F>.mutShuffleIndexesDoubleArray(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

fun <F> CellLifecycle<FloatArray, F>.mutShuffleIndexesFloatArray(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

fun <F> CellLifecycle<IntArray, F>.mutShuffleIndexesIntArray(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

fun <F> CellLifecycle<LongArray, F>.mutShuffleIndexesLongArray(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

fun <T, F> CellLifecycle<MutableList<T>, F>.mutShuffleIndexesMutableList(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

fun <F> CellLifecycle<ShortArray, F>.mutShuffleIndexesShortArray(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}
