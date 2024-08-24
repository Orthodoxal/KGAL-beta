package genetic.ga

/*
import genetic.clusters.simpleCluster
import genetic.clusters.simple_cluster.builder.SimpleClusterBuilder
import genetic.ga.cellular.CellularGAInstance
import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.distributed.DistributedGAInstance
import genetic.ga.distributed.builder.DistributedGABuilder
import genetic.ga.panmictic.PanmicticGA
import genetic.ga.panmictic.builder.PanmicticGABuilder

fun <V, F> panmicticGA(): Pair<GA<V, F>, PanmicticGABuilder<V, F>> =
    PanmicticGA<V, F>().let { it to it }

inline fun <V, F> panmicticGA(builder: PanmicticGABuilder<V, F>.() -> Unit): GA<V, F> {
    val (ga, gaBuilder) = panmicticGA<V, F>()
    gaBuilder.apply(builder)
    return ga
}

inline fun <V, F> simplePanmicticGA(
    builder: SimpleClusterBuilder<V, F>.(panmicticBuilder: PanmicticGABuilder<V, F>) -> Unit,
) = panmicticGA {
    +simpleCluster<V, F> { builder(this@panmicticGA) }
}

fun <V, F> distributedGA(): Pair<GA<V, F>, DistributedGABuilder<V, F>> =
    DistributedGAInstance<V, F>().let { it to it }

inline fun <V, F> distributedGA(builder: DistributedGABuilder<V, F>.() -> Unit): GA<V, F> {
    val (ga, gaBuilder) = distributedGA<V, F>()
    gaBuilder.apply(builder)
    return ga
}

fun <V, F> cellularGA(): Pair<GA<V, F>, CellularGABuilder<V, F>> =
    CellularGAInstance<V, F>().let { it to it }

inline fun <V, F> cellularGA(builder: CellularGABuilder<V, F>.() -> Unit): GA<V, F> {
    val (ga, gaBuilder) = cellularGA<V, F>()
    gaBuilder.apply(builder)
    return ga
}
*/
