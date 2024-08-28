package genetic.utils

import genetic.ga.core.lifecycle.GALifecycle
import kotlin.math.abs
import kotlin.math.ln
import kotlin.math.sqrt
import kotlin.random.Random

fun Double.equalsDelta(other: Double) = abs(this / other - 1) < 0.000001
infix fun Double.moreOrEquals(other: Double) = this > other || this.equalsDelta(other)
infix fun Double.lessOrEquals(other: Double) = this < other || this.equalsDelta(other)

fun randomByChance(chance: Double, random: Random) =
    chance lessOrEquals 0.0 || chance moreOrEquals 1.0 || chance > random.nextDouble(0.0, 1.0)

inline fun randomByChance(chance: Double, random: Random, action: () -> Unit) =
    if (randomByChance(chance, random)) action() else Unit

inline fun GALifecycle<*, *>.randomByChance(chance: Double, action: () -> Unit) =
    randomByChance(chance, random, action)

fun IntArray.inUntil(elem: Int, subSize: Int): Boolean {
    for (i in 0..<subSize) {
        if (get(i) == elem) return true
    }
    return false
}

fun IntRange.indicesByRandom(count: Int, random: Random): IntArray {
    if (count > last - first) throw IllegalStateException("Count cannot be more than size")
    val randomIndices = IntArray(count)
    repeat(count) { counter ->
        var index = random.nextInt(first, last)
        while (randomIndices.inUntil(index, counter)) {
            index = ++index % last
        }
        randomIndices[counter] = index
    }
    return randomIndices
}

fun Array<*>.indicesByRandom(count: Int, random: Random): IntArray {
    if (count > size) throw IllegalStateException("Count cannot be more than size")

    return if (size > 5000 && count > size / 4) {
        val newAr = IntArray(size) { it }
        newAr.shuffle(random)
        newAr.copyOf(count)
    } else {
        val randomIndices = IntArray(count)
        repeat(count) { counter ->
            var index = random.nextInt(0, size)
            while (randomIndices.inUntil(index, counter)) {
                index = ++index % size
            }
            randomIndices[counter] = index
        }
        randomIndices
    }
}

inline fun <reified T> Array<out T>.random(count: Int, random: Random): Array<T> {
    val indices = indicesByRandom(count, random)
    return Array(count) { get(indices[it]) }
}

inline fun <reified T> Array<out T>.randomWithIndices(count: Int, random: Random): Pair<Array<T>, IntArray> {
    val indices = indicesByRandom(count, random)
    return Array(count) { get(indices[it]) } to indices
}

fun Random.nextGaussian(mean: Double, stddev: Double): Double {
    var v1: Double
    var v2: Double
    var s: Double
    do {
        v1 = 2 * nextDouble() - 1
        v2 = 2 * nextDouble() - 1
        s = v1 * v1 + v2 * v2
    } while (s >= 1 || s.equalsDelta(0.0))
    val multiplier = sqrt(-2 * ln(s) / s)
    return mean + stddev * (v1 * multiplier)
}
