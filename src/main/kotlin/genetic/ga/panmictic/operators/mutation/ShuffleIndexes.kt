package genetic.ga.panmictic.operators.mutation

import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.base_operators.mutation.shuffle_indexes.mutationShuffleIndexes
import genetic.ga.panmictic.builder.PanmicticGABuilder

suspend fun <T, F> SimpleClusterLifecycle<Array<T>, F>.mutationShuffleIndexesArray(
    panmicticGABuilder: PanmicticGABuilder<Array<T>, F>,
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(panmicticGABuilder, mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

suspend fun <F> SimpleClusterLifecycle<BooleanArray, F>.mutationShuffleIndexesBooleanArray(
    panmicticGABuilder: PanmicticGABuilder<BooleanArray, F>,
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(panmicticGABuilder, mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

suspend fun <F> SimpleClusterLifecycle<ByteArray, F>.mutationShuffleIndexesByteArray(
    panmicticGABuilder: PanmicticGABuilder<ByteArray, F>,
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(panmicticGABuilder, mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

suspend fun <F> SimpleClusterLifecycle<CharArray, F>.mutationShuffleIndexesCharArray(
    panmicticGABuilder: PanmicticGABuilder<CharArray, F>,
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(panmicticGABuilder, mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

suspend fun <F> SimpleClusterLifecycle<DoubleArray, F>.mutationShuffleIndexesDoubleArray(
    panmicticGABuilder: PanmicticGABuilder<DoubleArray, F>,
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(panmicticGABuilder, mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

suspend fun <F> SimpleClusterLifecycle<FloatArray, F>.mutationShuffleIndexesFloatArray(
    panmicticGABuilder: PanmicticGABuilder<FloatArray, F>,
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(panmicticGABuilder, mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

suspend fun <F> SimpleClusterLifecycle<IntArray, F>.mutationShuffleIndexesIntArray(
    panmicticGABuilder: PanmicticGABuilder<IntArray, F>,
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(panmicticGABuilder, mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

suspend fun <F> SimpleClusterLifecycle<LongArray, F>.mutationShuffleIndexesLongArray(
    panmicticGABuilder: PanmicticGABuilder<LongArray, F>,
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(panmicticGABuilder, mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

suspend fun <T, F> SimpleClusterLifecycle<MutableList<T>, F>.mutationShuffleIndexesBooleanMutableList(
    panmicticGABuilder: PanmicticGABuilder<MutableList<T>, F>,
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(panmicticGABuilder, mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

suspend fun <F> SimpleClusterLifecycle<ShortArray, F>.mutationShuffleIndexesShortArray(
    panmicticGABuilder: PanmicticGABuilder<ShortArray, F>,
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
    onlySingleRun: Boolean = false,
) = mutation(panmicticGABuilder, mutationChance, onlySingleRun) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}
