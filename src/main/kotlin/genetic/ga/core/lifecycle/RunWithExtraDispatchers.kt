package genetic.ga.core.lifecycle

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend inline fun GALifecycle<*, *>.runActionIterative(action: suspend (iteration: Int) -> Unit) {
    var iteration = currentIteration.getAndIncrement()
    while (iteration < maxIteration) {
        action(iteration)
        iteration = currentIteration.getAndIncrement()
    }
}

suspend inline fun GALifecycle<*, *>.runIterative(
    start: Int,
    end: Int,
    crossinline action: suspend CoroutineScope.() -> Unit
) {
    maxIteration = end
    currentIteration.set(start)
    coroutineScope { action() }
}

suspend inline fun GALifecycle<*, *>.runWithExtraDispatchersIterative(
    start: Int,
    end: Int,
    crossinline action: suspend (iteration: Int) -> Unit,
) = runIterative(start, end) {
    extraDispatchers?.forEach { dispatcher -> launch(dispatcher) { runActionIterative(action) } }
}
