package genetic.stat.registers

import genetic.ga.core.GA
import genetic.ga.core.currentSize
import genetic.ga.core.lifecycle.Lifecycle
import genetic.ga.core.lifecycle.size
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
val Lifecycle<*, Int>.mean
    get() = population.fold(0.0) { acc, chromosome -> acc + chromosome.fitness!! } / size

@get:JvmName("getMeanDouble")
val Lifecycle<*, Double>.mean
    get() = population.fold(0.0) { acc, chromosome -> acc + chromosome.fitness!! } / size

@get:JvmName("getMeanLong")
val Lifecycle<*, Long>.mean
    get() = population.fold(0.0) { acc, chromosome -> acc + chromosome.fitness!! } / size


@JvmName("meanInt")
fun Lifecycle<*, Int>.mean() = Statistic(NAME, mean)

@JvmName("meanDouble")
fun Lifecycle<*, Double>.mean() = Statistic(NAME, mean)

@JvmName("meanLong")
fun Lifecycle<*, Long>.mean() = Statistic(NAME, mean)
