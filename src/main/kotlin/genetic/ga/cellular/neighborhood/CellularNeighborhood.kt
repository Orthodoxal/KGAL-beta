package genetic.ga.cellular.neighborhood

import genetic.ga.cellular.utils.Dimens

interface CellularNeighborhood {
    val radius: Int
    fun neighboursCount(dimenCount: Int): Int
    fun neighboursIndicesMatrix(dimens: Dimens): Pair<IntArray, Array<IntArray>>
}
