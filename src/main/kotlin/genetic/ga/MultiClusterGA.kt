package genetic.ga

import genetic.clusters.base.Cluster
import genetic.clusters.base.builder.ClusterBuilder
import genetic.clusters.base.state.ClusterState
import genetic.clusters.base.state.StopPolicy
import genetic.ga.lifecycle.GALifecycle
import genetic.ga.lifecycle.launchCluster
import genetic.ga.lifecycle.launchClusters
import genetic.ga.state.GAState
import genetic.stat.StatisticsInstance
import genetic.utils.clusters.checkClusterNameOrTrySetDefaultName
import genetic.utils.statOnLifecycleIteration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

internal class MultiClusterGA<V, F, L : GALifecycle<V, F>>(
    override val lifecycleScope: L,
) : AbstractGA<V, F, L>(), GABuilder<V, F, L> {
    private var clusters: MutableList<Cluster<V, F>> = mutableListOf()

    override var lifecycle: suspend L.() -> Unit = baseSingleGALifecycle
    override var beforeLifecycle: suspend L.() -> Unit = { }
    override var afterLifecycle: suspend L.() -> Unit = { }

    override var random: Random = Random
    override var randomSeed: Int = 0
        set(value) {
            random = Random(value)
        }

    override fun L.lifecycle(
        before: (suspend L.() -> Unit)?,
        after: (suspend L.() -> Unit)?,
        lifecycle: suspend L.() -> Unit
    ) {
        before?.let { beforeLifecycle = before }
        after?.let { afterLifecycle = after }
        this@MultiClusterGA.lifecycle = lifecycle
    }

    override fun applyStatistics() {
        clusters.mapNotNull { it.stat }.apply(::prepareStatistics)
    }

    override fun CoroutineScope.stopGA(coroutineContext: CoroutineContext, stopPolicy: StopPolicy) {
        clusters.forEach { launch(coroutineContext) { it.stop(stopPolicy) } }
    }

    override suspend fun L.execute() {
        lifecycle()
        statOnLifecycleIteration(statisticsInstance)

        // GA stopped by stopSignal from GALifecycle OR by Cluster FINISHED
        if (this.stopSignal || cluster.state == ClusterState.FINISHED) {
            state = GAState.FINISHED
            this.stopSignal = false
            return
        }

        // GA stopped by stopSignal
        if (this@MultiClusterGA.stopSignal) {
            state = GAState.STOPPED
            this@MultiClusterGA.stopSignal = false
            gaStatisticsCoroutineScope.coroutineContext.cancelChildren()
            return
        }
    }

    override fun setStatInstance(statisticsInstance: StatisticsInstance, coroutineContext: CoroutineContext) {
        gaStatisticsCoroutineContext = coroutineContext
        this.statisticsInstance = statisticsInstance
    }

    override fun addCluster(cluster: Cluster<V, F>): Cluster<V, F> = cluster.apply {
        checkClusterNameOrTrySetDefaultName(cluster, clusters)
        clusters.add(cluster)
    }

    private val baseSingleGALifecycle: suspend GALifecycle<*, *>.() -> Unit
        get() = {
            launchClusters(clusters)
            coroutineContext.job.children.forEach { it.join() }
        }
}
