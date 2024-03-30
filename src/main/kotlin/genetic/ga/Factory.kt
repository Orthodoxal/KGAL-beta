package genetic.ga

import genetic.clusters.simpleCluster
import genetic.clusters.simple_cluster.builder.SimpleClusterBuilder
import genetic.ga.panmictic.PanmicticGAInstance
import genetic.ga.panmictic.builder.PanmicticGABuilder

fun <V, F> panmicticGA(): Pair<GA<V, F>, PanmicticGABuilder<V, F>> =
    PanmicticGAInstance<V, F>().let { it to it }

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
