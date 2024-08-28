package genetic.stat.registers

import genetic.chromosome.Chromosome
import genetic.ga.core.GA
import genetic.ga.core.builder.GABuilder
import genetic.stat.StatisticRegister
import genetic.stat.StatisticsBuilder
import genetic.ga.core.population.get

private const val REGISTER_NAME = "AVG"

@JvmName("avgInt")
fun avg(source: Array<out Chromosome<*, Int>>, size: Int) =
    source.fold(0) { acc, chromosome -> acc + chromosome.fitness!! }.toDouble() / size

@JvmName("avgDouble")
fun avg(source: Array<out Chromosome<*, Double>>, size: Int) =
    source.fold(0.0) { acc, chromosome -> acc + chromosome.fitness!! } / size

@JvmName("avgLong")
fun avg(source: Array<out Chromosome<*, Long>>, size: Int) =
    source.fold(0.0) { acc, chromosome -> acc + chromosome.fitness!! } / size

@JvmName("avgInt")
fun GABuilder<*, Int, *>.avg() = avg(population.get(), population.currentSize)

@JvmName("avgDouble")
fun GABuilder<*, Double, *>.avg() = avg(population.get(), population.currentSize)

@JvmName("avgLong")
fun GABuilder<*, Long, *>.avg() = avg(population.get(), population.currentSize)

@JvmName("avgInt")
fun GA<*, Int>.avg() = avg(population.get(), population.currentSize)

@JvmName("avgDouble")
fun GA<*, Double>.avg() = avg(population.get(), population.currentSize)

@JvmName("avgLong")
fun GA<*, Long>.avg() = avg(population.get(), population.currentSize)

@JvmName("avgInt")
fun StatisticsBuilder.avg(gaBuilder: GABuilder<*, Int, *>) =
    +StatisticRegister(REGISTER_NAME) { gaBuilder.avg() }

@JvmName("avgDouble")
fun StatisticsBuilder.avg(gaBuilder: GABuilder<*, Double, *>) =
    +StatisticRegister(REGISTER_NAME) { gaBuilder.avg() }

@JvmName("avgLong")
fun StatisticsBuilder.avg(gaBuilder: GABuilder<*, Long, *>) =
    +StatisticRegister(REGISTER_NAME) { gaBuilder.avg() }

// TODO() Добавить отдельные реализации для распределенного ГА?
