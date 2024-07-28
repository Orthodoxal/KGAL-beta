package genetic.clusters.cellular.operators.crossover

import genetic.clusters.base.operators.crossover.one_point.crossoverOnePoint
import genetic.clusters.cellular.lifecycle.CellLifecycle

fun <T, F> CellLifecycle<Array<T>, F>.cxOnePointArray(
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

fun <F> CellLifecycle<BooleanArray, F>.cxOnePointBooleanArray(
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

fun <F> CellLifecycle<ByteArray, F>.cxOnePointByteArray(
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

fun <F> CellLifecycle<CharArray, F>.cxOnePointCharArray(
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

fun <F> CellLifecycle<DoubleArray, F>.cxOnePointDoubleArray(
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

fun <F> CellLifecycle<FloatArray, F>.cxOnePointFloatArray(
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

fun <F> CellLifecycle<IntArray, F>.cxOnePointIntArray(
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

fun <F> CellLifecycle<LongArray, F>.cxOnePointLongArray(
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

fun <T, F> CellLifecycle<MutableList<T>, F>.cxOnePointMutableList(
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}

fun <F> CellLifecycle<ShortArray, F>.cxOnePointShortArray(
    chance: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverOnePoint(chromosome1.value, chromosome2.value, random)
}
