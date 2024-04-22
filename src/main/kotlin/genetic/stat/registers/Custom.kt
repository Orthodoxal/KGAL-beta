package genetic.stat.registers

import genetic.clusters.ClusterBuilder
import genetic.ga.GABuilder
import genetic.stat.StatisticRegister
import genetic.stat.StatisticsBuilder

inline fun <V, F> StatisticsBuilder.register(
    owner: ClusterBuilder<V, F>,
    name: String,
    crossinline registrar: ClusterBuilder<V, F>.() -> Any?,
) = +StatisticRegister(name) { owner.registrar() }

inline fun <V, F> StatisticsBuilder.register(
    owner: GABuilder<V, F>,
    name: String,
    crossinline registrar: GABuilder<V, F>.() -> Any?,
) = +StatisticRegister(name) { owner.registrar() }
