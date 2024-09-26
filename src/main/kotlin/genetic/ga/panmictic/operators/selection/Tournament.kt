package genetic.ga.panmictic.operators.selection

import genetic.ga.core.lifecycle.factory
import genetic.ga.core.lifecycle.size
import genetic.ga.core.operators.selection.tournament.selectionTournament
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

suspend fun <V, F> PanmicticLifecycle<V, F>.selTournament(
    size: Int,
    parallelWorkersLimit: Int = parallelismConfig.count,
) = selection(parallelWorkersLimit) { source -> selectionTournament(source, size, random) }

suspend fun <V, F> PanmicticLifecycle<V, F>.selTournament(
    percent: Double,
    size: Int,
    parallelWorkersLimit: Int = parallelismConfig.count,
) {
    val limit = (this.size * percent).toInt()
    selectionWithIndex(parallelWorkersLimit) { index, source ->
        if (index < limit) {
            selectionTournament(source, size, random)
        } else {
            random.factory()
        }
    }
}
