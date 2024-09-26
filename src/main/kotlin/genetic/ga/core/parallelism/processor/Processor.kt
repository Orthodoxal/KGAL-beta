package genetic.ga.core.parallelism.processor

import genetic.ga.core.lifecycle.Lifecycle
import genetic.ga.core.parallelism.config.enabled
import genetic.ga.core.parallelism.helper.runActionIterative
import genetic.utils.loop
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

inline fun Lifecycle<*, *>.execute(
    parallelWorkersLimit: Int,
    sequential: () -> Unit,
    parallel: () -> Unit,
) {
    if (parallelismConfig.enabled && parallelWorkersLimit > 0) {
        parallel()
    } else {
        sequential()
    }
}

suspend inline fun Lifecycle<*, *>.execute(
    parallelWorkersLimit: Int,
    startIteration: Int,
    endIteration: Int,
    crossinline action: suspend (iteration: Int) -> Unit,
) {
    if (parallelismConfig.enabled && parallelWorkersLimit > 0) {
        parallelExecute(parallelWorkersLimit, startIteration, endIteration, action)
    } else {
        loop(startIteration, endIteration) { index -> action(index) }
    }
}

suspend inline fun Lifecycle<*, *>.execute(
    parallelWorkersLimit: Int,
    startIteration: Int,
    endIteration: Int,
    step: Int,
    crossinline action: suspend (iteration: Int) -> Unit,
) {
    if (parallelismConfig.enabled && parallelWorkersLimit > 0) {
        parallelExecute(parallelWorkersLimit, startIteration, endIteration, action)
    } else {
        loop(startIteration, endIteration, step) { index -> action(index) }
    }
}

suspend inline fun Lifecycle<*, *>.execute(
    parallelWorkersLimit: Int,
    startIteration: Int,
    endIteration: Int,
    sequential: (iteration: Int) -> Unit,
    crossinline parallel: suspend (iteration: Int) -> Unit,
) {
    if (parallelismConfig.enabled && parallelWorkersLimit > 0) {
        parallelExecute(parallelWorkersLimit, startIteration, endIteration, parallel)
    } else {
        loop(startIteration, endIteration, sequential)
    }
}

suspend inline fun Lifecycle<*, *>.parallelExecute(
    parallelWorkersLimit: Int,
    startIteration: Int,
    endIteration: Int,
    crossinline action: suspend (iteration: Int) -> Unit,
) {
    parallelismConfig.parallelismIterativeHelper.set(startIteration, endIteration)
    coroutineScope {
        loop(start = 0, end = parallelWorkersLimit) {
            launch(parallelismConfig.dispatcher) {
                parallelismConfig.parallelismIterativeHelper.runActionIterative { iteration -> action(iteration) }
            }
        }
    }
}
