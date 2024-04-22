package genetic.stat

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

class StatisticsInstance(
    replay: Int,
    extraBufferCapacity: Int,
    onBufferOverflow: BufferOverflow,
    override val beforeLifecycle: Set<StatisticRegister>,
    override val afterLifecycle: Set<StatisticRegister>,
    override val onLifecycleIteration: Set<StatisticRegister>,
) : Statistics, MutableSharedFlow<StatisticNote> by MutableSharedFlow(replay, extraBufferCapacity, onBufferOverflow)
