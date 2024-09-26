package genetic.utils

import genetic.chromosome.Chromosome

internal fun <V, F> fillArrayChromosomeBySubArray(
    array: Array<Chromosome<V, F>>,
    subArray: Array<Chromosome<V, F>>
) {
    repeat(array.size) { index ->
        if (index < subArray.size) {
            array[index] = subArray[index]
        } else {
            array[index] = subArray[index % subArray.size].clone()
        }
    }
}

inline fun <T> Array<out T>.forEach(start: Int, end: Int = this.size, action: (T) -> Unit) {
    var index = start
    while (index < end) {
        action(this[index])
        index++
    }
}

inline fun <T> Array<out T>.forEachIndexed(start: Int, end: Int = this.size, action: (index: Int, T) -> Unit) {
    var index = start
    while (index < end) {
        action(index, this[index])
        index++
    }
}

inline fun loop(start: Int, end: Int, action: (index: Int) -> Unit) {
    for (index in start..<end) action(index)
}

inline fun loop(start: Int, end: Int, step: Int, action: (index: Int) -> Unit) {
    for (index in start..<end step step) action(index)
}

inline fun IntArray.forEachReverseIndexed(action: (Int, index: Int) -> Unit) {
    var index = lastIndex
    while (index >= 0) {
        action(this[index], index)
        index--
    }
}

inline fun <T> Array<T>.forEachReverse(action: (T) -> Unit) {
    var index = lastIndex
    while (index >= 0) {
        action(this[index])
        index--
    }
}

inline fun <T> Array<T>.forEachReverseIndexed(action: (T, index: Int) -> Unit) {
    var index = lastIndex
    while (index >= 0) {
        action(this[index], index)
        index--
    }
}
