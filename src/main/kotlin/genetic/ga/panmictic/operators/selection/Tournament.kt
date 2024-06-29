package genetic.ga.panmictic.operators.selection

import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.base_operators.selection.tournament.DEFAULT_TOURNAMENT_SIZE
import genetic.ga.base_operators.selection.tournament.selectionTournament
import genetic.ga.panmictic.builder.PanmicticGABuilder

suspend fun <V, F> SimpleClusterLifecycle<V, F>.selectionTournament(
    panmicticGABuilder: PanmicticGABuilder<V, F>,
    tournamentSize: Int = DEFAULT_TOURNAMENT_SIZE,
    elitism: Int = 0,
    onlySingleRun: Boolean = false,
) = selection(panmicticGABuilder, elitism, onlySingleRun) { source -> selectionTournament(source, tournamentSize, random) }

suspend fun <V, F> SimpleClusterLifecycle<V, F>.selectionTournament(
    panmicticGABuilder: PanmicticGABuilder<V, F>,
    percent: Double,
    tournamentSize: Int = DEFAULT_TOURNAMENT_SIZE,
    elitism: Int = 0,
    onlySingleRun: Boolean = false,
) = selectionWithIndex(panmicticGABuilder, elitism, onlySingleRun) { index, source ->
    if (index < (populationSize * percent).toInt()) {
        selectionTournament(source, tournamentSize, random)
    } else {
        populationFactory(index, random)
    }
}
