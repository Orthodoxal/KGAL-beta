package genetic.ga.core.processor.parallelism.config

import genetic.ga.core.config.AbstractConfigGAScope
import kotlinx.coroutines.CoroutineDispatcher

class ParallelismConfigScope(
    workersCount: Int = DEFAULT_PARALLEL_WORKER_COUNT,
    override var dispatcher: CoroutineDispatcher = DEFAULT_PARALLEL_DISPATCHER,
) : ParallelismConfig {
    override var workersCount: Int = workersCount
        set(value) {
            require(value > 1) { "Illegal count of workers for ParallelismConfig. Count must be at least 2" }
            field = value
        }
}

inline fun AbstractConfigGAScope<*, *, *>.parallelismConfig(
    config: ParallelismConfigScope.() -> Unit,
) {
    parallelismConfig = ParallelismConfigScope().apply(config)
}
