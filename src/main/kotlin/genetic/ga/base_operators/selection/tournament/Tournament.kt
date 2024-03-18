package genetic.ga.base_operators.selection.tournament

import genetic.chromosome.Chromosome
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.utils.random

private const val DEFAULT_TOURNAMENT_SIZE = 3

internal fun <V, F> SimpleClusterLifecycle<V, F>.selectionTournament(
    source: Array<Chromosome<V, F>>,
    tournamentSize: Int = DEFAULT_TOURNAMENT_SIZE,
): Chromosome<V, F> {
    val tournament = source.random(tournamentSize, random)
    return tournament.max().clone()
}
