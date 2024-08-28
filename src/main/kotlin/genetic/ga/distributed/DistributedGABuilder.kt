package genetic.ga.distributed

import genetic.ga.core.GA
import genetic.ga.core.builder.GABuilder
import genetic.ga.cellular.CellularGA
import genetic.ga.cellular.CellularGABuilder
import genetic.ga.distributed.lifecycle.DistributedLifecycle
import genetic.ga.panmictic.PanmicticGA
import genetic.ga.panmictic.PanmicticGABuilder
import genetic.utils.loop
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.random.Random

interface DistributedGABuilder<V, F> : GABuilder<V, F, DistributedLifecycle<V, F>> {
    fun addCluster(ga: GA<V, F>): GA<V, F>
    operator fun GA<V, F>.unaryPlus(): GA<V, F> = addCluster(this)
}

inline fun <V, F> DistributedGABuilder<V, F>.pGA(
    builder: PanmicticGABuilder<V, F>.() -> Unit
) = addCluster(PanmicticGA<V, F>(Random(random.nextInt())).apply(builder))

inline fun <V, F> DistributedGABuilder<V, F>.pGAs(
    count: Int,
    mainDispatcher: CoroutineDispatcher? = null,
    builder: PanmicticGABuilder<V, F>.(index: Int) -> Unit
) = loop(start = 0, end = count) { index ->
    +PanmicticGA<V, F>(Random(random.nextInt())).apply {
        builder(index)
        mainDispatcher?.let { this.mainDispatcher = mainDispatcher }
    }
}

inline fun <V, F> DistributedGABuilder<V, F>.cGA(
    builder: CellularGABuilder<V, F>.() -> Unit
) = +CellularGA<V, F>(Random(random.nextInt())).apply(builder)

inline fun <V, F> DistributedGABuilder<V, F>.cGAs(
    count: Int,
    mainDispatcher: CoroutineDispatcher? = null,
    builder: CellularGABuilder<V, F>.(index: Int) -> Unit
) = loop(start = 0, end = count) { index ->
    +CellularGA<V, F>(Random(random.nextInt())).apply {
        builder(index)
        mainDispatcher?.let { this.mainDispatcher = mainDispatcher }
    }
}
