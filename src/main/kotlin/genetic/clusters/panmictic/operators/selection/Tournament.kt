package genetic.clusters.panmictic.operators.selection

import genetic.clusters.base.operators.selection.tournament.DEFAULT_TOURNAMENT_SIZE
import genetic.clusters.base.operators.selection.tournament.selectionTournament
import genetic.clusters.panmictic.PanmicticLifecycle
import genetic.utils.clusters.popCurrentSize
import genetic.utils.clusters.popFactory

suspend fun <V, F> PanmicticLifecycle<V, F>.selTournament(
    tournamentSize: Int = DEFAULT_TOURNAMENT_SIZE,
    onlySingleRun: Boolean = false,
) = selection(onlySingleRun) { source -> selectionTournament(source, tournamentSize, random) }

suspend fun <V, F> PanmicticLifecycle<V, F>.selTournament(
    percent: Double,
    tournamentSize: Int = DEFAULT_TOURNAMENT_SIZE,
    onlySingleRun: Boolean = false,
) = selectionWithIndex(onlySingleRun) { index, source ->
    if (index < (popCurrentSize * percent).toInt()) {
        selectionTournament(source, tournamentSize, random)
    } else {
        popFactory(index, random)
    }
}
