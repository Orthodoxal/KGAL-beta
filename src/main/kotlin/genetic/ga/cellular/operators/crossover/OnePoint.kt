package genetic.ga.cellular.operators.crossover

import genetic.ga.base_operators.crossover.one_point.*
import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle

fun <T, F> SimpleClusterCellLifecycle<Array<T>, F>.crossoverOnePointArray(
    cellularGABuilder: CellularGABuilder<Array<T>, F>,
    chance: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverOnePointArray(chromosome1.value, chromosome2.value, random)
}

fun <F> SimpleClusterCellLifecycle<BooleanArray, F>.crossoverOnePointBooleanArray(
    cellularGABuilder: CellularGABuilder<BooleanArray, F>,
    chance: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverOnePointBooleanArray(chromosome1.value, chromosome2.value, random)
}

fun <F> SimpleClusterCellLifecycle<ByteArray, F>.crossoverOnePointByteArray(
    cellularGABuilder: CellularGABuilder<ByteArray, F>,
    chance: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverOnePointByteArray(chromosome1.value, chromosome2.value, random)
}

fun <F> SimpleClusterCellLifecycle<CharArray, F>.crossoverOnePointCharArray(
    cellularGABuilder: CellularGABuilder<CharArray, F>,
    chance: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverOnePointCharArray(chromosome1.value, chromosome2.value, random)
}

fun <F> SimpleClusterCellLifecycle<DoubleArray, F>.crossoverOnePointDoubleArray(
    cellularGABuilder: CellularGABuilder<DoubleArray, F>,
    chance: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverOnePointDoubleArray(chromosome1.value, chromosome2.value, random)
}

fun <F> SimpleClusterCellLifecycle<FloatArray, F>.crossoverOnePointFloatArray(
    cellularGABuilder: CellularGABuilder<FloatArray, F>,
    chance: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverOnePointFloatArray(chromosome1.value, chromosome2.value, random)
}

fun <F> SimpleClusterCellLifecycle<IntArray, F>.crossoverOnePointIntArray(
    cellularGABuilder: CellularGABuilder<IntArray, F>,
    chance: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverOnePointIntArray(chromosome1.value, chromosome2.value, random)
}

fun <F> SimpleClusterCellLifecycle<LongArray, F>.crossoverOnePointLongArray(
    cellularGABuilder: CellularGABuilder<LongArray, F>,
    chance: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverOnePointLongArray(chromosome1.value, chromosome2.value, random)
}

fun <T, F> SimpleClusterCellLifecycle<MutableList<T>, F>.crossoverOnePointMutableList(
    cellularGABuilder: CellularGABuilder<MutableList<T>, F>,
    chance: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverOnePointMutableList(chromosome1.value, chromosome2.value, random)
}

fun <F> SimpleClusterCellLifecycle<ShortArray, F>.crossoverOnePointShortArray(
    cellularGABuilder: CellularGABuilder<ShortArray, F>,
    chance: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverOnePointShortArray(chromosome1.value, chromosome2.value, random)
}
