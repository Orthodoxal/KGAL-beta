package genetic.stat

import genetic.clusters.ClusterBuilder
import genetic.ga.GABuilder
import kotlinx.coroutines.Dispatchers
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

fun <V, F> GABuilder<V, F>.statGA(
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
}

fun <V, F> ClusterBuilder<V, F>.statCluster(
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
    setStatInstance(statisticsInstance)
}
