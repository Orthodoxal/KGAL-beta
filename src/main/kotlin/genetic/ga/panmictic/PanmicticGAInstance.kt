package genetic.ga.panmictic

import genetic.chromosome.Chromosome
import genetic.clusters.Cluster
import genetic.ga.AbstractGA
import genetic.ga.lifecycle.GALifecycle.Companion.BASE_LIFECYCLE
import genetic.ga.lifecycle.LifecycleStartOption
import genetic.ga.panmictic.builder.PanmicticGABuilder
import genetic.ga.panmictic.lifecycle.PanmicticGALifecycle
import genetic.ga.panmictic.lifecycle.PanmicticGALifecycleInstance
import genetic.stat.StatisticsInstance
import genetic.utils.clusters.checkClusterNameOrTrySetDefaultName
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random

internal class PanmicticGAInstance<V, F> : AbstractGA<V, F>(), PanmicticGABuilder<V, F> {
    private var beforeLifecycle: suspend PanmicticGALifecycle<V, F>.() -> Unit = { }
    private var lifecycle: suspend PanmicticGALifecycle<V, F>.() -> Unit = BASE_LIFECYCLE
    private var afterLifecycle: suspend PanmicticGALifecycle<V, F>.() -> Unit = { }
    override var random: Random = Random

    override fun CoroutineScope.startByOption(
        startOption: LifecycleStartOption,
        iterationFrom: Int,
        coroutineContext: CoroutineContext
    ) {
        val lifecycleScope = PanmicticGALifecycleInstance(
            builder = this@PanmicticGAInstance,
            lifecycleStartOption = startOption,
        )

        launchGA(iterationFrom, coroutineContext) {
            baseStartGA(lifecycleScope, beforeLifecycle, lifecycle, afterLifecycle)
        }
    }

    override fun addCluster(cluster: Cluster<V, F>): Cluster<V, F> = cluster.apply {
        if (clusters.isNotEmpty()) throw Exception("Panmictic clusters maximum count = 1")
        checkClusterNameOrTrySetDefaultName(cluster, clusters)
        clusters.add(cluster)
    }

    override fun PanmicticGABuilder<V, F>.lifecycle(
        before: (suspend PanmicticGALifecycle<V, F>.() -> Unit)?,
        after: (suspend PanmicticGALifecycle<V, F>.() -> Unit)?,
        lifecycle: suspend PanmicticGALifecycle<V, F>.() -> Unit,
    ) {
        before?.let { beforeLifecycle = before }
        after?.let { afterLifecycle = after }
        this@PanmicticGAInstance.lifecycle = lifecycle
    }

    override var randomSeed: Int = 0
        set(value) {
            random = Random(value)
        }
    override lateinit var populationFactory: (index: Int, random: Random) -> Chromosome<V, F>
    override lateinit var fitnessFunction: (V) -> F

    override fun setStatInstance(statisticsInstance: StatisticsInstance, coroutineContext: CoroutineContext) {
        gaStatisticsCoroutineContext = coroutineContext
        this.statisticsInstance = statisticsInstance
    }
}
