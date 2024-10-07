package genetic.statistics.registers

import genetic.ga.core.GA
import genetic.ga.core.lifecycle.Lifecycle
import genetic.statistics.note.Statistic

private const val NAME = "SIZE"

inline val GA<*, *>.size get() = population.size


inline val Lifecycle<*, *>.size get() = population.size


fun Lifecycle<*, *>.size() = Statistic(NAME, size)
