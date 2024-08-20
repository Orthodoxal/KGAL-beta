package genetic.ga.panmictic

import genetic.chromosome.Chromosome
import genetic.clusters.base.builder.ClusterBuilder
import genetic.clusters.base.state.ClusterState
import genetic.clusters.base.state.StopPolicy
import genetic.clusters.panmictic.PanmicticCluster
import genetic.ga.AbstractGA
import genetic.ga.lifecycle.GALifecycle.Companion.BASE_AFTER_LIFECYCLE
import genetic.ga.lifecycle.GALifecycle.Companion.BASE_BEFORE_LIFECYCLE
import genetic.ga.lifecycle.GALifecycle.Companion.BASE_LIFECYCLE
import genetic.ga.lifecycle.LifecycleStartOption
import genetic.ga.panmictic.builder.PanmicticGABuilder
import genetic.ga.panmictic.lifecycle.PanmicticGALifecycle
import genetic.ga.panmictic.lifecycle.PanmicticGALifecycleInstance
import genetic.ga.state.GAState
import genetic.stat.StatisticsInstance
import genetic.utils.statOnLifecycleIteration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random

internal class PanmicticGA<V, F> : AbstractGA<V, F, PanmicticGALifecycle<V, F>>(), PanmicticGABuilder<V, F> {
    private lateinit var panmicticCluster: PanmicticCluster<V, F>

    override val lifecycleScope: PanmicticGALifecycle<V, F> =
        PanmicticGALifecycleInstance(
            builder = this@PanmicticGA,
            lifecycleStartOption = LifecycleStartOption.START,
        )
    override var lifecycle: suspend PanmicticGALifecycle<V, F>.() -> Unit = BASE_LIFECYCLE
    override var beforeLifecycle: suspend PanmicticGALifecycle<V, F>.() -> Unit = BASE_BEFORE_LIFECYCLE
    override var afterLifecycle: suspend PanmicticGALifecycle<V, F>.() -> Unit = BASE_AFTER_LIFECYCLE

    override var random: Random = Random
    override var randomSeed: Int = 0
        set(value) {
            random = Random(value)
        }
    override lateinit var populationFactory: (index: Int, random: Random) -> Chromosome<V, F>
    override lateinit var fitnessFunction: (V) -> F

    fun PanmicticCluster<V, F>.unaryPlus(): PanmicticCluster<V, F> = this.apply(::addCluster)

    override fun applyStatistics() {
        panmicticCluster.stat?.apply(::prepareStatistics)
    }

    override fun CoroutineScope.stopGA(coroutineContext: CoroutineContext, stopPolicy: StopPolicy) {
        launch(coroutineContext) { panmicticCluster.stop(stopPolicy) }
    }

    override suspend fun PanmicticGALifecycle<V, F>.execute() {
        lifecycle()
        statOnLifecycleIteration(statisticsInstance)

        // GA stopped by stopSignal from GALifecycle OR by Cluster FINISHED
        if (this.stopSignal || panmicticCluster.state == ClusterState.FINISHED) {
            state = GAState.FINISHED
            this.stopSignal = false
            return
        }

        // GA stopped by stopSignal
        if (this@PanmicticGA.stopSignal) {
            state = GAState.STOPPED
            this@PanmicticGA.stopSignal = false
            gaStatisticsCoroutineScope.coroutineContext.cancelChildren()
            return
        }
    }

    private fun addCluster(cluster: PanmicticCluster<V, F>) {
        if (this@PanmicticGA::panmicticCluster.isInitialized) throw Exception("Panmictic clusters maximum count = 1")
        if (cluster.name == null) {
            val asClusterBuilder = (cluster as? ClusterBuilder<*, *>)
                ?: throw IllegalStateException("Cluster must have a name. Set default name FAILED cause cluster not implement ClusterBuilder")
            asClusterBuilder.name = "Population 1"
        }
        panmicticCluster = cluster
    }

    override fun PanmicticGABuilder<V, F>.lifecycle(
        before: (suspend PanmicticGALifecycle<V, F>.() -> Unit)?,
        after: (suspend PanmicticGALifecycle<V, F>.() -> Unit)?,
        lifecycle: suspend PanmicticGALifecycle<V, F>.() -> Unit,
    ) {
        before?.let { beforeLifecycle = before }
        after?.let { afterLifecycle = after }
        this@PanmicticGA.lifecycle = lifecycle
    }

    override fun setStatInstance(statisticsInstance: StatisticsInstance, coroutineContext: CoroutineContext) {
        gaStatisticsCoroutineContext = coroutineContext
        this.statisticsInstance = statisticsInstance
    }
}
