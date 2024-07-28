package genetic.clusters.cellular.neighborhood

interface CellularNeighborhood {
    val radius: Int
    fun neighboursCount(dimenCount: Int): Int
    fun neighboursIndicesMatrix(dimensionalSizes: IntArray): Pair<IntArray, Array<IntArray>>
}
