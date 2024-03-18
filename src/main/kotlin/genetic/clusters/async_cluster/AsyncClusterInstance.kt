package genetic.clusters.async_cluster

import genetic.chromosome.Chromosome
import genetic.clusters.AbstractCluster
import genetic.clusters.async_cluster.builder.AsyncClusterBuilder
import genetic.clusters.async_cluster.lifecycle.AsyncClusterLifecycle
import genetic.clusters.async_cluster.lifecycle.AsyncClusterLifecycleInstance
import genetic.clusters.async_cluster.operator.AsyncOperator
import genetic.clusters.async_cluster.operator.AsyncOperatorResult
import genetic.clusters.async_cluster.operator.AsyncOperatorTask
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlin.random.Random

internal class AsyncClusterInstance<V, F>(
    private val taskChannel: Channel<AsyncOperatorTask<V, F>>,
    private val resultChannel: Channel<AsyncOperatorResult<V, F>>,
) : AbstractCluster<V, F>(), AsyncClusterBuilder<V, F> {
    private val lifecycleScope: AsyncClusterLifecycle<V, F> by lazy {
        AsyncClusterLifecycleInstance(builder = this, resultChannel = resultChannel)
    }
    private var lifecycle: (AsyncClusterLifecycle<V, F>.() -> Unit)? = null

    override var randomSeed: Int = 0
        set(value) {
            random = Random(value)
        }
    override lateinit var populationFactory: (index: Int, random: Random) -> Chromosome<V, F>
    override lateinit var fitnessFunction: (V) -> F

    override var dispatchers: Array<CoroutineDispatcher> = arrayOf()
    override var operators: Array<AsyncOperator<V, F>> = arrayOf()
    override fun AsyncClusterBuilder<V, F>.lifecycle(lifecycle: AsyncClusterLifecycle<V, F>.() -> Unit) {
        this@AsyncClusterInstance.lifecycle = lifecycle
    }

    override suspend fun start() {
        TODO("Not yet implemented")
    }

}
