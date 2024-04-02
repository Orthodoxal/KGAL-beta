package genetic.ga.cellular.operators.selection

import genetic.ga.base_operators.selection.tournament.DEFAULT_TOURNAMENT_SIZE
import genetic.ga.base_operators.selection.tournament.selectionTournament
import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle

fun <V, F> SimpleClusterCellLifecycle<V, F>.selectionTournament(
    cellularGABuilder: CellularGABuilder<V, F>,
    tournamentSize: Int = DEFAULT_TOURNAMENT_SIZE,
) = selection(cellularGABuilder) { selectionTournament(neighbours, tournamentSize, random) }
