package genetic.clusters.base.operators

import genetic.clusters.base.lifecycle.ClusterLifecycle

inline fun <V, F> ClusterLifecycle<V, F>.stopBy(predicate: (ClusterLifecycle<V, F>) -> Boolean) {
    if (predicate(this)) {
        stopSignal = true
    }
}
