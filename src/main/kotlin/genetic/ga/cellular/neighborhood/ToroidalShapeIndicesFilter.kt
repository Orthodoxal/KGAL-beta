package genetic.ga.cellular.neighborhood

import genetic.ga.cellular.utils.Dimens
import genetic.ga.cellular.utils.coordinatesInNArrayByPosition
import genetic.ga.cellular.utils.positionByCoordinatesInNArray

/**
 * Toroidal filter for indices
 * @return neighboursIndices
 */
fun toroidalShapeIndicesFilter(
    position: Int,
    dimens: Dimens,
    indicesOneArray: IntArray,
    indicesNArray: Array<IntArray>,
): IntArray {
    val positionCoordinatesNArray = coordinatesInNArrayByPosition(position, dimens)
    return IntArray(indicesOneArray.size) { neighbourIndex ->
        val neighbourCoordinatesNArray = IntArray(dimens.size) { dimenIndex ->
            positionCoordinatesNArray[dimenIndex] + indicesNArray[neighbourIndex][dimenIndex]
        }

        var isNeedReEvaluate = false
        neighbourCoordinatesNArray.forEachIndexed { index, coordinate ->
            val dimenSize = dimens.value[index]
            if (coordinate < 0) { // координата не отрицательна
                isNeedReEvaluate = true
                neighbourCoordinatesNArray[index] = dimenSize + coordinate
            } else if (coordinate >= dimenSize) { // координата не больше, чем граница размерности
                isNeedReEvaluate = true
                neighbourCoordinatesNArray[index] = coordinate % dimenSize
            }
        }

        if (isNeedReEvaluate) {
            positionByCoordinatesInNArray(neighbourCoordinatesNArray, dimens)
        } else {
            position + indicesOneArray[neighbourIndex]
        }
    }
}
