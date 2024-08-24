package genetic.clusters.cellular.operators.mutation

import genetic.clusters.base.operators.mutation.shuffle_indexes.mutationShuffleIndexes
import genetic.clusters.cellular.lifecycle.CellLifecycle

@JvmName("mutShuffleIndexesArray")
fun <T, F> CellLifecycle<Array<T>, F>.mutShuffleIndexes(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesBooleanArray")
fun <F> CellLifecycle<BooleanArray, F>.mutShuffleIndexes(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesByteArray")
fun <F> CellLifecycle<ByteArray, F>.mutShuffleIndexes(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesCharArray")
fun <F> CellLifecycle<CharArray, F>.mutShuffleIndexes(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesDoubleArray")
fun <F> CellLifecycle<DoubleArray, F>.mutShuffleIndexes(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesFloatArray")
fun <F> CellLifecycle<FloatArray, F>.mutShuffleIndexes(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesIntArray")
fun <F> CellLifecycle<IntArray, F>.mutShuffleIndexes(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesLongArray")
fun <F> CellLifecycle<LongArray, F>.mutShuffleIndexes(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesMutableList")
fun <T, F> CellLifecycle<MutableList<T>, F>.mutShuffleIndexes(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesShortArray")
fun <F> CellLifecycle<ShortArray, F>.mutShuffleIndexes(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}
