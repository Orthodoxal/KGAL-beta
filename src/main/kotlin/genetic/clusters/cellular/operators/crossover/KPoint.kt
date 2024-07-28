package genetic.clusters.cellular.operators.crossover

import genetic.clusters.base.operators.crossover.k_point.crossoverKPoint
import genetic.clusters.cellular.lifecycle.CellLifecycle

fun <T, F> CellLifecycle<Array<T>, F>.cxKPointArray(
    count: Int,
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

fun <F> CellLifecycle<BooleanArray, F>.cxKPointBooleanArray(
    count: Int,
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

fun <F> CellLifecycle<ByteArray, F>.cxKPointByteArray(
    count: Int,
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

fun <F> CellLifecycle<CharArray, F>.cxKPointCharArray(
    count: Int,
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

fun <F> CellLifecycle<DoubleArray, F>.cxKPointDoubleArray(
    count: Int,
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

fun <F> CellLifecycle<FloatArray, F>.cxKPointFloatArray(
    count: Int,
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

fun <F> CellLifecycle<IntArray, F>.cxKPointIntArray(
    count: Int,
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

fun <F> CellLifecycle<LongArray, F>.cxKPointLongArray(
    count: Int,
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

fun <T, F> CellLifecycle<MutableList<T>, F>.cxKPointMutableList(
    count: Int,
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}

fun <F> CellLifecycle<ShortArray, F>.cxKPointShortArray(
    count: Int,
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverKPoint(chromosome1.value, chromosome2.value, count, random)
}
