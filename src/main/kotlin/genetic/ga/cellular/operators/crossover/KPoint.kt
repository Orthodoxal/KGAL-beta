package genetic.ga.cellular.operators.crossover

import genetic.ga.core.operators.crossover.k_point.crossoverKPoint
import genetic.ga.cellular.lifecycle.CellLifecycle

@JvmName("cxKPointArray")
fun <T, F> CellLifecycle<Array<T>, F>.cxKPoint(
    count: Int,
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

@JvmName("cxKPointBooleanArray")
fun <F> CellLifecycle<BooleanArray, F>.cxKPoint(
    count: Int,
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

@JvmName("cxKPointByteArray")
fun <F> CellLifecycle<ByteArray, F>.cxKPoint(
    count: Int,
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

@JvmName("cxKPointCharArray")
fun <F> CellLifecycle<CharArray, F>.cxKPoint(
    count: Int,
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

@JvmName("cxKPointDoubleArray")
fun <F> CellLifecycle<DoubleArray, F>.cxKPoint(
    count: Int,
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

@JvmName("cxKPointFloatArray")
fun <F> CellLifecycle<FloatArray, F>.cxKPoint(
    count: Int,
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

@JvmName("cxKPointIntArray")
fun <F> CellLifecycle<IntArray, F>.cxKPoint(
    count: Int,
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

@JvmName("cxKPointLongArray")
fun <F> CellLifecycle<LongArray, F>.cxKPoint(
    count: Int,
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

@JvmName("cxKPointMutableList")
fun <T, F> CellLifecycle<MutableList<T>, F>.cxKPoint(
    count: Int,
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

@JvmName("cxKPointShortArray")
fun <F> CellLifecycle<ShortArray, F>.cxKPoint(
    count: Int,
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}
