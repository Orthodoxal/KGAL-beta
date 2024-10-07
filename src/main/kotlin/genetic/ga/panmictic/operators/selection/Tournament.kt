package genetic.ga.panmictic.operators.selection

import genetic.ga.core.lifecycle.factory
import genetic.ga.core.lifecycle.size
import genetic.ga.core.operators.selection.tournament.selectionTournament
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

suspend fun <V, F> PanmicticLifecycle<V, F>.selTournament(
    size: Int,
    parallelismLimit: Int = parallelismConfig.workersCount,
) = selection(parallelismLimit) { source, random -> selectionTournament(source, size, random) }

suspend fun <V, F> PanmicticLifecycle<V, F>.selTournament(
    percent: Double,
    size: Int,
    parallelismLimit: Int = parallelismConfig.workersCount,
) {
    val limit = (this.size * percent).toInt()
    selectionWithIndex(parallelismLimit) { index, source, random ->
        if (index < limit) {
            selectionTournament(source, size, random)
        } else {
            random.factory()
        }
    }
}
