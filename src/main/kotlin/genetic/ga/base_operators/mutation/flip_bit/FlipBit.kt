package genetic.ga.base_operators.mutation.flip_bit

import genetic.chromosome.Chromosome
import genetic.utils.randomByChance
import kotlin.random.Random

internal fun <F> mutationFlipBitBooleanArray(
    chromosome: Chromosome<BooleanArray, F>,
    chance: Double,
    random: Random,
) = chromosome.value.forEachIndexed { index, gene ->
    randomByChance(chance, random) { chromosome.value[index] = !gene }
}

internal fun <F> mutationFlipBitIntArray(
    chromosome: Chromosome<IntArray, F>,
    chance: Double,
    random: Random,
) = chromosome.value.forEachIndexed { index, gene ->
    randomByChance(chance, random) { chromosome.value[index] = if (gene == 1) 0 else 1 }
}
