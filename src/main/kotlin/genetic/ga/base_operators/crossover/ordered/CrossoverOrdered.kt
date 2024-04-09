package genetic.ga.base_operators.crossover.ordered

import kotlin.random.Random

internal fun crossoverOrderedIntArray(ind1: IntArray, ind2: IntArray, random: Random): Pair<IntArray, IntArray> {
    val size = minOf(ind1.size, ind2.size)
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
            holes1[ind2[i]] = false
            holes2[ind1[i]] = false
        }
    }

    val temp1 = ind1.copyOf()
    val temp2 = ind2.copyOf()
    var k1 = b + 1
    var k2 = b + 1

    for (i in 0..<size) {
        if (!holes1[temp1[(i + b + 1) % size]]) {
            ind1[k1 % size] = temp1[(i + b + 1) % size]
            k1++
        }

        if (!holes2[temp2[(i + b + 1) % size]]) {
            ind2[k2 % size] = temp2[(i + b + 1) % size]
            k2++
        }
    }

    for (i in a..b) {
        val temp = ind1[i]
        ind1[i] = ind2[i]
        ind2[i] = temp
    }

    return Pair(ind1, ind2)
}