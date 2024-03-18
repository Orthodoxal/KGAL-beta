package genetic.ga.panmictic.operators.selection

import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.base_operators.selection.tournament.selectionTournament
import genetic.ga.panmictic.builder.PanmicticGABuilder

private const val DEFAULT_TOURNAMENT_SIZE = 3

fun <V, F> SimpleClusterLifecycle<V, F>.selectionTournament(
    panmicticGABuilder: PanmicticGABuilder<V, F>,
    tournamentSize: Int = DEFAULT_TOURNAMENT_SIZE,
) = selection(panmicticGABuilder) { source -> selectionTournament(source, tournamentSize) }
