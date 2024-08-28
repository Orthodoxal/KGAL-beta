package genetic.ga.core.operators

import genetic.ga.core.lifecycle.GALifecycle

inline fun <V, F> GALifecycle<V, F>.stopBy(predicate: (GALifecycle<V, F>) -> Boolean) {
    if (predicate(this)) {
        stopSignal = true
    }
}
