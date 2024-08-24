package genetic.stat.registers

import genetic.clusters.base.builder.ClusterBuilder
import genetic.clusters.base.lifecycle.ClusterLifecycle
import genetic.stat.StatisticRegister
import genetic.stat.StatisticsBuilder

inline fun <V, F, L : ClusterLifecycle<V, F>> StatisticsBuilder.register(
    owner: ClusterBuilder<V, F, L>,
    name: String,
    crossinline registrar: ClusterBuilder<V, F, L>.() -> Any?,
) = +StatisticRegister(name) { owner.registrar() }

/*inline fun <V, F> StatisticsBuilder.register(
    owner: GABuilder<V, F>,
    name: String,
    crossinline registrar: GABuilder<V, F>.() -> Any?,
) = +StatisticRegister(name) { owner.registrar() }*/
