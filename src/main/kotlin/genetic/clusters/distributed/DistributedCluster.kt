package genetic.clusters.distributed

import genetic.clusters.base.AbstractCluster
import genetic.clusters.base.Cluster
import genetic.clusters.base.builder.ClusterBuilder
import genetic.clusters.base.state.ClusterState
import genetic.clusters.distributed.lifecycle.DistributedLifecycle
import genetic.clusters.distributed.lifecycle.DistributedLifecycleInstance
import genetic.clusters.distributed.lifecycle.LifecycleStartOption
import genetic.utils.clusters.checkClusterNameOrTrySetDefaultName
import kotlinx.coroutines.launch

class DistributedCluster<V, F> : AbstractCluster<V, F, DistributedLifecycle<V, F>>(), DistributedClusterBuilder<V, F> {
    var clusters: MutableList<Cluster<V, F>> = mutableListOf()
    // override var population: Population<V, F> TODO() Реализовать как общую популяцию

    override val lifecycleScope: DistributedLifecycle<V, F> by lazy { DistributedLifecycleInstance(this) }
    override lateinit var lifecycle: suspend DistributedLifecycle<V, F>.() -> Unit

    override suspend fun start() {
        if (state == ClusterState.STARTED) return
        lifecycleScope.lifecycleStartOption = LifecycleStartOption.Start
        startByOption(iterationFrom = 0)
    }

    override suspend fun resume() {
        if (state != ClusterState.STOPPED)
            throw IllegalStateException("Cluster $name state $state incorrect for resuming: State must be STOPPED")

        lifecycleScope.lifecycleStartOption = LifecycleStartOption.Resume
        startByOption(iterationFrom = iteration)
    }

    override suspend fun restart(resetPopulation: Boolean) {
        lifecycleScope.lifecycleStartOption = LifecycleStartOption.Restart(resetPopulation)
        startByOption(iterationFrom = 0)
    }

    override fun prepareStatistics() {
        super.prepareStatistics()
        with(statisticsCoroutineScope) {
            clusters.forEach {
                launch { it.stat?.collect { stat?.emit(it) } }
            }
        }
    }

    override suspend fun startByOption(iterationFrom: Int) {
        prepareClusters()
        super.startByOption(iterationFrom)
    }

    override fun addCluster(cluster: Cluster<V, F>): Cluster<V, F> = cluster.apply {
        checkClusterNameOrTrySetDefaultName(cluster, clusters)
        clusters.add(this)
    }

    private fun prepareClusters() {
        clusters
            .mapNotNull { it as? ClusterBuilder<V, F, *> }
            .forEachIndexed { index, cluster ->
                if (cluster.randomSeed == 0 && randomSeed != 0) {
                    cluster.randomSeed = random.nextInt()
                }

                if (cluster.fitnessFunction == null) {
                    cluster.fitnessFunction = fitnessFunction
                }

                if (cluster.mainDispatcher == null) {
                    val extraDispatcher = extraDispatchers?.getOrNull(index)
                    cluster.mainDispatcher = extraDispatcher
                }
            }
    }
}