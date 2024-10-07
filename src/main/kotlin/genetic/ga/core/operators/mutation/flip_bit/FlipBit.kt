package genetic.ga.core.operators.mutation.flip_bit

import genetic.utils.randomByChance
import kotlin.random.Random

fun mutationFlipBit(value: BooleanArray, chance: Double, random: Random) =
    value.forEachIndexed { index, gene ->
        randomByChance(chance, random) { value[index] = !gene }
    }

fun mutationFlipBit(value: IntArray, chance: Double, random: Random) =
    value.forEachIndexed { index, gene ->
        randomByChance(chance, random) { value[index] = if (gene == 1) 0 else 1 }
    }
