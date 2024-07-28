package genetic.clusters.base.operators

import genetic.clusters.base.lifecycle.ClusterLifecycle

inline fun ClusterLifecycle.stopBy(predicate: (ClusterLifecycle) -> Boolean) {
    if (predicate(this)) {
        stopSignal = true
    }
}
