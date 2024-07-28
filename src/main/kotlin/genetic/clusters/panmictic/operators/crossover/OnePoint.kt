package genetic.clusters.panmictic.operators.crossover

import genetic.clusters.base.operators.crossover.one_point.*
import genetic.clusters.panmictic.PanmicticLifecycle

suspend fun <T, F> PanmicticLifecycle<Array<T>, F>.cxOnePointArray(
    chance: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

suspend fun <F> PanmicticLifecycle<BooleanArray, F>.cxOnePointBooleanArray(
    chance: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

suspend fun <F> PanmicticLifecycle<ByteArray, F>.cxOnePointByteArray(
    chance: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

suspend fun <F> PanmicticLifecycle<CharArray, F>.cxOnePointCharArray(
    chance: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

suspend fun <F> PanmicticLifecycle<DoubleArray, F>.cxOnePointDoubleArray(
    chance: Double = 0.9,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

suspend fun <F> PanmicticLifecycle<FloatArray, F>.cxOnePointFloatArray(
    chance: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

suspend fun <F> PanmicticLifecycle<IntArray, F>.cxOnePointIntArray(
    chance: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

suspend fun <F> PanmicticLifecycle<LongArray, F>.cxOnePointLongArray(
    chance: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

suspend fun <T, F> PanmicticLifecycle<MutableList<T>, F>.cxOnePointMutableList(
    chance: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

suspend fun <F> PanmicticLifecycle<ShortArray, F>.cxOnePointShortArray(
    chance: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}
