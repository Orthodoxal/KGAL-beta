package genetic.clusters.cellular.operators.selection

import genetic.clusters.base.operators.selection.tournament.DEFAULT_TOURNAMENT_SIZE
import genetic.clusters.base.operators.selection.tournament.selectionTournament
import genetic.clusters.cellular.lifecycle.CellLifecycle

fun <V, F> CellLifecycle<V, F>.selTournament(
    tournamentSize: Int = DEFAULT_TOURNAMENT_SIZE,
) = selection { source -> selectionTournament(source, tournamentSize, random) }
