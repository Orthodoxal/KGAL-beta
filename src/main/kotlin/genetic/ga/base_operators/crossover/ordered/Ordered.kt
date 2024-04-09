package genetic.ga.base_operators.crossover.ordered

import genetic.chromosome.Chromosome
import kotlin.random.Random

internal fun crossoverOrderedIntArray(
    chromosome1: Chromosome<IntArray, *>,
    chromosome2: Chromosome<IntArray, *>,
    random: Random,
): Pair<IntArray, IntArray> {
    val genes1 = chromosome1.value
    val genes2 = chromosome2.value
    val size = minOf(genes1.size, genes2.size)
    var a = random.nextInt(size)
    var b = random.nextInt(size - 1)
    if (a == b) b++
    if (b < a) {
        val temp = a
        a = b
        b = temp
    }

    val holes1 = BooleanArray(size) { true }
    val holes2 = BooleanArray(size) { true }

    for (i in 0..<size) {
        if (i < a || i > b) {
            holes1[genes2[i]] = false
            holes2[genes1[i]] = false
        }
    }

    val temp1 = genes1.copyOf()
    val temp2 = genes2.copyOf()
    var k1 = b + 1
    var k2 = b + 1

    for (i in 0..<size) {
        if (!holes1[temp1[(i + b + 1) % size]]) {
            genes1[k1 % size] = temp1[(i + b + 1) % size]
            k1++
        }

        if (!holes2[temp2[(i + b + 1) % size]]) {
            genes2[k2 % size] = temp2[(i + b + 1) % size]
            k2++
        }
    }

    for (i in a..b) {
        val temp = genes1[i]
        genes1[i] = genes2[i]
        genes2[i] = temp
    }

    return Pair(genes1, genes2)
}