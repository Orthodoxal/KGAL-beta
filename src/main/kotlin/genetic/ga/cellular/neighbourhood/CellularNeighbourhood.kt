package genetic.ga.cellular.neighbourhood

import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.cellular.utils.coordinatesInNArrayByPosition

sealed interface CellularNeighbourhood {
    val radius: Int

    class VonNeumann(override val radius: Int) : CellularNeighbourhood
    class Moore(override val radius: Int) : CellularNeighbourhood
}

/*fun <V, F> CellularGABuilder<V, F>.getNeighboursIndices(position: Int): IntArray {
    val coordinates = coordinatesInNArrayByPosition(position, dimensions)

}*/
