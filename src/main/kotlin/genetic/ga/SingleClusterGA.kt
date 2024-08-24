package genetic.ga

/*import genetic.clusters.base.Cluster
import genetic.clusters.base.builder.ClusterBuilder
import genetic.clusters.base.state.ClusterState
import genetic.clusters.base.state.StopPolicy
import genetic.ga.lifecycle.GALifecycle
import genetic.ga.lifecycle.launchCluster
import genetic.ga.state.GAState
import genetic.stat.StatisticsInstance
import genetic.utils.statOnLifecycleIteration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

internal class SingleClusterGA<V, F, L : GALifecycle<V, F>>(
    override val lifecycleScope: L,
) : AbstractGA<V, F, L>(), GABuilder<V, F, L> {
    private lateinit var cluster: Cluster<V, F>

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
        this@SingleClusterGA.lifecycle = lifecycle
    }

    override fun applyStatistics() {
        cluster.stat?.apply(::prepareStatistics)
    }

    override fun CoroutineScope.stopGA(coroutineContext: CoroutineContext, stopPolicy: StopPolicy) {
        launch(coroutineContext) { cluster.stop(stopPolicy) }
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
        if (this@SingleClusterGA.stopSignal) {
            state = GAState.STOPPED
            this@SingleClusterGA.stopSignal = false
            gaStatisticsCoroutineScope.coroutineContext.cancelChildren()
            return
        }
    }

    override fun setStatInstance(statisticsInstance: StatisticsInstance, coroutineContext: CoroutineContext) {
        gaStatisticsCoroutineContext = coroutineContext
        this.statisticsInstance = statisticsInstance
    }

    override fun addCluster(cluster: Cluster<V, F>): Cluster<V, F> = cluster.apply {
        if (this@SingleClusterGA::cluster.isInitialized) throw Exception("For single cluster GA maximum clusters count = 1")
        if (cluster.name == null) {
            val asClusterBuilder = (cluster as? ClusterBuilder<*, *>)
                ?: throw IllegalStateException("Cluster must have a name. Set default name FAILED cause cluster not implement ClusterBuilder")
            asClusterBuilder.name = "Population 1"
        }
        this@SingleClusterGA.cluster = cluster
    }

    private val baseSingleGALifecycle: suspend GALifecycle<*, *>.() -> Unit
        get() = {
            launchCluster(cluster)
            coroutineContext.job.children.forEach { it.join() }
        }
}*/
