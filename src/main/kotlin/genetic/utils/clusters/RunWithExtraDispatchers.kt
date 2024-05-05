package genetic.utils.clusters

import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend inline fun <V, F> SimpleClusterLifecycle<V, F>.runWithExtraDispatchers(
    crossinline action: suspend () -> Unit,
) {
    coroutineScope {
        extraDispatchers?.map { dispatcher -> launch(dispatcher) { action() } }
    }
}

suspend inline fun <V, F> SimpleClusterLifecycle<V, F>.runWithExtraDispatchersIterative(
    start: Int,
    end: Int,
    crossinline action: suspend (iteration: Int) -> Unit,
) {
    maxIteration = end
    currentIteration.set(start)

    runWithExtraDispatchers {
        var iteration = currentIteration.getAndIncrement()
        while (iteration < maxIteration) {
            action(iteration)
            iteration = currentIteration.getAndIncrement()
        }
    }
}
