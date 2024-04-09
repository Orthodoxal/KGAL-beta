package genetic.ga.cellular.operators.crossover

import genetic.ga.base_operators.crossover.uniform.*
import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle

fun <T, F> SimpleClusterCellLifecycle<Array<T>, F>.crossoverUniformArray(
    cellularGABuilder: CellularGABuilder<Array<T>, F>,
    chance: Double,
    chanceUniform: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverUniformArray(chromosome1, chromosome2, chanceUniform, random)
}

fun <F> SimpleClusterCellLifecycle<BooleanArray, F>.crossoverUniformBooleanArray(
    cellularGABuilder: CellularGABuilder<BooleanArray, F>,
    chance: Double,
    chanceUniform: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverUniformBooleanArray(chromosome1, chromosome2, chanceUniform, random)
}

fun <F> SimpleClusterCellLifecycle<ByteArray, F>.crossoverUniformByteArray(
    cellularGABuilder: CellularGABuilder<ByteArray, F>,
    chance: Double,
    chanceUniform: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverUniformByteArray(chromosome1, chromosome2, chanceUniform, random)
}

fun <F> SimpleClusterCellLifecycle<CharArray, F>.crossoverUniformCharArray(
    cellularGABuilder: CellularGABuilder<CharArray, F>,
    chance: Double,
    chanceUniform: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverUniformCharArray(chromosome1, chromosome2, chanceUniform, random)
}

fun <F> SimpleClusterCellLifecycle<DoubleArray, F>.crossoverUniformDoubleArray(
    cellularGABuilder: CellularGABuilder<DoubleArray, F>,
    chance: Double,
    chanceUniform: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverUniformDoubleArray(chromosome1, chromosome2, chanceUniform, random)
}

fun <F> SimpleClusterCellLifecycle<FloatArray, F>.crossoverUniformFloatArray(
    cellularGABuilder: CellularGABuilder<FloatArray, F>,
    chance: Double,
    chanceUniform: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverUniformFloatArray(chromosome1, chromosome2, chanceUniform, random)
}

fun <F> SimpleClusterCellLifecycle<IntArray, F>.crossoverUniformIntArray(
    cellularGABuilder: CellularGABuilder<IntArray, F>,
    chance: Double,
    chanceUniform: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverUniformIntArray(chromosome1, chromosome2, chanceUniform, random)
}

fun <F> SimpleClusterCellLifecycle<LongArray, F>.crossoverUniformLongArray(
    cellularGABuilder: CellularGABuilder<LongArray, F>,
    chance: Double,
    chanceUniform: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverUniformLongArray(chromosome1, chromosome2, chanceUniform, random)
}

fun <T, F> SimpleClusterCellLifecycle<MutableList<T>, F>.crossoverUniformMutableList(
    cellularGABuilder: CellularGABuilder<MutableList<T>, F>,
    chance: Double,
    chanceUniform: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverUniformMutableList(chromosome1, chromosome2, chanceUniform, random)
}

fun <F> SimpleClusterCellLifecycle<ShortArray, F>.crossoverUniformShortArray(
    cellularGABuilder: CellularGABuilder<ShortArray, F>,
    chance: Double,
    chanceUniform: Double,
) = crossover(cellularGABuilder, chance) { chromosome1, chromosome2 ->
    crossoverUniformShortArray(chromosome1, chromosome2, chanceUniform, random)
}
