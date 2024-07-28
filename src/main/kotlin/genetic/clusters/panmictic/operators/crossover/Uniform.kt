package genetic.clusters.panmictic.operators.crossover

import genetic.clusters.base.operators.crossover.uniform.*
import genetic.clusters.panmictic.PanmicticLifecycle

suspend fun <T, F> PanmicticLifecycle<Array<T>, F>.cxUniformArray(
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

suspend fun <F> PanmicticLifecycle<BooleanArray, F>.cxUniformBooleanArray(
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

suspend fun <F> PanmicticLifecycle<ByteArray, F>.cxUniformByteArray(
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

suspend fun <F> PanmicticLifecycle<CharArray, F>.cxUniformCharArray(
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

suspend fun <F> PanmicticLifecycle<DoubleArray, F>.cxUniformDoubleArray(
    chance: Double = 0.9,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

suspend fun <F> PanmicticLifecycle<FloatArray, F>.cxUniformFloatArray(
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

suspend fun <F> PanmicticLifecycle<IntArray, F>.cxUniformIntArray(
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

suspend fun <F> PanmicticLifecycle<LongArray, F>.cxUniformLongArray(
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

suspend fun <T, F> PanmicticLifecycle<MutableList<T>, F>.cxUniformMutableList(
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

suspend fun <F> PanmicticLifecycle<ShortArray, F>.cxUniformShortArray(
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}
