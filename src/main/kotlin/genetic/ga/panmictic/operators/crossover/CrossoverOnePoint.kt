package genetic.ga.panmictic.operators.crossover

import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.base_operators.crossover.one_point.*
import genetic.ga.base_operators.crossover.one_point.crossoverOnePointBooleanArray
import genetic.ga.panmictic.builder.PanmicticGABuilder

suspend fun <T, F> SimpleClusterLifecycle<Array<T>, F>.crossoverOnePointArray(
    panmicticGABuilder: PanmicticGABuilder<Array<T>, F>,
    chance: Double = 0.9,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOnePointArray(chromosome1, chromosome2, random)
}

suspend fun <F> SimpleClusterLifecycle<BooleanArray, F>.crossoverOnePointBooleanArray(
    panmicticGABuilder: PanmicticGABuilder<BooleanArray, F>,
    chance: Double = 0.9,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOnePointBooleanArray(chromosome1, chromosome2, random)
}

suspend fun <F> SimpleClusterLifecycle<ByteArray, F>.crossoverOnePointByteArray(
    panmicticGABuilder: PanmicticGABuilder<ByteArray, F>,
    chance: Double = 0.9,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOnePointByteArray(chromosome1, chromosome2, random)
}

suspend fun <F> SimpleClusterLifecycle<CharArray, F>.crossoverOnePointCharArray(
    panmicticGABuilder: PanmicticGABuilder<CharArray, F>,
    chance: Double = 0.9,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOnePointCharArray(chromosome1, chromosome2, random)
}

suspend fun <F> SimpleClusterLifecycle<DoubleArray, F>.crossoverOnePointDoubleArray(
    panmicticGABuilder: PanmicticGABuilder<DoubleArray, F>,
    chance: Double = 0.9,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOnePointDoubleArray(chromosome1, chromosome2, random)
}

suspend fun <F> SimpleClusterLifecycle<FloatArray, F>.crossoverOnePointFloatArray(
    panmicticGABuilder: PanmicticGABuilder<FloatArray, F>,
    chance: Double = 0.9,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOnePointFloatArray(chromosome1, chromosome2, random)
}

suspend fun <F> SimpleClusterLifecycle<IntArray, F>.crossoverOnePointIntArray(
    panmicticGABuilder: PanmicticGABuilder<IntArray, F>,
    chance: Double = 0.9,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOnePointIntArray(chromosome1, chromosome2, random)
}

suspend fun <F> SimpleClusterLifecycle<LongArray, F>.crossoverOnePointLongArray(
    panmicticGABuilder: PanmicticGABuilder<LongArray, F>,
    chance: Double = 0.9,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOnePointLongArray(chromosome1, chromosome2, random)
}

suspend fun <T, F> SimpleClusterLifecycle<MutableList<T>, F>.crossoverOnePointMutableList(
    panmicticGABuilder: PanmicticGABuilder<MutableList<T>, F>,
    chance: Double = 0.9,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOnePointMutableList(chromosome1, chromosome2, random)
}

suspend fun <F> SimpleClusterLifecycle<ShortArray, F>.crossoverOnePointShortArray(
    panmicticGABuilder: PanmicticGABuilder<ShortArray, F>,
    chance: Double = 0.9,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOnePointShortArray(chromosome1, chromosome2, random)
}
