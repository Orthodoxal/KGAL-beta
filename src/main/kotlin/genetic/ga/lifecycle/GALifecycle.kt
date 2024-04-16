package genetic.ga.lifecycle

import genetic.clusters.Cluster

interface GALifecycle {
    var lifecycleStartOption: LifecycleStartOption

    suspend fun launchClusters(clusters: List<Cluster<*, *>>) = when (lifecycleStartOption) {
        LifecycleStartOption.START -> clusters.forEach { it.start() }
        LifecycleStartOption.RESTART -> clusters.forEach { it.restart() }
        LifecycleStartOption.RESUME -> clusters.forEach { it.resume() }
    }.also { lifecycleStartOption = LifecycleStartOption.START }
}
