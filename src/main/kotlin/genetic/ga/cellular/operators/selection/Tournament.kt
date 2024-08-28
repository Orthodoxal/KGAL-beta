package genetic.ga.cellular.operators.selection

import genetic.ga.cellular.lifecycle.CellLifecycle
import genetic.ga.core.operators.selection.tournament.DEFAULT_TOURNAMENT_SIZE
import genetic.ga.core.operators.selection.tournament.selectionTournament

fun <V, F> CellLifecycle<V, F>.selTournament(
    tournamentSize: Int = DEFAULT_TOURNAMENT_SIZE,
) = selection { source -> selectionTournament(source, tournamentSize, random) }
