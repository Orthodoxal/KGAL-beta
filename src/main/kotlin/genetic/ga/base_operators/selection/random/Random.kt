package genetic.ga.base_operators.selection.random

import genetic.chromosome.Chromosome
import kotlin.random.Random

fun <V, F> selectionRandom(source: Array<Chromosome<V, F>>, random: Random) = source.random(random)
