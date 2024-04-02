package genetic.ga.panmictic

import genetic.chromosome.Chromosome
import genetic.clusters.Cluster
import genetic.ga.AbstractGA
import genetic.ga.panmictic.builder.PanmicticGABuilder
import genetic.ga.panmictic.lifecycle.PanmicticGALifecycle
import genetic.ga.panmictic.lifecycle.PanmicticGALifecycleInstance
import genetic.ga.state.GAState
import genetic.utils.checkClusterNameOrTrySetDefaultName
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

internal class PanmicticGAInstance<V, F> : AbstractGA<V, F>(), PanmicticGABuilder<V, F> {
    private val lifecycleScope: PanmicticGALifecycle<V, F> by lazy { PanmicticGALifecycleInstance(builder = this) }
    private var beforeLifecycle: suspend PanmicticGALifecycle<V, F>.() -> Unit = { }
    private var lifecycle: suspend PanmicticGALifecycle<V, F>.() -> Unit = BASE_LIFECYCLE
    private var afterLifecycle: suspend PanmicticGALifecycle<V, F>.() -> Unit = { }
    private var random: Random = Random

    override suspend fun start(coroutineContext: CoroutineContext) {
        state = GAState.STARTED
        coroutineScope {
            gaJob = launch(coroutineContext) { startGA() }
        }
        state = GAState.FINISHED
    }

    private suspend fun startGA() {
        with(lifecycleScope) {
            beforeLifecycle()
            while (iteration < maxIteration) {
                lifecycle()
                super.iteration++
            }
            afterLifecycle()
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

    companion object {
        private val BASE_LIFECYCLE: suspend PanmicticGALifecycle<*, *>.() -> Unit = {
            clusters.forEach { it.start() }
            coroutineContext.job.children.forEach { it.join() }
        }
    }
}
