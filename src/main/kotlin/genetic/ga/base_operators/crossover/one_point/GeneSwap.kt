package genetic.ga.base_operators.crossover.one_point

private inline fun swapStrategy(size: Int, crossIndex: Int, swapper: (swapIndex: Int) -> Unit) {
    val isSecondHalf = crossIndex > size / 2
    var start = if (isSecondHalf) crossIndex else 0
    val end = if (isSecondHalf) size else crossIndex
    while (start != end) {
        swapper(start)
        start++
    }
}

internal fun <T> geneSwap(first: Array<T>, second: Array<T>, crossIndex: Int) =
    swapStrategy(first.size, crossIndex) { swapIndex ->
        val temp = first[swapIndex]
        first[swapIndex] = second[swapIndex]
        second[swapIndex] = temp
    }

internal fun geneSwap(first: BooleanArray, second: BooleanArray, crossIndex: Int) =
    swapStrategy(first.size, crossIndex) { swapIndex ->
        val temp = first[swapIndex]
        first[swapIndex] = second[swapIndex]
        second[swapIndex] = temp
    }

internal fun geneSwap(first: ByteArray, second: ByteArray, crossIndex: Int) =
    swapStrategy(first.size, crossIndex) { swapIndex ->
        val temp = first[swapIndex]
        first[swapIndex] = second[swapIndex]
        second[swapIndex] = temp
    }

internal fun geneSwap(first: CharArray, second: CharArray, crossIndex: Int) =
    swapStrategy(first.size, crossIndex) { swapIndex ->
        val temp = first[swapIndex]
        first[swapIndex] = second[swapIndex]
        second[swapIndex] = temp
    }

internal fun geneSwap(first: DoubleArray, second: DoubleArray, crossIndex: Int) =
    swapStrategy(first.size, crossIndex) { swapIndex ->
        val temp = first[swapIndex]
        first[swapIndex] = second[swapIndex]
        second[swapIndex] = temp
    }

internal fun geneSwap(first: FloatArray, second: FloatArray, crossIndex: Int) =
    swapStrategy(first.size, crossIndex) { swapIndex ->
        val temp = first[swapIndex]
        first[swapIndex] = second[swapIndex]
        second[swapIndex] = temp
    }

internal fun geneSwap(first: IntArray, second: IntArray, crossIndex: Int) =
    swapStrategy(first.size, crossIndex) { swapIndex ->
        val temp = first[swapIndex]
        first[swapIndex] = second[swapIndex]
        second[swapIndex] = temp
    }

internal fun geneSwap(first: LongArray, second: LongArray, crossIndex: Int) =
    swapStrategy(first.size, crossIndex) { swapIndex ->
        val temp = first[swapIndex]
        first[swapIndex] = second[swapIndex]
        second[swapIndex] = temp
    }

internal fun <T> geneSwap(first: MutableList<T>, second: MutableList<T>, crossIndex: Int) =
    swapStrategy(first.size, crossIndex) { swapIndex ->
        val temp = first[swapIndex]
        first[swapIndex] = second[swapIndex]
        second[swapIndex] = temp
    }

internal fun geneSwap(first: ShortArray, second: ShortArray, crossIndex: Int) =
    swapStrategy(first.size, crossIndex) { swapIndex ->
        val temp = first[swapIndex]
        first[swapIndex] = second[swapIndex]
        second[swapIndex] = temp
    }
