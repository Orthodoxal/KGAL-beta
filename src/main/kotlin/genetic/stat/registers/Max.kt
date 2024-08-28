package genetic.stat.registers

import genetic.ga.core.GA
import genetic.ga.core.builder.GABuilder
import genetic.stat.StatisticRegister
import genetic.stat.StatisticsBuilder
import genetic.ga.core.population.max

private const val REGISTER_NAME = "MAX"

fun <V, F> GA<V, F>.max() = population.max()
fun <V, F> GABuilder<V, F, *>.max() = population.max()

fun StatisticsBuilder.max(cluster: GABuilder<*, *, *>) =
    +StatisticRegister(REGISTER_NAME) { cluster.max().fitness }

fun StatisticsBuilder.maxChromosome(cluster: GABuilder<*, *, *>) =
    +StatisticRegister(REGISTER_NAME) { cluster.max() }
