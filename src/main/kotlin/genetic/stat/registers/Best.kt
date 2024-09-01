package genetic.stat.registers

import genetic.ga.core.GA
import genetic.ga.core.lifecycle.GALifecycle
import genetic.ga.core.population.best
import genetic.stat.note.Statistic

private const val NAME = "BEST"
private const val NAME_FITNESS = "BEST FITNESS"

inline val <V, F> GA<V, F>.best get() = population.best

inline val <V, F> GA<V, F>.bestFitness get() = population.best.fitness


inline val <V, F> GALifecycle<V, F>.best get() = population.best

inline val <V, F> GALifecycle<V, F>.bestFitness get() = population.best.fitness


fun <V, F> GALifecycle<V, F>.best() = Statistic(NAME, best)

fun <F> GALifecycle<*, F>.bestFitness() = Statistic(NAME_FITNESS, bestFitness)
