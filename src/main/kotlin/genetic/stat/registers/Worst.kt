package genetic.stat.registers

import genetic.ga.core.GA
import genetic.ga.core.lifecycle.Lifecycle
import genetic.ga.core.population.worst
import genetic.stat.note.Statistic

private const val NAME = "WORST"
private const val NAME_FITNESS = "WORST FITNESS"

inline val <V, F> GA<V, F>.worst get() = population.worst

inline val <V, F> GA<V, F>.worstFitness get() = population.worst.fitness


inline val <V, F> Lifecycle<V, F>.worst get() = population.worst

inline val <V, F> Lifecycle<V, F>.worstFitness get() = population.worst.fitness


fun <V, F> Lifecycle<V, F>.worst() = Statistic(NAME, worst)

fun <F> Lifecycle<*, F>.worstFitness() = Statistic(NAME_FITNESS, worstFitness)
