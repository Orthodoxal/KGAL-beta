package genetic.ga.core.processor.parallelism.config

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal const val DEFAULT_PARALLEL_WORKER_COUNT = 0
internal val DEFAULT_PARALLEL_DISPATCHER = Dispatchers.Default

interface ParallelismConfig {
    val workersCount: Int
    val dispatcher: CoroutineDispatcher

    companion object {
        const val NO_PARALLELISM = 0
    }
}

inline val ParallelismConfig.enabled get() = workersCount > 1

fun ParallelismConfig(
    workersCount: Int = DEFAULT_PARALLEL_WORKER_COUNT,
    dispatcher: CoroutineDispatcher = DEFAULT_PARALLEL_DISPATCHER,
): ParallelismConfig = ParallelismConfigScope(workersCount, dispatcher)

inline fun parallelismConfig(config: ParallelismConfigScope.() -> Unit): ParallelismConfig =
    ParallelismConfigScope().apply(config)

fun ParallelismConfig.clone() = ParallelismConfigScope(
    workersCount = workersCount,
    dispatcher = dispatcher,
)
