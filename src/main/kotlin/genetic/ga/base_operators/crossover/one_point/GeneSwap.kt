package genetic.ga.base_operators.crossover.one_point

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
