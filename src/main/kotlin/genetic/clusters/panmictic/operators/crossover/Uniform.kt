package genetic.clusters.panmictic.operators.crossover

import genetic.clusters.base.operators.crossover.uniform.*
import genetic.clusters.panmictic.PanmicticLifecycle

@JvmName("cxUniformArray")
suspend fun <T, F> PanmicticLifecycle<Array<T>, F>.cxUniform(
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

@JvmName("cxUniformBooleanArray")
suspend fun <F> PanmicticLifecycle<BooleanArray, F>.cxUniform(
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

@JvmName("cxUniformByteArray")
suspend fun <F> PanmicticLifecycle<ByteArray, F>.cxUniform(
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

@JvmName("cxUniformCharArray")
suspend fun <F> PanmicticLifecycle<CharArray, F>.cxUniform(
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

@JvmName("cxUniformDoubleArray")
suspend fun <F> PanmicticLifecycle<DoubleArray, F>.cxUniform(
    chance: Double = 0.9,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

@JvmName("cxUniformFloatArray")
suspend fun <F> PanmicticLifecycle<FloatArray, F>.cxUniform(
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

@JvmName("cxUniformIntArray")
suspend fun <F> PanmicticLifecycle<IntArray, F>.cxUniform(
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

@JvmName("cxUniformLongArray")
suspend fun <F> PanmicticLifecycle<LongArray, F>.cxUniform(
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

@JvmName("cxUniformMutableList")
suspend fun <T, F> PanmicticLifecycle<MutableList<T>, F>.cxUniform(
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

@JvmName("cxUniformShortArray")
suspend fun <F> PanmicticLifecycle<ShortArray, F>.cxUniform(
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}
