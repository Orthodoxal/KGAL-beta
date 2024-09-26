package genetic.statistics.config

import genetic.statistics.note.StatisticNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlin.coroutines.CoroutineContext

internal const val DEFAULT_REPLAY: Int = 0
internal const val DEFAULT_EXTRA_BUFFER_CAPACITY: Int = 100
internal val DEFAULT_ON_BUFFER_OVERFLOW: BufferOverflow = BufferOverflow.SUSPEND
internal val DEFAULT_COROUTINE_CONTEXT: CoroutineContext = Dispatchers.IO
internal const val DEFAULT_ENABLE_DEFAULT_COLLECTOR: Boolean = true
internal val DEFAULT_COLLECTOR: FlowCollector<StatisticNote<Any?>> = FlowCollector(::println)

interface StatisticsConfig {
    val replay: Int
    val extraBufferCapacity: Int
    val onBufferOverflow: BufferOverflow
    val coroutineContext: CoroutineContext
    val enableDefaultCollector: Boolean
    val defaultCollector: FlowCollector<StatisticNote<Any?>>

    companion object Default : StatisticsConfig by StatisticsConfigScope()
}

val StatisticsConfig.flow: MutableSharedFlow<StatisticNote<Any?>>
    get() = MutableSharedFlow(replay, extraBufferCapacity, onBufferOverflow)

val StatisticsConfig.newCoroutineScope: CoroutineScope
    get() = CoroutineScope(SupervisorJob() + coroutineContext)
