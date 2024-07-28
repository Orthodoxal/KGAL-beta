package genetic.clusters.panmictic.operators.selection

import genetic.clusters.base.operators.selection.random.selectionRandom
import genetic.clusters.panmictic.PanmicticLifecycle

suspend fun <V, F> PanmicticLifecycle<V, F>.selRandom(
    onlySingleRun: Boolean = false,
) = selection(onlySingleRun) { source -> selectionRandom(source, random) }
