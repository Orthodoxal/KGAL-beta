package genetic.ga.core.processor

import genetic.ga.core.lifecycle.Lifecycle
import genetic.ga.core.processor.parallelism.config.enabled
import genetic.utils.loop
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.random.Random

inline fun Lifecycle<*, *>.process(
    parallelismLimit: Int,
    sequential: () -> Unit,
    parallel: () -> Unit,
) {
    if (parallelismConfig.enabled && parallelismLimit > 0) {
        parallel()
    } else {
        sequential()
    }
}

suspend inline fun Lifecycle<*, *>.process(
    parallelismLimit: Int,
    startIteration: Int,
    endIteration: Int,
    crossinline action: suspend (iteration: Int, random: Random) -> Unit,
) = process(
    parallelismLimit = parallelismLimit,
    sequential = { loop(startIteration, endIteration) { index -> action(index, random) } },
    parallel = {
        parallelProcess(parallelismLimit, startIteration, endIteration, action)
    }
)

suspend inline fun Lifecycle<*, *>.process(
    parallelismLimit: Int,
    startIteration: Int,
    endIteration: Int,
    step: Int,
    crossinline action: suspend (iteration: Int, random: Random) -> Unit,
) = process(
    parallelismLimit = parallelismLimit,
    sequential = { loop(startIteration, endIteration, step) { index -> action(index, random) } },
    parallel = {
        parallelProcess(parallelismLimit, startIteration, endIteration, step, action)
    }
)

suspend inline fun Lifecycle<*, *>.parallelProcess(
    parallelismLimit: Int,
    startIteration: Int,
    endIteration: Int,
    crossinline action: suspend (iteration: Int, random: Random) -> Unit,
) {
    coroutineScope {
        loop(start = 0, end = parallelismLimit) { workerIndex ->
            val workerRandom = Random(seed = random.nextInt())
            launch(parallelismConfig.dispatcher) {
                var index = workerIndex + startIteration
                while (index < endIteration) {
                    action(index, workerRandom)
                    index += parallelismLimit
                }
            }
        }
    }
}

suspend inline fun Lifecycle<*, *>.parallelProcess(
    parallelismLimit: Int,
    startIteration: Int,
    endIteration: Int,
    step: Int,
    crossinline action: suspend (iteration: Int, random: Random) -> Unit,
) {
    coroutineScope {
        val workerStep = step * parallelismLimit
        loop(start = 0, end = parallelismLimit) { workerIndex ->
            val workerRandom = Random(seed = random.nextInt())
            launch(parallelismConfig.dispatcher) {
                var index = workerIndex * step + startIteration
                while (index < endIteration) {
                    action(index, workerRandom)
                    index += workerStep
                }
            }
        }
    }
}
