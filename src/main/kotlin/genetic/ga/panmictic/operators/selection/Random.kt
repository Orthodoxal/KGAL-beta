package genetic.ga.panmictic.operators.selection

import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.base_operators.selection.random.selectionRandom
import genetic.ga.panmictic.builder.PanmicticGABuilder

suspend fun <V, F> SimpleClusterLifecycle<V, F>.selectionRandom(
    panmicticGABuilder: PanmicticGABuilder<V, F>,
    onlySingleRun: Boolean = false,
) = selection(panmicticGABuilder, onlySingleRun) { source -> selectionRandom(source, random) }
