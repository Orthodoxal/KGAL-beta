package genetic.stat

import kotlinx.coroutines.flow.SharedFlow

interface Statistics : SharedFlow<StatisticNote> {
    val beforeLifecycle: Set<StatisticRegister>
    val afterLifecycle: Set<StatisticRegister>
    val onLifecycleIteration: Set<StatisticRegister>
}
