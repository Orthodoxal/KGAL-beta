package genetic.ga.panmictic.operators.crossover

import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.base_operators.crossover.uniform.*
import genetic.ga.panmictic.builder.PanmicticGABuilder

suspend fun <T, F> SimpleClusterLifecycle<Array<T>, F>.crossoverUniformArray(
    panmicticGABuilder: PanmicticGABuilder<Array<T>, F>,
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniformArray(chromosome1, chromosome2, chanceUniform, random)
}

suspend fun <F> SimpleClusterLifecycle<BooleanArray, F>.crossoverUniformBooleanArray(
    panmicticGABuilder: PanmicticGABuilder<BooleanArray, F>,
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniformBooleanArray(chromosome1, chromosome2, chanceUniform, random)
}

suspend fun <F> SimpleClusterLifecycle<ByteArray, F>.crossoverUniformByteArray(
    panmicticGABuilder: PanmicticGABuilder<ByteArray, F>,
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniformByteArray(chromosome1, chromosome2, chanceUniform, random)
}

suspend fun <F> SimpleClusterLifecycle<CharArray, F>.crossoverUniformCharArray(
    panmicticGABuilder: PanmicticGABuilder<CharArray, F>,
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniformCharArray(chromosome1, chromosome2, chanceUniform, random)
}

suspend fun <F> SimpleClusterLifecycle<DoubleArray, F>.crossoverUniformDoubleArray(
    panmicticGABuilder: PanmicticGABuilder<DoubleArray, F>,
    chance: Double = 0.9,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniformDoubleArray(chromosome1, chromosome2, chanceUniform, random)
}

suspend fun <F> SimpleClusterLifecycle<FloatArray, F>.crossoverUniformFloatArray(
    panmicticGABuilder: PanmicticGABuilder<FloatArray, F>,
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniformFloatArray(chromosome1, chromosome2, chanceUniform, random)
}

suspend fun <F> SimpleClusterLifecycle<IntArray, F>.crossoverUniformIntArray(
    panmicticGABuilder: PanmicticGABuilder<IntArray, F>,
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniformIntArray(chromosome1, chromosome2, chanceUniform, random)
}

suspend fun <F> SimpleClusterLifecycle<LongArray, F>.crossoverUniformLongArray(
    panmicticGABuilder: PanmicticGABuilder<LongArray, F>,
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniformLongArray(chromosome1, chromosome2, chanceUniform, random)
}

suspend fun <T, F> SimpleClusterLifecycle<MutableList<T>, F>.crossoverUniformMutableList(
    panmicticGABuilder: PanmicticGABuilder<MutableList<T>, F>,
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniformMutableList(chromosome1, chromosome2, chanceUniform, random)
}

suspend fun <F> SimpleClusterLifecycle<ShortArray, F>.crossoverUniformShortArray(
    panmicticGABuilder: PanmicticGABuilder<ShortArray, F>,
    chance: Double,
    chanceUniform: Double,
    onlySingleRun: Boolean = false,
) = crossover(panmicticGABuilder, chance, onlySingleRun) { chromosome1, chromosome2 ->
    crossoverUniformShortArray(chromosome1, chromosome2, chanceUniform, random)
}
