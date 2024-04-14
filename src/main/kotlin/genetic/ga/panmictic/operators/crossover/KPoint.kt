package genetic.ga.panmictic.operators.crossover

import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.base_operators.crossover.k_point.*
import genetic.ga.panmictic.builder.PanmicticGABuilder

suspend fun <T, F> SimpleClusterLifecycle<Array<T>, F>.crossoverKPointArray(
    panmicticGABuilder: PanmicticGABuilder<Array<T>, F>,
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPointArray(chromosome1.value, chromosome2.value, count, random)
}

suspend fun <F> SimpleClusterLifecycle<BooleanArray, F>.crossoverKPointBooleanArray(
    panmicticGABuilder: PanmicticGABuilder<BooleanArray, F>,
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPointBooleanArray(chromosome1.value, chromosome2.value, count, random)
}

suspend fun <F> SimpleClusterLifecycle<ByteArray, F>.crossoverKPointByteArray(
    panmicticGABuilder: PanmicticGABuilder<ByteArray, F>,
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPointByteArray(chromosome1.value, chromosome2.value, count, random)
}

suspend fun <F> SimpleClusterLifecycle<CharArray, F>.crossoverKPointCharArray(
    panmicticGABuilder: PanmicticGABuilder<CharArray, F>,
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPointCharArray(chromosome1.value, chromosome2.value, count, random)
}

suspend fun <F> SimpleClusterLifecycle<DoubleArray, F>.crossoverKPointDoubleArray(
    panmicticGABuilder: PanmicticGABuilder<DoubleArray, F>,
    chance: Double = 0.9,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPointDoubleArray(chromosome1.value, chromosome2.value, count, random)
}

suspend fun <F> SimpleClusterLifecycle<FloatArray, F>.crossoverKPointFloatArray(
    panmicticGABuilder: PanmicticGABuilder<FloatArray, F>,
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPointFloatArray(chromosome1.value, chromosome2.value, count, random)
}

suspend fun <F> SimpleClusterLifecycle<IntArray, F>.crossoverKPointIntArray(
    panmicticGABuilder: PanmicticGABuilder<IntArray, F>,
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPointIntArray(chromosome1.value, chromosome2.value, count, random)
}

suspend fun <F> SimpleClusterLifecycle<LongArray, F>.crossoverKPointLongArray(
    panmicticGABuilder: PanmicticGABuilder<LongArray, F>,
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPointLongArray(chromosome1.value, chromosome2.value, count, random)
}

suspend fun <T, F> SimpleClusterLifecycle<MutableList<T>, F>.crossoverKPointMutableList(
    panmicticGABuilder: PanmicticGABuilder<MutableList<T>, F>,
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPointMutableList(chromosome1.value, chromosome2.value, count, random)
}

suspend fun <F> SimpleClusterLifecycle<ShortArray, F>.crossoverKPointShortArray(
    panmicticGABuilder: PanmicticGABuilder<ShortArray, F>,
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPointShortArray(chromosome1.value, chromosome2.value, count, random)
}
