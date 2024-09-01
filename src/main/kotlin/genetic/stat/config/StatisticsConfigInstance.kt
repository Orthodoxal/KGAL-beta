package genetic.stat.config

import genetic.ga.core.builder.GABuilder
import genetic.stat.note.StatisticNote
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.FlowCollector
import kotlin.coroutines.CoroutineContext

internal class StatisticsConfigInstance(
    override val replay: Int,
    override val extraBufferCapacity: Int,
    override val onBufferOverflow: BufferOverflow,
    override val coroutineContext: CoroutineContext,
    override val enableDefaultCollector: Boolean,
    override val defaultCollector: FlowCollector<StatisticNote<Any?>>,
) : StatisticsConfig

fun GABuilder<*, *, *>.statConfig(
    enableDefaultCollector: Boolean = DEFAULT_ENABLE_DEFAULT_COLLECTOR,
    replay: Int = DEFAULT_REPLAY,
    extraBufferCapacity: Int = DEFAULT_EXTRA_BUFFER_CAPACITY,
    onBufferOverflow: BufferOverflow = DEFAULT_ON_BUFFER_OVERFLOW,
    coroutineContext: CoroutineContext = DEFAULT_COROUTINE_CONTEXT,
    defaultCollector: FlowCollector<StatisticNote<Any?>> = DEFAULT_COLLECTOR,
) {
    StatisticsConfigInstance(
        replay,
        extraBufferCapacity,
        onBufferOverflow,
        coroutineContext,
        enableDefaultCollector,
        defaultCollector,
    ).applyToGA()
}
