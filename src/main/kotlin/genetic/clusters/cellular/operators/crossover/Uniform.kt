package genetic.clusters.cellular.operators.crossover

import genetic.clusters.base.operators.crossover.uniform.crossoverUniform
import genetic.clusters.cellular.lifecycle.CellLifecycle

fun <T, F> CellLifecycle<Array<T>, F>.crossoverUniformArray(
    chance: Double,
    chanceUniform: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

fun <F> CellLifecycle<BooleanArray, F>.crossoverUniformBooleanArray(
    chance: Double,
    chanceUniform: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

fun <F> CellLifecycle<ByteArray, F>.crossoverUniformByteArray(
    chance: Double,
    chanceUniform: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

fun <F> CellLifecycle<CharArray, F>.crossoverUniformCharArray(
    chance: Double,
    chanceUniform: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

fun <F> CellLifecycle<DoubleArray, F>.crossoverUniformDoubleArray(
    chance: Double,
    chanceUniform: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

fun <F> CellLifecycle<FloatArray, F>.crossoverUniformFloatArray(
    chance: Double,
    chanceUniform: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

fun <F> CellLifecycle<IntArray, F>.crossoverUniformIntArray(
    chance: Double,
    chanceUniform: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

fun <F> CellLifecycle<LongArray, F>.crossoverUniformLongArray(
    chance: Double,
    chanceUniform: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

fun <T, F> CellLifecycle<MutableList<T>, F>.crossoverUniformMutableList(
    chance: Double,
    chanceUniform: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

fun <F> CellLifecycle<ShortArray, F>.crossoverUniformShortArray(
    chance: Double,
    chanceUniform: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}
