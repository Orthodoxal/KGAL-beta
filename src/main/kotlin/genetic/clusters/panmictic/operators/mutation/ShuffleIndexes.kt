package genetic.clusters.panmictic.operators.mutation

import genetic.clusters.base.operators.mutation.shuffle_indexes.mutationShuffleIndexes
import genetic.clusters.panmictic.PanmicticLifecycle

suspend fun <T, F> PanmicticLifecycle<Array<T>, F>.mutShuffleIndexesArray(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

suspend fun <F> PanmicticLifecycle<BooleanArray, F>.mutShuffleIndexesBooleanArray(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

suspend fun <F> PanmicticLifecycle<ByteArray, F>.mutShuffleIndexesByteArray(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

suspend fun <F> PanmicticLifecycle<CharArray, F>.mutShuffleIndexesCharArray(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

suspend fun <F> PanmicticLifecycle<DoubleArray, F>.mutShuffleIndexesDoubleArray(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

suspend fun <F> PanmicticLifecycle<FloatArray, F>.mutShuffleIndexesFloatArray(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

suspend fun <F> PanmicticLifecycle<IntArray, F>.mutShuffleIndexesIntArray(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

suspend fun <F> PanmicticLifecycle<LongArray, F>.mutShuffleIndexesLongArray(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

suspend fun <T, F> PanmicticLifecycle<MutableList<T>, F>.mutShuffleIndexesBooleanMutableList(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

suspend fun <F> PanmicticLifecycle<ShortArray, F>.mutShuffleIndexesShortArray(
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}
