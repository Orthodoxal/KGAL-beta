package genetic.ga.core.operators.selection.tournament

import genetic.chromosome.Chromosome
import genetic.utils.random
import kotlin.random.Random

const val DEFAULT_TOURNAMENT_SIZE = 3

fun <V, F> selectionTournament(
    source: Array<Chromosome<V, F>>,
    tournamentSize: Int,
    random: Random,
): Chromosome<V, F> {
    val tournament = source.random(tournamentSize, random)
    return tournament.max().clone()
}
