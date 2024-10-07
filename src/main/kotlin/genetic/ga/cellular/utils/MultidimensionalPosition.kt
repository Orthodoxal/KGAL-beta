package genetic.ga.cellular.utils

fun positionByCoordinatesInNArray(
    coordinates: IntArray,
    dimens: Dimens,
): Int {
    var pos = 0
    repeat(dimens.size) { n ->
        var multi = 1
        repeat(n) { i -> multi *= dimens.value[i] }
        pos += coordinates[n] * multi
    }
    return pos
}

fun coordinatesInNArrayByPosition(
    position: Int,
    dimens: Dimens,
): IntArray = IntArray(dimens.size) { n ->
    var multi = 1
    repeat(n) { i -> multi *= dimens.value[i] }
    (position / multi) % dimens.value[n]
}
