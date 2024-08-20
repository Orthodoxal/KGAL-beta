package genetic.ga.lifecycle

import genetic.clusters.base.Cluster
import genetic.ga.GABuilder

interface GALifecycle<V, F> : GABuilder<V, F, GALifecycle<V, F>>  {
    var lifecycleStartOption: LifecycleStartOption
    var stopSignal: Boolean
}

suspend fun GALifecycle<*, *>.launchCluster(cluster: Cluster<*, *>) = when (lifecycleStartOption) {
    LifecycleStartOption.START -> cluster.start()
    LifecycleStartOption.RESTART -> cluster.restart()
    LifecycleStartOption.RESUME -> cluster.resume()
}.also { lifecycleStartOption = LifecycleStartOption.START }

suspend fun GALifecycle<*, *>.launchClusters(clusters: List<Cluster<*, *>>) = when (lifecycleStartOption) {
    LifecycleStartOption.START -> clusters.forEach { it.start() }
    LifecycleStartOption.RESTART -> clusters.forEach { it.restart() }
    LifecycleStartOption.RESUME -> clusters.forEach { it.resume() }
}.also { lifecycleStartOption = LifecycleStartOption.START }
