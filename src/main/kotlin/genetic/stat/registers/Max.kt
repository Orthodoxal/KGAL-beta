package genetic.stat.registers

import genetic.clusters.base.builder.ClusterBuilder
import genetic.stat.StatisticRegister
import genetic.stat.StatisticsBuilder
import genetic.utils.clusters.max

private const val REGISTER_NAME = "MAX"

//fun <V, F> Cluster<V, F>.max() = population.max()
fun <V, F> ClusterBuilder<V, F, *>.max() = population.max()

fun StatisticsBuilder.max(cluster: ClusterBuilder<*, *, *>) =
    +StatisticRegister(REGISTER_NAME) { cluster.max().fitness }

/*fun <V, F> StatisticsBuilder.max(ga: GABuilder<V, F>) =
    +StatisticRegister(REGISTER_NAME) { ga.clusters.maxOfOrNull { it.max() }?.fitness }*/

fun StatisticsBuilder.maxChromosome(cluster: ClusterBuilder<*, *, *>) =
    +StatisticRegister(REGISTER_NAME) { cluster.max() }

/*fun <V, F> StatisticsBuilder.maxChromosome(ga: GABuilder<V, F>) =
    +StatisticRegister(REGISTER_NAME) { ga.clusters.maxOfOrNull { it.max() } }*/
