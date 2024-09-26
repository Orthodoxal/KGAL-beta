package genetic.ga.panmictic.operators.crossover

import genetic.ga.core.operators.crossover.one_point.crossoverOnePoint
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

@JvmName("cxOnePointArray")
suspend fun <T, F> PanmicticLifecycle<Array<T>, F>.cxOnePoint(
    chance: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
    crossoverType: CrossoverType = CrossoverType.Iterative,
) = crossover(chance, parallelWorkersLimit, crossoverType) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

@JvmName("cxOnePointBooleanArray")
suspend fun <F> PanmicticLifecycle<BooleanArray, F>.cxOnePoint(
    chance: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
    crossoverType: CrossoverType = CrossoverType.Iterative,
) = crossover(chance, parallelWorkersLimit, crossoverType) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

@JvmName("cxOnePointByteArray")
suspend fun <F> PanmicticLifecycle<ByteArray, F>.cxOnePoint(
    chance: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
    crossoverType: CrossoverType = CrossoverType.Iterative,
) = crossover(chance, parallelWorkersLimit, crossoverType) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

@JvmName("cxOnePointCharArray")
suspend fun <F> PanmicticLifecycle<CharArray, F>.cxOnePoint(
    chance: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
    crossoverType: CrossoverType = CrossoverType.Iterative,
) = crossover(chance, parallelWorkersLimit, crossoverType) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

@JvmName("cxOnePointDoubleArray")
suspend fun <F> PanmicticLifecycle<DoubleArray, F>.cxOnePoint(
    chance: Double = 0.9,
    parallelWorkersLimit: Int = parallelismConfig.count,
    crossoverType: CrossoverType = CrossoverType.Iterative,
) = crossover(chance, parallelWorkersLimit, crossoverType) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

@JvmName("cxOnePointFloatArray")
suspend fun <F> PanmicticLifecycle<FloatArray, F>.cxOnePoint(
    chance: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
    crossoverType: CrossoverType = CrossoverType.Iterative,
) = crossover(chance, parallelWorkersLimit, crossoverType) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

@JvmName("cxOnePointIntArray")
suspend fun <F> PanmicticLifecycle<IntArray, F>.cxOnePoint(
    chance: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
    crossoverType: CrossoverType = CrossoverType.Iterative,
) = crossover(chance, parallelWorkersLimit, crossoverType) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

@JvmName("cxOnePointLongArray")
suspend fun <F> PanmicticLifecycle<LongArray, F>.cxOnePoint(
    chance: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
    crossoverType: CrossoverType = CrossoverType.Iterative,
) = crossover(chance, parallelWorkersLimit, crossoverType) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

@JvmName("cxOnePointMutableList")
suspend fun <T, F> PanmicticLifecycle<MutableList<T>, F>.cxOnePoint(
    chance: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
    crossoverType: CrossoverType = CrossoverType.Iterative,
) = crossover(chance, parallelWorkersLimit, crossoverType) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

@JvmName("cxOnePointShortArray")
suspend fun <F> PanmicticLifecycle<ShortArray, F>.cxOnePoint(
    chance: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
    crossoverType: CrossoverType = CrossoverType.Iterative,
) = crossover(chance, parallelWorkersLimit, crossoverType) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}
