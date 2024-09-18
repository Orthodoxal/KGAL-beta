package genetic.stat.config

import genetic.ga.core.config.AbstractConfigGAScope
import genetic.stat.note.StatisticNote
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.FlowCollector
import kotlin.coroutines.CoroutineContext

class StatisticsConfigScope(
    override var replay: Int = DEFAULT_REPLAY,
    override var extraBufferCapacity: Int = DEFAULT_EXTRA_BUFFER_CAPACITY,
    override var onBufferOverflow: BufferOverflow = DEFAULT_ON_BUFFER_OVERFLOW,
    override var coroutineContext: CoroutineContext = DEFAULT_COROUTINE_CONTEXT,
    override var enableDefaultCollector: Boolean = DEFAULT_ENABLE_DEFAULT_COLLECTOR,
    override var defaultCollector: FlowCollector<StatisticNote<Any?>> = DEFAULT_COLLECTOR,
) : StatisticsConfig

inline fun AbstractConfigGAScope<*, *, *>.statConfig(
    config: StatisticsConfigScope.() -> Unit,
) {
    statisticsConfig = StatisticsConfigScope().apply(config)
}
