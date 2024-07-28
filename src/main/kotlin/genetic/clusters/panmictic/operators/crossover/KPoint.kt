package genetic.clusters.panmictic.operators.crossover

import genetic.clusters.base.operators.crossover.k_point.*
import genetic.clusters.panmictic.PanmicticLifecycle

suspend fun <T, F> PanmicticLifecycle<Array<T>, F>.cxKPointArray(
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

suspend fun <F> PanmicticLifecycle<BooleanArray, F>.cxKPointBooleanArray(
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

suspend fun <F> PanmicticLifecycle<ByteArray, F>.cxKPointByteArray(
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

suspend fun <F> PanmicticLifecycle<CharArray, F>.cxKPointCharArray(
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

suspend fun <F> PanmicticLifecycle<DoubleArray, F>.cxKPointDoubleArray(
    chance: Double = 0.9,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

suspend fun <F> PanmicticLifecycle<FloatArray, F>.cxKPointFloatArray(
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

suspend fun <F> PanmicticLifecycle<IntArray, F>.cxKPointIntArray(
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

suspend fun <F> PanmicticLifecycle<LongArray, F>.cxKPointLongArray(
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

suspend fun <T, F> PanmicticLifecycle<MutableList<T>, F>.cxKPointMutableList(
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

suspend fun <F> PanmicticLifecycle<ShortArray, F>.cxKPointShortArray(
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}
