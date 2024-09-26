package genetic.ga.core.parallelism.helper

import java.util.concurrent.atomic.AtomicInteger

interface ParallelismIterativeHelper {
    val currentIteration: AtomicInteger
    val endIteration: Int

    fun set(startIteration: Int, endIteration: Int)
}

fun ParallelismIterativeHelper(startIteration: Int = 0, endIteration: Int = 0): ParallelismIterativeHelper =
    ParallelismIterativeHelperInstance(currentIteration = AtomicInteger(startIteration), endIteration = endIteration)

inline fun ParallelismIterativeHelper.runActionIterative(
    action: (iteration: Int) -> Unit,
) {
    var iteration = currentIteration.getAndIncrement()
    while (iteration < endIteration) {
        action(iteration)
        iteration = currentIteration.getAndIncrement()
    }
}
