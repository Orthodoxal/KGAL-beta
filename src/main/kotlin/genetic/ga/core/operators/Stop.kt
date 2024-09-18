package genetic.ga.core.operators

import genetic.ga.core.lifecycle.Lifecycle

inline fun <V, F> Lifecycle<V, F>.stop() {
    stopSignal = true
}

inline fun <V, F> Lifecycle<V, F>.stopBy(predicate: Lifecycle<V, F>.() -> Boolean) {
    if (predicate(this)) {
        stop()
    }
}
