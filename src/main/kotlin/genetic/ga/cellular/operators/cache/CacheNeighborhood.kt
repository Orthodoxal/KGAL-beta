package genetic.ga.cellular.operators.cache

import genetic.ga.cellular.lifecycle.CellularLifecycle
import genetic.ga.cellular.neighborhood.toroidalShapeIndicesFilter

internal const val IS_CACHE_NEIGHBORHOOD_ACTUAL = "IS_CACHE_NEIGHBORHOOD_ACTUAL"

private val CellularLifecycle<*, *>.isCacheNeighborhoodActual
    get() = store[IS_CACHE_NEIGHBORHOOD_ACTUAL] as? Boolean ?: false

fun CellularLifecycle<*, *>.cacheNeighborhood() {
    if (!isCacheNeighborhoodActual) {
        val (indicesOneArray, indicesNArray) = neighborhood.neighboursIndicesMatrix(dimens)
        neighboursIndicesCache = Array(dimens.size) { position ->
            toroidalShapeIndicesFilter(position, dimens, indicesOneArray, indicesNArray)
        }
        store[IS_CACHE_NEIGHBORHOOD_ACTUAL] = true
    }
}
