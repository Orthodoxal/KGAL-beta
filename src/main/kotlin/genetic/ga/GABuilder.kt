package genetic.ga

import genetic.clusters.base.Cluster
import genetic.ga.lifecycle.GALifecycle
import genetic.stat.StatisticsInstance
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random

interface GABuilder<V, F, L : GALifecycle<V, F>> {
    val random: Random
    var randomSeed: Int

    fun L.lifecycle(
        before: (suspend L.() -> Unit)? = null,
        after: (suspend L.() -> Unit)? = null,
        lifecycle: suspend L.() -> Unit,
    )

    fun setStatInstance(statisticsInstance: StatisticsInstance, coroutineContext: CoroutineContext)
    fun addCluster(cluster: Cluster<V, F>): Cluster<V, F>
    operator fun Cluster<V, F>.unaryPlus(): Cluster<V, F> = addCluster(this)
}
