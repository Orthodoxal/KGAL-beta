package genetic.ga.panmictic.operators.selection

import genetic.ga.core.operators.selection.random.selectionRandom
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

suspend fun <V, F> PanmicticLifecycle<V, F>.selRandom(
    parallelWorkersLimit: Int = parallelismConfig.count,
) = selection(parallelWorkersLimit) { source -> selectionRandom(source, random) }
