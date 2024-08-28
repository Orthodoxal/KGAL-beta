package genetic.ga.panmictic.operators.crossover

import genetic.ga.core.operators.crossover.k_point.*
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

@JvmName("cxKPointArray")
suspend fun <T, F> PanmicticLifecycle<Array<T>, F>.cxKPoint(
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

@JvmName("cxKPointBooleanArray")
suspend fun <F> PanmicticLifecycle<BooleanArray, F>.cxKPoint(
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

@JvmName("cxKPointByteArray")
suspend fun <F> PanmicticLifecycle<ByteArray, F>.cxKPoint(
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

@JvmName("cxKPointCharArray")
suspend fun <F> PanmicticLifecycle<CharArray, F>.cxKPoint(
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

@JvmName("cxKPointDoubleArray")
suspend fun <F> PanmicticLifecycle<DoubleArray, F>.cxKPoint(
    chance: Double = 0.9,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

@JvmName("cxKPointFloatArray")
suspend fun <F> PanmicticLifecycle<FloatArray, F>.cxKPoint(
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

@JvmName("cxKPointIntArray")
suspend fun <F> PanmicticLifecycle<IntArray, F>.cxKPoint(
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

@JvmName("cxKPointLongArray")
suspend fun <F> PanmicticLifecycle<LongArray, F>.cxKPoint(
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

@JvmName("cxKPointMutableList")
suspend fun <T, F> PanmicticLifecycle<MutableList<T>, F>.cxKPoint(
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

@JvmName("cxKPointShortArray")
suspend fun <F> PanmicticLifecycle<ShortArray, F>.cxKPoint(
    chance: Double,
    count: Int,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}
