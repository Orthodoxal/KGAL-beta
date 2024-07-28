package genetic.utils.clusters

import genetic.clusters.base.lifecycle.ClusterLifecycle
import genetic.clusters.cellular.lifecycle.CellularLifecycle
import genetic.clusters.panmictic.PanmicticLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend inline fun ClusterLifecycle.runActionIterative(action: suspend (iteration: Int) -> Unit) {
    var iteration = currentIteration.getAndIncrement()
    while (iteration < maxIteration) {
        action(iteration)
        iteration = currentIteration.getAndIncrement()
    }
}

suspend inline fun ClusterLifecycle.runIterative(
    start: Int,
    end: Int,
    crossinline action: suspend CoroutineScope.() -> Unit
) {
    maxIteration = end
    currentIteration.set(start)
    coroutineScope { action() }
}

suspend inline fun PanmicticLifecycle<*, *>.runWithExtraDispatchersIterative(
    start: Int,
    end: Int,
    crossinline action: suspend (iteration: Int) -> Unit,
) = runIterative(start, end) {
    extraDispatchers?.forEach { dispatcher -> launch(dispatcher) { runActionIterative(action) } }
}

suspend inline fun CellularLifecycle<*, *>.runWithExtraDispatchersIterative(
    start: Int,
    end: Int,
    crossinline action: suspend (iteration: Int) -> Unit,
) = runIterative(start, end) {
    extraDispatchers?.forEach { dispatcher -> launch(dispatcher) { runActionIterative(action) } }
}
