package genetic.stat

import genetic.clusters.base.builder.ClusterBuilder
import kotlinx.coroutines.channels.BufferOverflow

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

/*fun <V, F> GABuilder<V, F>.statGA(
    coroutineContext: CoroutineContext = Dispatchers.IO,
    replay: Int = 0,
    extraBufferCapacity: Int? = null,
    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
    beforeLifecycle: (StatisticsBuilder.() -> Unit)? = null,
    afterLifecycle: (StatisticsBuilder.() -> Unit)? = null,
    onLifecycleIteration: (StatisticsBuilder.() -> Unit)? = null,
) {
    val statisticsInstance = stat(
        replay,
        extraBufferCapacity,
        onBufferOverflow,
        beforeLifecycle,
        afterLifecycle,
        onLifecycleIteration,
    )
    setStatInstance(statisticsInstance, coroutineContext)
}*/

fun ClusterBuilder<*, *, *>.statCluster(
    replay: Int = 0,
    extraBufferCapacity: Int? = null,
    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
    beforeLifecycle: (StatisticsBuilder.() -> Unit)? = null,
    afterLifecycle: (StatisticsBuilder.() -> Unit)? = null,
    onLifecycleIteration: (StatisticsBuilder.() -> Unit)? = null,
) {
    val statisticsInstance = stat(
        replay,
        extraBufferCapacity,
        onBufferOverflow,
        beforeLifecycle,
        afterLifecycle,
        onLifecycleIteration,
    )
    stat = statisticsInstance
}
