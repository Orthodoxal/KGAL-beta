package genetic.stat.registers

import genetic.ga.core.GA
import genetic.ga.core.currentSize
import genetic.ga.core.lifecycle.GALifecycle
import genetic.ga.core.lifecycle.currentSize
import genetic.ga.core.population.fold
import genetic.stat.note.Statistic

private const val NAME = "MEAN"

@get:JvmName("getMeanInt")
val GA<*, Int>.mean
    get() = population.fold(0.0) { acc, chromosome -> acc + chromosome.fitness!! } / currentSize

@get:JvmName("getMeanDouble")
val GA<*, Double>.mean
    get() = population.fold(0.0) { acc, chromosome -> acc + chromosome.fitness!! } / currentSize

@get:JvmName("getMeanLong")
val GA<*, Long>.mean
    get() = population.fold(0.0) { acc, chromosome -> acc + chromosome.fitness!! } / currentSize


@get:JvmName("getMeanInt")
val GALifecycle<*, Int>.mean
    get() = population.fold(0.0) { acc, chromosome -> acc + chromosome.fitness!! } / currentSize

@get:JvmName("getMeanDouble")
val GALifecycle<*, Double>.mean
    get() = population.fold(0.0) { acc, chromosome -> acc + chromosome.fitness!! } / currentSize

@get:JvmName("getMeanLong")
val GALifecycle<*, Long>.mean
    get() = population.fold(0.0) { acc, chromosome -> acc + chromosome.fitness!! } / currentSize


@JvmName("meanInt")
fun GALifecycle<*, Int>.mean() = Statistic(NAME, mean)

@JvmName("meanDouble")
fun GALifecycle<*, Double>.mean() = Statistic(NAME, mean)

@JvmName("meanLong")
fun GALifecycle<*, Long>.mean() = Statistic(NAME, mean)
