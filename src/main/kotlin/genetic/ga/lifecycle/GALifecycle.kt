package genetic.ga.lifecycle

import genetic.clusters.Cluster
import genetic.ga.GABuilder
import kotlinx.coroutines.job
import kotlin.coroutines.coroutineContext

interface GALifecycle<V, F> : GABuilder<V, F> {
    var lifecycleStartOption: LifecycleStartOption
    var stopSignal: Boolean

    suspend fun launchClusters(clusters: List<Cluster<*, *>>) = when (lifecycleStartOption) {
        LifecycleStartOption.START -> clusters.forEach { it.start() }
        LifecycleStartOption.RESTART -> clusters.forEach { it.restart() }
        LifecycleStartOption.RESUME -> clusters.forEach { it.resume() }
    }.also { lifecycleStartOption = LifecycleStartOption.START }

    companion object {
        val BASE_LIFECYCLE: suspend GALifecycle<*, *>.() -> Unit = {
            launchClusters(clusters)
            coroutineContext.job.children.forEach { it.join() }
        }
        val BASE_BEFORE_LIFECYCLE: suspend GALifecycle<*, *>.() -> Unit = { }
        val BASE_AFTER_LIFECYCLE: suspend GALifecycle<*, *>.() -> Unit = { }
    }
}
