package genetic.ga.cellular.operators.selection

import genetic.ga.cellular.lifecycle.CellLifecycle
import genetic.ga.core.operators.selection.tournament.selectionTournament

fun <V, F> CellLifecycle<V, F>.selTournament(
    tournamentSize: Int,
) = selection { source -> selectionTournament(source, tournamentSize, random) }
