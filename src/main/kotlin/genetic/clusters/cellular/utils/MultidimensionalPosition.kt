package genetic.clusters.cellular.utils

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
