package genetic.ga.panmictic.operators.mutation

import genetic.ga.core.operators.mutation.shuffle_indexes.mutationShuffleIndexes
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

@JvmName("mutShuffleIndexesArray")
suspend fun <T, F> PanmicticLifecycle<Array<T>, F>.mutShuffleIndexes(
    chance: Double,
    shuffleIndexesChance: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
) = mutation(chance, parallelWorkersLimit) { chromosome ->
    mutationShuffleIndexes(chromosome.value, shuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesBooleanArray")
suspend fun <F> PanmicticLifecycle<BooleanArray, F>.mutShuffleIndexes(
    chance: Double,
    shuffleIndexesChance: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
) = mutation(chance, parallelWorkersLimit) { chromosome ->
    mutationShuffleIndexes(chromosome.value, shuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesByteArray")
suspend fun <F> PanmicticLifecycle<ByteArray, F>.mutShuffleIndexes(
    chance: Double,
    shuffleIndexesChance: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
) = mutation(chance, parallelWorkersLimit) { chromosome ->
    mutationShuffleIndexes(chromosome.value, shuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesCharArray")
suspend fun <F> PanmicticLifecycle<CharArray, F>.mutShuffleIndexes(
    chance: Double,
    shuffleIndexesChance: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
) = mutation(chance, parallelWorkersLimit) { chromosome ->
    mutationShuffleIndexes(chromosome.value, shuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesDoubleArray")
suspend fun <F> PanmicticLifecycle<DoubleArray, F>.mutShuffleIndexes(
    chance: Double,
    shuffleIndexesChance: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
) = mutation(chance, parallelWorkersLimit) { chromosome ->
    mutationShuffleIndexes(chromosome.value, shuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesFloatArray")
suspend fun <F> PanmicticLifecycle<FloatArray, F>.mutShuffleIndexes(
    chance: Double,
    shuffleIndexesChance: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
) = mutation(chance, parallelWorkersLimit) { chromosome ->
    mutationShuffleIndexes(chromosome.value, shuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesIntArray")
suspend fun <F> PanmicticLifecycle<IntArray, F>.mutShuffleIndexes(
    chance: Double,
    shuffleIndexesChance: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
) = mutation(chance, parallelWorkersLimit) { chromosome ->
    mutationShuffleIndexes(chromosome.value, shuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesLongArray")
suspend fun <F> PanmicticLifecycle<LongArray, F>.mutShuffleIndexes(
    chance: Double,
    shuffleIndexesChance: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
) = mutation(chance, parallelWorkersLimit) { chromosome ->
    mutationShuffleIndexes(chromosome.value, shuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesMutableList")
suspend fun <T, F> PanmicticLifecycle<MutableList<T>, F>.mutShuffleIndexes(
    chance: Double,
    shuffleIndexesChance: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
) = mutation(chance, parallelWorkersLimit) { chromosome ->
    mutationShuffleIndexes(chromosome.value, shuffleIndexesChance, random)
}

@JvmName("mutShuffleIndexesShortArray")
suspend fun <F> PanmicticLifecycle<ShortArray, F>.mutShuffleIndexes(
    chance: Double,
    shuffleIndexesChance: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
) = mutation(chance, parallelWorkersLimit) { chromosome ->
    mutationShuffleIndexes(chromosome.value, shuffleIndexesChance, random)
}
