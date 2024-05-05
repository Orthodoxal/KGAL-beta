package genetic.ga.distributed.operators.stop

import genetic.ga.distributed.lifecycle.DistributedGALifecycle

fun <V, F> DistributedGALifecycle<V, F>.stopBy(predicate: (DistributedGALifecycle<V, F>) -> Boolean) {
    if (predicate(this)) {
        stopSignal = true
    }
}
