package genetic.clusters.panmictic.operators.mutation

import genetic.clusters.base.operators.mutation.shuffle_indexes.mutationShuffleIndexes
import genetic.clusters.panmictic.PanmicticLifecycle

@JvmName("mutShuffleIndexesArray")
suspend fun <T, F> PanmicticLifecycle<Array<T>, F>.mutShuffleIndexes(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesBooleanArray")
suspend fun <F> PanmicticLifecycle<BooleanArray, F>.mutShuffleIndexes(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesByteArray")
suspend fun <F> PanmicticLifecycle<ByteArray, F>.mutShuffleIndexes(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesCharArray")
suspend fun <F> PanmicticLifecycle<CharArray, F>.mutShuffleIndexes(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesDoubleArray")
suspend fun <F> PanmicticLifecycle<DoubleArray, F>.mutShuffleIndexes(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesFloatArray")
suspend fun <F> PanmicticLifecycle<FloatArray, F>.mutShuffleIndexes(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesIntArray")
suspend fun <F> PanmicticLifecycle<IntArray, F>.mutShuffleIndexes(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesLongArray")
suspend fun <F> PanmicticLifecycle<LongArray, F>.mutShuffleIndexes(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesMutableList")
suspend fun <T, F> PanmicticLifecycle<MutableList<T>, F>.mutShuffleIndexes(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesShortArray")
suspend fun <F> PanmicticLifecycle<ShortArray, F>.mutShuffleIndexes(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}
