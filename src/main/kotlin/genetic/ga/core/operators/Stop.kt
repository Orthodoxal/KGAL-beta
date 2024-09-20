package genetic.ga.core.operators

import genetic.ga.core.lifecycle.Lifecycle

inline fun <V, F> Lifecycle<V, F>.stopBy(
    maxIteration: Int = Int.MAX_VALUE,
) {
    if (iteration >= maxIteration) {
        finishedByMaxIteration = true
    }
}

inline fun <V, F> Lifecycle<V, F>.stopBy(
    stopCondition: Lifecycle<V, F>.() -> Boolean,
) {
    if (stopCondition()) {
        finishByStopConditions = true
    }
}

inline fun <V, F> Lifecycle<V, F>.stopBy(
    maxIteration: Int = Int.MAX_VALUE,
    stopCondition: Lifecycle<V, F>.() -> Boolean,
) {
    stopBy(maxIteration)
    stopBy(stopCondition)
}
