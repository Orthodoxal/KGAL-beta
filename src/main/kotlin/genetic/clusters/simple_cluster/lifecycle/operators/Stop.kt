package genetic.clusters.simple_cluster.lifecycle.operators

import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle

inline fun <V, F> SimpleClusterLifecycle<V, F>.stopBy(predicate: (SimpleClusterLifecycle<V, F>) -> Boolean) {
    if (predicate(this)) {
        stopSignal = true
    }
}
