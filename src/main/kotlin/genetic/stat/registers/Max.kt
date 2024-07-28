package genetic.stat.registers

import genetic.clusters.base.Cluster
import genetic.clusters.base.builder.ClusterBuilder
import genetic.ga.GABuilder
import genetic.stat.StatisticRegister
import genetic.stat.StatisticsBuilder
import genetic.utils.clusters.max

private const val REGISTER_NAME = "MAX"

fun <V, F> Cluster<V, F>.max() = population.max()
fun <V, F> ClusterBuilder<V, F>.max() = population.max()

fun <V, F> StatisticsBuilder.max(cluster: ClusterBuilder<V, F>) =
    +StatisticRegister(REGISTER_NAME) { cluster.max().fitness }

fun <V, F> StatisticsBuilder.max(ga: GABuilder<V, F>) =
    +StatisticRegister(REGISTER_NAME) { ga.clusters.maxOfOrNull { it.max() }?.fitness }

fun <V, F> StatisticsBuilder.maxChromosome(cluster: ClusterBuilder<V, F>) =
    +StatisticRegister(REGISTER_NAME) { cluster.max() }

fun <V, F> StatisticsBuilder.maxChromosome(ga: GABuilder<V, F>) =
    +StatisticRegister(REGISTER_NAME) { ga.clusters.maxOfOrNull { it.max() } }
