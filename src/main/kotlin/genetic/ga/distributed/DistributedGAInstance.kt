package genetic.ga.distributed

import genetic.chromosome.Chromosome
import genetic.clusters.Cluster
import genetic.ga.AbstractGA
import genetic.ga.distributed.builder.DistributedGABuilder
import genetic.ga.distributed.lifecycle.DistributedGALifecycle
import genetic.ga.distributed.lifecycle.DistributedGALifecycleInstance
import genetic.ga.panmictic.builder.PanmicticGABuilder
import genetic.ga.panmictic.lifecycle.PanmicticGALifecycle
import genetic.ga.state.GAState
import genetic.utils.checkClusterNameOrTrySetDefaultName
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

internal class DistributedGAInstance<V, F> : AbstractGA<V, F>(), DistributedGABuilder<V, F> {
    private val lifecycleScope: DistributedGALifecycle<V, F> by lazy { DistributedGALifecycleInstance(builder = this) }
    private var beforeLifecycle: suspend DistributedGALifecycle<V, F>.() -> Unit = { }
    private var lifecycle: suspend DistributedGALifecycle<V, F>.() -> Unit = BASE_LIFECYCLE
    private var afterLifecycle: suspend DistributedGALifecycle<V, F>.() -> Unit = { }
    override var random: Random = Random

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
        checkClusterNameOrTrySetDefaultName(cluster, clusters)
        clusters.add(cluster)
    }

    override fun DistributedGABuilder<V, F>.lifecycleDistributed(
        defaultLifecycleRunFirst: Boolean,
        before: (suspend DistributedGALifecycle<V, F>.() -> Unit)?,
        after: (suspend DistributedGALifecycle<V, F>.() -> Unit)?,
        lifecycle: suspend DistributedGALifecycle<V, F>.() -> Unit
    ) {
        before?.let { beforeLifecycle = before }
        after?.let { afterLifecycle = after }
        this@DistributedGAInstance.lifecycle = {
            if (defaultLifecycleRunFirst) BASE_LIFECYCLE()
            lifecycle()
        }
    }

    override var randomSeed: Int = 0
        set(value) {
            random = Random(value)
        }

    override fun PanmicticGABuilder<V, F>.lifecycle(
        before: (suspend PanmicticGALifecycle<V, F>.() -> Unit)?,
        after: (suspend PanmicticGALifecycle<V, F>.() -> Unit)?,
        lifecycle: suspend PanmicticGALifecycle<V, F>.() -> Unit
    ) {
        throw IllegalStateException("Distributed GA not support lifecycle use lifecycleDistributed")
    }

    override lateinit var populationFactory: (index: Int, random: Random) -> Chromosome<V, F>
    override lateinit var fitnessFunction: (V) -> F

    companion object {
        private val BASE_LIFECYCLE: suspend DistributedGALifecycle<*, *>.() -> Unit = {
            for (cluster in clusters) cluster.start()
            coroutineContext.job.children.forEach { it.join() }
        }
    }
}
