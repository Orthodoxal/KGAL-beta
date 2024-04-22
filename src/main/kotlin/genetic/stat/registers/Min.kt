package genetic.stat.registers

import genetic.clusters.Cluster
import genetic.clusters.ClusterBuilder
import genetic.ga.GABuilder
import genetic.stat.StatisticRegister
import genetic.stat.StatisticsBuilder

private const val REGISTER_NAME = "MIN"

fun <V, F> Cluster<V, F>.min() = population.min()
fun <V, F> ClusterBuilder<V, F>.min() = population.min()

fun <V, F> StatisticsBuilder.min(cluster: ClusterBuilder<V, F>) =
    +StatisticRegister(REGISTER_NAME) { cluster.min().fitness }

fun <V, F> StatisticsBuilder.min(ga: GABuilder<V, F>) =
    +StatisticRegister(REGISTER_NAME) { ga.clusters.minOfOrNull { it.min() }?.fitness }

fun <V, F> StatisticsBuilder.minChromosome(cluster: ClusterBuilder<V, F>) =
    +StatisticRegister(REGISTER_NAME) { cluster.min() }

fun <V, F> StatisticsBuilder.minChromosome(ga: GABuilder<V, F>) =
    +StatisticRegister(REGISTER_NAME) { ga.clusters.minOfOrNull { it.min() } }
