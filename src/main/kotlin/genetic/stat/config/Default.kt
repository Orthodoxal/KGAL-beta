package genetic.stat.config

import genetic.stat.note.StatisticNote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.FlowCollector
import kotlin.coroutines.CoroutineContext

internal const val DEFAULT_REPLAY: Int = 0
internal const val DEFAULT_EXTRA_BUFFER_CAPACITY: Int = 100
internal val DEFAULT_ON_BUFFER_OVERFLOW: BufferOverflow = BufferOverflow.SUSPEND
internal val DEFAULT_COROUTINE_CONTEXT: CoroutineContext = Dispatchers.IO
internal const val DEFAULT_ENABLE_DEFAULT_COLLECTOR: Boolean = true
internal val DEFAULT_COLLECTOR: FlowCollector<StatisticNote<Any?>> = FlowCollector(::println)
