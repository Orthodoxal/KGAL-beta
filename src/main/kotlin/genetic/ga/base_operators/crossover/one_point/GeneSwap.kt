package genetic.ga.base_operators.crossover.one_point

internal fun <T> geneSwap(first: Array<T>, second: Array<T>, crossIndex: Int) {
    val isSecondHalf = crossIndex > first.size / 2
    var start = if (isSecondHalf) crossIndex else 0
    val end = if (isSecondHalf) first.size else crossIndex
    while (start != end) {
        val temp = first[start]
        first[start] = second[start]
        second[start] = temp
        start++
    }
}

internal fun geneSwap(first: BooleanArray, second: BooleanArray, crossIndex: Int) {
    val isSecondHalf = crossIndex > first.size / 2
    var start = if (isSecondHalf) crossIndex else 0
    val end = if (isSecondHalf) first.size else crossIndex
    while (start != end) {
        val temp = first[start]
        first[start] = second[start]
        second[start] = temp
        start++
    }
}

internal fun geneSwap(first: ByteArray, second: ByteArray, crossIndex: Int) {
    val isSecondHalf = crossIndex > first.size / 2
    var start = if (isSecondHalf) crossIndex else 0
    val end = if (isSecondHalf) first.size else crossIndex
    while (start != end) {
        val temp = first[start]
        first[start] = second[start]
        second[start] = temp
        start++
    }
}

internal fun geneSwap(first: CharArray, second: CharArray, crossIndex: Int) {
    val isSecondHalf = crossIndex > first.size / 2
    var start = if (isSecondHalf) crossIndex else 0
    val end = if (isSecondHalf) first.size else crossIndex
    while (start != end) {
        val temp = first[start]
        first[start] = second[start]
        second[start] = temp
        start++
    }
}

internal fun geneSwap(first: DoubleArray, second: DoubleArray, crossIndex: Int) {
    val isSecondHalf = crossIndex > first.size / 2
    var start = if (isSecondHalf) crossIndex else 0
    val end = if (isSecondHalf) first.size else crossIndex
    while (start != end) {
        val temp = first[start]
        first[start] = second[start]
        second[start] = temp
        start++
    }
}

internal fun geneSwap(first: FloatArray, second: FloatArray, crossIndex: Int) {
    val isSecondHalf = crossIndex > first.size / 2
    var start = if (isSecondHalf) crossIndex else 0
    val end = if (isSecondHalf) first.size else crossIndex
    while (start != end) {
        val temp = first[start]
        first[start] = second[start]
        second[start] = temp
        start++
    }
}

internal fun geneSwap(first: IntArray, second: IntArray, crossIndex: Int) {
    val isSecondHalf = crossIndex > first.size / 2
    var start = if (isSecondHalf) crossIndex else 0
    val end = if (isSecondHalf) first.size else crossIndex
    while (start != end) {
        val temp = first[start]
        first[start] = second[start]
        second[start] = temp
        start++
    }
}

internal fun geneSwap(first: LongArray, second: LongArray, crossIndex: Int) {
    val isSecondHalf = crossIndex > first.size / 2
    var start = if (isSecondHalf) crossIndex else 0
    val end = if (isSecondHalf) first.size else crossIndex
    while (start != end) {
        val temp = first[start]
        first[start] = second[start]
        second[start] = temp
        start++
    }
}

internal fun <T> geneSwap(first: MutableList<T>, second: MutableList<T>, crossIndex: Int) {
    val isSecondHalf = crossIndex > first.size / 2
    var start = if (isSecondHalf) crossIndex else 0
    val end = if (isSecondHalf) first.size else crossIndex
    while (start != end) {
        val temp = first[start]
        first[start] = second[start]
        second[start] = temp
        start++
    }
}

internal fun geneSwap(first: ShortArray, second: ShortArray, crossIndex: Int) {
    val isSecondHalf = crossIndex > first.size / 2
    var start = if (isSecondHalf) crossIndex else 0
    val end = if (isSecondHalf) first.size else crossIndex
    while (start != end) {
        val temp = first[start]
        first[start] = second[start]
        second[start] = temp
        start++
    }
}
