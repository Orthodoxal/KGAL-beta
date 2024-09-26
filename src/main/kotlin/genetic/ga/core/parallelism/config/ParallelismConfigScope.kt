package genetic.ga.core.parallelism.config

import genetic.ga.core.config.AbstractConfigGAScope
import genetic.ga.core.parallelism.helper.ParallelismIterativeHelper
import kotlinx.coroutines.CoroutineDispatcher

class ParallelismConfigScope(
    count: Int = DEFAULT_PARALLEL_WORKER_COUNT,
    override var dispatcher: CoroutineDispatcher = DEFAULT_PARALLEL_DISPATCHER,
    override var parallelismIterativeHelper: ParallelismIterativeHelper = ParallelismIterativeHelper(),
) : ParallelismConfig {
    override var count: Int = count
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
