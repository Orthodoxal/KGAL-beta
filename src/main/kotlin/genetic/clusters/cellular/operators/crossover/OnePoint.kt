package genetic.clusters.cellular.operators.crossover

import genetic.clusters.base.operators.crossover.one_point.crossoverOnePoint
import genetic.clusters.cellular.lifecycle.CellLifecycle

@JvmName("cxOnePointArray")
fun <T, F> CellLifecycle<Array<T>, F>.cxOnePoint(
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

@JvmName("cxOnePointBooleanArray")
fun <F> CellLifecycle<BooleanArray, F>.cxOnePoint(
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

@JvmName("cxOnePointByteArray")
fun <F> CellLifecycle<ByteArray, F>.cxOnePoint(
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

@JvmName("cxOnePointCharArray")
fun <F> CellLifecycle<CharArray, F>.cxOnePoint(
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

@JvmName("cxOnePointDoubleArray")
fun <F> CellLifecycle<DoubleArray, F>.cxOnePoint(
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

@JvmName("cxOnePointFloatArray")
fun <F> CellLifecycle<FloatArray, F>.cxOnePoint(
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

@JvmName("cxOnePointIntArray")
fun <F> CellLifecycle<IntArray, F>.cxOnePoint(
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

@JvmName("cxOnePointLongArray")
fun <F> CellLifecycle<LongArray, F>.cxOnePoint(
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

@JvmName("cxOnePointMutableList")
fun <T, F> CellLifecycle<MutableList<T>, F>.cxOnePoint(
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

@JvmName("cxOnePointShortArray")
fun <F> CellLifecycle<ShortArray, F>.cxOnePoint(
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}
