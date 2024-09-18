package genetic.ga.panmictic.operators.selection

import genetic.ga.core.lifecycle.factory
import genetic.ga.core.lifecycle.size
import genetic.ga.core.operators.selection.tournament.DEFAULT_TOURNAMENT_SIZE
import genetic.ga.core.operators.selection.tournament.selectionTournament
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

suspend fun <V, F> PanmicticLifecycle<V, F>.selTournament(
    size: Int = DEFAULT_TOURNAMENT_SIZE,
    onlySingleRun: Boolean = false,
) = selection(onlySingleRun) { source -> selectionTournament(source, size, random) }

suspend fun <V, F> PanmicticLifecycle<V, F>.selTournament(
    percent: Double,
    size: Int = DEFAULT_TOURNAMENT_SIZE,
    onlySingleRun: Boolean = false,
) = selectionWithIndex(onlySingleRun) { index, source ->
    if (index < (this.size * percent).toInt()) {
        selectionTournament(source, size, random)
    } else {
        random.factory()
    }
}
