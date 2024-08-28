package genetic.ga.cellular.neighborhood.moore

import genetic.ga.cellular.neighborhood.CellularNeighborhood
import genetic.ga.cellular.utils.positionByCoordinatesInNArray

data class Moore(override val radius: Int) : CellularNeighborhood {

    override fun neighboursCount(dimenCount: Int): Int {
        var result = radius
        repeat(dimenCount - 1) { result *= radius }
        return result
    }

    override fun neighboursIndicesMatrix(dimensionalSizes: IntArray): Pair<IntArray, Array<IntArray>> {
        val dimenCount = dimensionalSizes.size
        val radDimen = radius * 2 + 1
        var neighboursCount = radDimen
        repeat(dimenCount - 1) { neighboursCount *= radDimen }
        neighboursCount--

        val coordinates = IntArray(dimensionalSizes.size) { -radius }
        val resultOneArray = IntArray(neighboursCount)
        val resultNArray = Array(neighboursCount) { IntArray(dimenCount) }
        var resultIndex = 0

        var dimenIndex = 0
        while (coordinates[dimenIndex] <= radius) {
            if (coordinates.all { it == 0 }) {
                coordinates[dimenIndex] = coordinates[dimenIndex] + 1
                continue
            }
            resultOneArray[resultIndex] = positionByCoordinatesInNArray(coordinates, dimensionalSizes)
            resultNArray[resultIndex] = coordinates.copyOf()
            resultIndex++
            coordinates[dimenIndex] = coordinates[dimenIndex] + 1

            if (resultIndex == neighboursCount) return resultOneArray to resultNArray

            while (coordinates[dimenIndex] > radius) {
                coordinates[dimenIndex] = -radius
                dimenIndex++
                coordinates[dimenIndex] = coordinates[dimenIndex] + 1
            }
            dimenIndex = 0
        }
        return resultOneArray to resultNArray
    }
}
