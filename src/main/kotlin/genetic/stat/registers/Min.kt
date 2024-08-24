package genetic.stat.registers

import genetic.clusters.base.builder.ClusterBuilder
import genetic.stat.StatisticRegister
import genetic.stat.StatisticsBuilder
import genetic.utils.clusters.get

private const val REGISTER_NAME = "MIN"

//fun <V, F> Cluster<V, F>.min() = populationOld.min()
fun <V, F> ClusterBuilder<V, F, *>.min() = population.get().min()

fun StatisticsBuilder.min(cluster: ClusterBuilder<*, *, *>) =
    +StatisticRegister(REGISTER_NAME) { cluster.min().fitness }

/*fun <V, F> StatisticsBuilder.min(ga: GABuilder<V, F>) =
    +StatisticRegister(REGISTER_NAME) { ga.clusters.minOfOrNull { it.min() }?.fitness }*/

fun StatisticsBuilder.minChromosome(cluster: ClusterBuilder<*, *, *>) =
    +StatisticRegister(REGISTER_NAME) { cluster.min() }

/*fun <V, F> StatisticsBuilder.minChromosome(ga: GABuilder<V, F>) =
    +StatisticRegister(REGISTER_NAME) { ga.clusters.minOfOrNull { it.min() } }*/
