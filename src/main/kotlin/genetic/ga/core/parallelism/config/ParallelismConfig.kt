package genetic.ga.core.parallelism.config

import genetic.ga.core.parallelism.helper.ParallelismIterativeHelper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal const val DEFAULT_PARALLEL_WORKER_COUNT = 0
internal val DEFAULT_PARALLEL_DISPATCHER = Dispatchers.Default

interface ParallelismConfig {
    val count: Int
    val dispatcher: CoroutineDispatcher
    val parallelismIterativeHelper: ParallelismIterativeHelper
}

fun ParallelismConfig(): ParallelismConfig = ParallelismConfigScope()

inline val ParallelismConfig.enabled get() = count > 1

fun ParallelismConfig.clone() = ParallelismConfigScope(
    count = count,
    dispatcher = dispatcher,
    parallelismIterativeHelper = ParallelismIterativeHelper(
        startIteration = parallelismIterativeHelper.currentIteration.get(),
        endIteration = parallelismIterativeHelper.endIteration,
    ),
)
