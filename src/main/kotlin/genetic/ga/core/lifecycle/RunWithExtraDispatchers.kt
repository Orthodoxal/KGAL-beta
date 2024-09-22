package genetic.ga.core.lifecycle

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend inline fun Lifecycle<*, *>.runActionIterative(action: suspend (iteration: Int) -> Unit) {
    var iteration = currentIterationMultiRun.getAndIncrement()
    while (iteration < maxIterationMultiRun) {
        action(iteration)
        iteration = currentIterationMultiRun.getAndIncrement()
    }
}

suspend inline fun Lifecycle<*, *>.runIterative(
    start: Int,
    end: Int,
    crossinline action: suspend CoroutineScope.() -> Unit
) {
    maxIterationMultiRun = end
    currentIterationMultiRun.set(start)
    coroutineScope { action() }
}

suspend inline fun Lifecycle<*, *>.runWithExtraDispatchersIterative(
    start: Int,
    end: Int,
    crossinline action: suspend (iteration: Int) -> Unit,
) = runIterative(start, end) {
    extraDispatchers.forEach { dispatcher -> launch(dispatcher) { runActionIterative(action) } }
}
