package genetic.clusters.cellular.neighborhood

import genetic.clusters.cellular.utils.coordinatesInNArrayByPosition
import genetic.clusters.cellular.utils.positionByCoordinatesInNArray

/**
 * Toroidal filter for indices
 * @return neighboursIndices
 */
fun toroidalShapeIndicesFilter(
    position: Int,
    dimensionalSizes: IntArray,
    indicesOneArray: IntArray,
    indicesNArray: Array<IntArray>,
): IntArray {
    val positionCoordinatesNArray = coordinatesInNArrayByPosition(position, dimensionalSizes)
    return IntArray(indicesOneArray.size) { neighbourIndex ->
        val neighbourCoordinatesNArray = IntArray(dimensionalSizes.size) { dimenIndex ->
            positionCoordinatesNArray[dimenIndex] + indicesNArray[neighbourIndex][dimenIndex]
        }

        var isNeedReEvaluate = false
        neighbourCoordinatesNArray.forEachIndexed { index, coordinate ->
            val dimenSize = dimensionalSizes[index]
            if (coordinate < 0) { // координата не отрицательна
                isNeedReEvaluate = true
                neighbourCoordinatesNArray[index] = dimenSize + coordinate
            } else if (coordinate >= dimenSize) { // координата не больше, чем граница размерности
                isNeedReEvaluate = true
                neighbourCoordinatesNArray[index] = coordinate % dimenSize
            }
        }

        if (isNeedReEvaluate) {
            positionByCoordinatesInNArray(neighbourCoordinatesNArray, dimensionalSizes)
        } else {
            position + indicesOneArray[neighbourIndex]
        }
    }
}
