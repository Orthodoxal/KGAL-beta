package genetic.utils

import genetic.clusters.ClusterBuilder
import kotlin.math.abs
import kotlin.random.Random

fun Double.equalsDelta(other: Double) = abs(this / other - 1) < 0.000001
infix fun Double.moreOrEquals(other: Double) = this > other || this.equalsDelta(other)
infix fun Double.lessOrEquals(other: Double) = this < other || this.equalsDelta(other)

fun randomByChance(chance: Double, random: Random) =
    chance lessOrEquals 0.0 || chance moreOrEquals 1.0 || chance > random.nextDouble(0.0, 1.0)

inline fun randomByChance(chance: Double, random: Random = Random, action: () -> Unit) =
    if (randomByChance(chance, random)) action() else Unit

inline fun <V, F> ClusterBuilder<V, F>.randomByChance(chance: Double, action: () -> Unit) =
    randomByChance(chance, random, action)

fun IntArray.inUntil(elem: Int, subSize: Int): Boolean {
    for (i in 0..<subSize) {
        if (get(i) == elem) return true
    }
    return false
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
