package genetic.ga.cellular.operators.crossover

import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle
import genetic.ga.base_operators.crossover.k_point.*

fun <T, F> SimpleClusterCellLifecycle<Array<T>, F>.crossoverKPointArray(
    cellularGABuilder: CellularGABuilder<Array<T>, F>,
    count: Int,
    chance: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverKPointArray(chromosome1, chromosome2, count, random)
}

fun <F> SimpleClusterCellLifecycle<BooleanArray, F>.crossoverKPointBooleanArray(
    cellularGABuilder: CellularGABuilder<BooleanArray, F>,
    count: Int,
    chance: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverKPointBooleanArray(chromosome1, chromosome2, count, random)
}

fun <F> SimpleClusterCellLifecycle<ByteArray, F>.crossoverKPointByteArray(
    cellularGABuilder: CellularGABuilder<ByteArray, F>,
    count: Int,
    chance: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverKPointByteArray(chromosome1, chromosome2, count, random)
}

fun <F> SimpleClusterCellLifecycle<CharArray, F>.crossoverKPointCharArray(
    cellularGABuilder: CellularGABuilder<CharArray, F>,
    count: Int,
    chance: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverKPointCharArray(chromosome1, chromosome2, count, random)
}

fun <F> SimpleClusterCellLifecycle<DoubleArray, F>.crossoverKPointDoubleArray(
    cellularGABuilder: CellularGABuilder<DoubleArray, F>,
    count: Int,
    chance: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverKPointDoubleArray(chromosome1, chromosome2, count, random)
}

fun <F> SimpleClusterCellLifecycle<FloatArray, F>.crossoverKPointFloatArray(
    cellularGABuilder: CellularGABuilder<FloatArray, F>,
    count: Int,
    chance: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverKPointFloatArray(chromosome1, chromosome2, count, random)
}

fun <F> SimpleClusterCellLifecycle<IntArray, F>.crossoverKPointIntArray(
    cellularGABuilder: CellularGABuilder<IntArray, F>,
    count: Int,
    chance: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverKPointIntArray(chromosome1, chromosome2, count, random)
}

fun <F> SimpleClusterCellLifecycle<LongArray, F>.crossoverKPointLongArray(
    cellularGABuilder: CellularGABuilder<LongArray, F>,
    count: Int,
    chance: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverKPointLongArray(chromosome1, chromosome2, count, random)
}

fun <T, F> SimpleClusterCellLifecycle<MutableList<T>, F>.crossoverKPointMutableList(
    cellularGABuilder: CellularGABuilder<MutableList<T>, F>,
    count: Int,
    chance: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverKPointMutableList(chromosome1, chromosome2, count, random)
}

fun <F> SimpleClusterCellLifecycle<ShortArray, F>.crossoverKPointShortArray(
    cellularGABuilder: CellularGABuilder<ShortArray, F>,
    count: Int,
    chance: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverKPointShortArray(chromosome1, chromosome2, count, random)
}
