package genetic.stat.registers

import genetic.ga.core.GA
import genetic.ga.core.lifecycle.GALifecycle
import genetic.stat.note.Statistic

private const val NAME = "SIZE"

inline val GA<*, *>.size get() = population.currentSize


inline val GALifecycle<*, *>.size get() = population.currentSize


fun GALifecycle<*, *>.size() = Statistic(NAME, size)
