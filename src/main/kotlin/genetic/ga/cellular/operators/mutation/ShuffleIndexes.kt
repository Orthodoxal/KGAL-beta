package genetic.ga.cellular.operators.mutation

import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle
import genetic.ga.base_operators.mutation.shuffle_indexes.mutationShuffleIndexes

fun <T, F> SimpleClusterCellLifecycle<Array<T>, F>.mutationShuffleIndexesArray(
    cellularGABuilder: CellularGABuilder<Array<T>, F>,
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(cellularGABuilder, mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

fun <F> SimpleClusterCellLifecycle<BooleanArray, F>.mutationShuffleIndexesBooleanArray(
    cellularGABuilder: CellularGABuilder<BooleanArray, F>,
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(cellularGABuilder, mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

fun <F> SimpleClusterCellLifecycle<ByteArray, F>.mutationShuffleIndexesByteArray(
    cellularGABuilder: CellularGABuilder<ByteArray, F>,
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(cellularGABuilder, mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

fun <F> SimpleClusterCellLifecycle<CharArray, F>.mutationShuffleIndexesCharArray(
    cellularGABuilder: CellularGABuilder<CharArray, F>,
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(cellularGABuilder, mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

fun <F> SimpleClusterCellLifecycle<DoubleArray, F>.mutationShuffleIndexesDoubleArray(
    cellularGABuilder: CellularGABuilder<DoubleArray, F>,
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(cellularGABuilder, mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

fun <F> SimpleClusterCellLifecycle<FloatArray, F>.mutationShuffleIndexesFloatArray(
    cellularGABuilder: CellularGABuilder<FloatArray, F>,
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(cellularGABuilder, mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

fun <F> SimpleClusterCellLifecycle<IntArray, F>.mutationShuffleIndexesIntArray(
    cellularGABuilder: CellularGABuilder<IntArray, F>,
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(cellularGABuilder, mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

fun <F> SimpleClusterCellLifecycle<LongArray, F>.mutationShuffleIndexesLongArray(
    cellularGABuilder: CellularGABuilder<LongArray, F>,
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(cellularGABuilder, mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

fun <T, F> SimpleClusterCellLifecycle<MutableList<T>, F>.mutationShuffleIndexesMutableList(
    cellularGABuilder: CellularGABuilder<MutableList<T>, F>,
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(cellularGABuilder, mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}

fun <F> SimpleClusterCellLifecycle<ShortArray, F>.mutationShuffleIndexesShortArray(
    cellularGABuilder: CellularGABuilder<ShortArray, F>,
    mutationChance: Double,
    mutationShuffleIndexesChance: Double,
) = mutation(cellularGABuilder, mutationChance) { chromosome ->
    mutationShuffleIndexes(chromosome.value, mutationShuffleIndexesChance, random)
}
