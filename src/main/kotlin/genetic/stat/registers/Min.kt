package genetic.stat.registers

import genetic.ga.core.GA
import genetic.ga.core.builder.GABuilder
import genetic.stat.StatisticRegister
import genetic.stat.StatisticsBuilder
import genetic.ga.core.population.get

private const val REGISTER_NAME = "MIN"

fun <V, F> GA<V, F>.min() = population.min()
fun <V, F> GABuilder<V, F, *>.min() = population.get().min()

fun StatisticsBuilder.min(cluster: GABuilder<*, *, *>) =
    +StatisticRegister(REGISTER_NAME) { cluster.min().fitness }

fun StatisticsBuilder.minChromosome(cluster: GABuilder<*, *, *>) =
    +StatisticRegister(REGISTER_NAME) { cluster.min() }
