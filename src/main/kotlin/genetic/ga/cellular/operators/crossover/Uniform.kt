package genetic.ga.cellular.operators.crossover

import genetic.ga.core.operators.crossover.uniform.crossoverUniform
import genetic.ga.cellular.lifecycle.CellLifecycle

@JvmName("crossoverUniformArray")
fun <T, F> CellLifecycle<Array<T>, F>.crossoverUniform(
    chance: Double,
    chanceUniform: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

@JvmName("crossoverUniformBooleanArray")
fun <F> CellLifecycle<BooleanArray, F>.crossoverUniform(
    chance: Double,
    chanceUniform: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

@JvmName("crossoverUniformByteArray")
fun <F> CellLifecycle<ByteArray, F>.crossoverUniform(
    chance: Double,
    chanceUniform: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

@JvmName("crossoverUniformCharArray")
fun <F> CellLifecycle<CharArray, F>.crossoverUniform(
    chance: Double,
    chanceUniform: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

@JvmName("crossoverUniformDoubleArray")
fun <F> CellLifecycle<DoubleArray, F>.crossoverUniform(
    chance: Double,
    chanceUniform: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

@JvmName("crossoverUniformFloatArray")
fun <F> CellLifecycle<FloatArray, F>.crossoverUniform(
    chance: Double,
    chanceUniform: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

@JvmName("crossoverUniformIntArray")
fun <F> CellLifecycle<IntArray, F>.crossoverUniform(
    chance: Double,
    chanceUniform: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

@JvmName("crossoverUniformLongArray")
fun <F> CellLifecycle<LongArray, F>.crossoverUniform(
    chance: Double,
    chanceUniform: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

@JvmName("crossoverUniformMutableList")
fun <T, F> CellLifecycle<MutableList<T>, F>.crossoverUniform(
    chance: Double,
    chanceUniform: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}

@JvmName("crossoverUniformShortArray")
fun <F> CellLifecycle<ShortArray, F>.crossoverUniform(
    chance: Double,
    chanceUniform: Double,
) = crossover(chance) { chromosome1, chromosome2 ->
    crossoverUniform(chromosome1.value, chromosome2.value, chanceUniform, random)
}
