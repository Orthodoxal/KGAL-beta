package genetic.stat.registers

import genetic.ga.core.builder.GABuilder
import genetic.ga.core.lifecycle.GALifecycle
import genetic.stat.StatisticRegister
import genetic.stat.StatisticsBuilder

inline fun <V, F, L : GALifecycle<V, F>> StatisticsBuilder.register(
    owner: GABuilder<V, F, L>,
    name: String,
    crossinline registrar: GABuilder<V, F, L>.() -> Any?,
) = +StatisticRegister(name) { owner.registrar() }
