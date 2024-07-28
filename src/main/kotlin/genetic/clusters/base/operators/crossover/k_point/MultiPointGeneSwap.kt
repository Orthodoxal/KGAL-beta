package genetic.clusters.base.operators.crossover.k_point

import genetic.clusters.base.operators.crossover.one_point.swapStrategy

internal inline fun multiSwapStrategy(size: Int, crossIndices: IntArray, swapper: (swapIndex: Int) -> Unit) {
    if (crossIndices.size == 1) {
        swapStrategy(size, crossIndices[0], swapper)
        return
    }

    var evenSegmentsGenesToSwap = 0
    var oddSegmentsGenesToSwap = 0
    crossIndices.forEachIndexed { index, crossIndex ->
        val count = if (index == 0) crossIndex + 1 else crossIndex - crossIndices[index - 1]
        if (index % 2 == 0) {
            evenSegmentsGenesToSwap += count
        } else {
            oddSegmentsGenesToSwap += count
        }
    }

    if (crossIndices.size % 2 == 0) {
        evenSegmentsGenesToSwap += size - 1 - crossIndices.last()
    } else {
        oddSegmentsGenesToSwap += size - 1 - crossIndices.last()
    }

    var endCross = if (evenSegmentsGenesToSwap <= oddSegmentsGenesToSwap) 0 else 1
    while (endCross <= crossIndices.size) {
        val end = if (endCross != crossIndices.size) crossIndices[endCross] else size - 1
        var start = if (endCross - 1 < 0) -1 else crossIndices[endCross - 1]
        while (start < end) swapper(++start)
        endCross += 2
    }
}

internal fun <T> geneSwap(first: Array<T>, second: Array<T>, crossIndices: IntArray) =
    multiSwapStrategy(first.size, crossIndices) { swapIndex ->
        val temp = first[swapIndex]
        first[swapIndex] = second[swapIndex]
        second[swapIndex] = temp
    }

internal fun geneSwap(first: BooleanArray, second: BooleanArray, crossIndices: IntArray) =
    multiSwapStrategy(first.size, crossIndices) { swapIndex ->
        val temp = first[swapIndex]
        first[swapIndex] = second[swapIndex]
        second[swapIndex] = temp
    }

internal fun geneSwap(first: ByteArray, second: ByteArray, crossIndices: IntArray) =
    multiSwapStrategy(first.size, crossIndices) { swapIndex ->
        val temp = first[swapIndex]
        first[swapIndex] = second[swapIndex]
        second[swapIndex] = temp
    }

internal fun geneSwap(first: CharArray, second: CharArray, crossIndices: IntArray) =
    multiSwapStrategy(first.size, crossIndices) { swapIndex ->
        val temp = first[swapIndex]
        first[swapIndex] = second[swapIndex]
        second[swapIndex] = temp
    }

internal fun geneSwap(first: DoubleArray, second: DoubleArray, crossIndices: IntArray) =
    multiSwapStrategy(first.size, crossIndices) { swapIndex ->
        val temp = first[swapIndex]
        first[swapIndex] = second[swapIndex]
        second[swapIndex] = temp
    }

internal fun geneSwap(first: FloatArray, second: FloatArray, crossIndices: IntArray) =
    multiSwapStrategy(first.size, crossIndices) { swapIndex ->
        val temp = first[swapIndex]
        first[swapIndex] = second[swapIndex]
        second[swapIndex] = temp
    }

internal fun geneSwap(first: IntArray, second: IntArray, crossIndices: IntArray) =
    multiSwapStrategy(first.size, crossIndices) { swapIndex ->
        val temp = first[swapIndex]
        first[swapIndex] = second[swapIndex]
        second[swapIndex] = temp
    }

internal fun geneSwap(first: LongArray, second: LongArray, crossIndices: IntArray) =
    multiSwapStrategy(first.size, crossIndices) { swapIndex ->
        val temp = first[swapIndex]
        first[swapIndex] = second[swapIndex]
        second[swapIndex] = temp
    }

internal fun <T> geneSwap(first: MutableList<T>, second: MutableList<T>, crossIndices: IntArray) =
    multiSwapStrategy(first.size, crossIndices) { swapIndex ->
        val temp = first[swapIndex]
        first[swapIndex] = second[swapIndex]
        second[swapIndex] = temp
    }

internal fun geneSwap(first: ShortArray, second: ShortArray, crossIndices: IntArray) =
    multiSwapStrategy(first.size, crossIndices) { swapIndex ->
        val temp = first[swapIndex]
        first[swapIndex] = second[swapIndex]
        second[swapIndex] = temp
    }
