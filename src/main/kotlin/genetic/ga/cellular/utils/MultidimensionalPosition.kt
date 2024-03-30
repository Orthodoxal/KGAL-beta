package genetic.ga.cellular.utils

import kotlin.math.abs

fun positionByCoordinatesInNArray(
    coordinates: IntArray,
    dimensionalSizes: IntArray,
): Int {
    var pos = 0
    repeat(dimensionalSizes.size) { n ->
        var multi = 1
        repeat(n) { i -> multi *= dimensionalSizes[i] }
        pos += coordinates[n] * multi
    }
    return pos
}

fun coordinatesInNArrayByPosition(
    position: Int,
    dimensionalSizes: IntArray,
): IntArray = IntArray(dimensionalSizes.size) { n ->
    var multi = 1
    repeat(n) { i -> multi *= dimensionalSizes[i] }
    (position / multi) % dimensionalSizes[n]
}

fun neumannNeighboursIndices(radius: Int, dimensionalSizes: IntArray): Pair<IntArray, Array<IntArray>> {
    val dimenSize = dimensionalSizes.size
    val count = delannoyNumber(dimenSize, radius) - 1 // Remove Center
    val resultOneArray = IntArray(count)
    val resultNArray = Array(count) { IntArray(dimenSize) }
    var resultIndex = 0

    val coordinates = IntArray(dimenSize)
    coordinates[0] = -radius
    val rCoordinates = IntArray(dimenSize)
    rCoordinates[0] = radius
    val isZero = BooleanArray(dimenSize - 1)

    var dimenIndex = 0
    if (dimenSize - 1 == 0) {
        var r = -radius
        repeat(count) { neighbourIndex ->
            if (r == 0) r++
            resultOneArray[neighbourIndex] = r
            resultNArray[neighbourIndex] = intArrayOf(r)
            r++
        }
        return resultOneArray to resultNArray
    }

    while (coordinates[dimenIndex] <= rCoordinates[dimenIndex]) {
        val rCoordinateNew = rCoordinates[dimenIndex] - abs(coordinates[dimenIndex])
        if (dimenIndex == 0 || isZero[dimenIndex - 1]) {
            isZero[dimenIndex] = coordinates[dimenIndex] == 0
        }
        dimenIndex++
        coordinates[dimenIndex] = -rCoordinateNew
        rCoordinates[dimenIndex] = rCoordinateNew

        if (dimenIndex == dimenSize - 1) {
            while (coordinates[dimenIndex] <= rCoordinates[dimenIndex]) {
                if (isZero.all { it } && coordinates[dimenIndex] == 0) {
                    coordinates[dimenIndex] = coordinates[dimenIndex] + 1
                    continue
                }
                resultOneArray[resultIndex] = positionByCoordinatesInNArray(coordinates, dimensionalSizes)
                resultNArray[resultIndex] = coordinates.copyOf()
                resultIndex++
                println(coordinates.joinToString(", ") { it.toString() })
                coordinates[dimenIndex] = coordinates[dimenIndex] + 1
            }

            while (coordinates[dimenIndex] >= rCoordinates[dimenIndex]) {
                dimenIndex--
                if (dimenIndex == -1) return resultOneArray to resultNArray
            }

            coordinates[dimenIndex] = coordinates[dimenIndex] + 1
        }
    }
    return resultOneArray to resultNArray
}

// Пример использования
fun main() {
    /*val position = 62
    val radius = 2
    val dimensionalSizes = intArrayOf(5, 5, 5, 5, 5)

    val positionCoordinatesNArray = coordinatesInNArrayByPosition(position, dimensionalSizes)
    val neighboursCount = delannoyNumber(radius, dimensionalSizes.size) - 1
    val (neumannNeighboursIndicesOneArray, neumannNeighboursIndicesNArray) =
        neumannNeighboursIndices(radius, dimensionalSizes)

    println("\nЦентральная координата (ячейка): $position")
    println("Радиус: $radius")
    println("Размерности: ${dimensionalSizes.joinToString(", ") { it.toString() }}")

    println("\nКоличество соседей")
    println(neighboursCount)

    val neighbours = IntArray(neighboursCount) { neighbourIndex ->
        val neighbourCoordinatesNArray = IntArray(dimensionalSizes.size) { dimenIndex ->
            positionCoordinatesNArray[dimenIndex] + neumannNeighboursIndicesNArray[neighbourIndex][dimenIndex]
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
            position + neumannNeighboursIndicesOneArray[neighbourIndex]
        }
    }
    println("Координаты соседей для координаты $position: ")
    println(neighbours.joinToString(", ") { it.toString() })*/

    //val position = 62
    val radius = 3
    val dimensionalSizes = intArrayOf(10, 10, 10)
    val neighboursCount = delannoyNumber(radius, dimensionalSizes.size) - 1
    val (neumannNeighboursIndicesOneArray, neumannNeighboursIndicesNArray) =
        neumannNeighboursIndices(radius, dimensionalSizes)
    val size = dimensionalSizes.fold(1) { acc, i -> acc * i }

    repeat(size) { position ->
        val positionCoordinatesNArray = coordinatesInNArrayByPosition(position, dimensionalSizes)

        println("\nЦентральная координата (ячейка): $position")
        println("Радиус: $radius")
        println("Размерности: ${dimensionalSizes.joinToString(", ") { it.toString() }}")

        println("\nКоличество соседей")
        println(neighboursCount)

        val neighbours = IntArray(neighboursCount) { neighbourIndex ->
            val neighbourCoordinatesNArray = IntArray(dimensionalSizes.size) { dimenIndex ->
                positionCoordinatesNArray[dimenIndex] + neumannNeighboursIndicesNArray[neighbourIndex][dimenIndex]
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
                position + neumannNeighboursIndicesOneArray[neighbourIndex]
            }
        }
        println("Координаты соседей для координаты $position: ")
        println(neighbours.joinToString(", ") { it.toString() })
    }
}
