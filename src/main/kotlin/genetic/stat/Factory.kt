package genetic.stat

import genetic.ga.core.builder.GABuilder
import kotlinx.coroutines.channels.BufferOverflow
import kotlin.coroutines.CoroutineContext

fun stat(
    replay: Int,
    extraBufferCapacity: Int? = null,
    onBufferOverflow: BufferOverflow,
    beforeLifecycle: (StatisticsBuilder.() -> Unit)? = null,
    afterLifecycle: (StatisticsBuilder.() -> Unit)? = null,
    onLifecycleIteration: (StatisticsBuilder.() -> Unit)? = null,
): StatisticsInstance {
    val beforeLifecycleBuilder = StatisticsBuilder()
    val afterLifecycleBuilder = StatisticsBuilder()
    val onLifecycleIterationBuilder = StatisticsBuilder()

    beforeLifecycle?.invoke(beforeLifecycleBuilder)
    afterLifecycle?.invoke(afterLifecycleBuilder)
    onLifecycleIteration?.invoke(onLifecycleIterationBuilder)

    val statExtraBufferCapacity = extraBufferCapacity
        ?: (beforeLifecycleBuilder.size + afterLifecycleBuilder.size + onLifecycleIterationBuilder.size)

    return StatisticsInstance(
        replay,
        statExtraBufferCapacity,
        onBufferOverflow,
        beforeLifecycleBuilder,
        afterLifecycleBuilder,
        onLifecycleIterationBuilder,
    )
}

fun GABuilder<*, *, *>.statCluster(
    replay: Int = 0,
    extraBufferCapacity: Int? = null,
    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
    beforeLifecycle: (StatisticsBuilder.() -> Unit)? = null,
    afterLifecycle: (StatisticsBuilder.() -> Unit)? = null,
    onLifecycleIteration: (StatisticsBuilder.() -> Unit)? = null,
    coroutineContext: CoroutineContext? = null,
) {
    val statisticsInstance = stat(
        replay,
        extraBufferCapacity,
        onBufferOverflow,
        beforeLifecycle,
        afterLifecycle,
        onLifecycleIteration,
    )
    coroutineContext?.let { setStatInstance(statisticsInstance, coroutineContext) } ?: { stat = statisticsInstance }
}
