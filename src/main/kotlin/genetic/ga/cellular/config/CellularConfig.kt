package genetic.ga.cellular.config

import genetic.ga.cellular.lifecycle.CellularLifecycle
import genetic.ga.cellular.neighborhood.CellularNeighborhood
import genetic.ga.cellular.type.CellularType
import genetic.ga.core.config.ConfigGA

interface CellularConfig<V, F> : ConfigGA<V, F, CellularLifecycle<V, F>> {
    var elitism: Boolean
    var cellularType: CellularType
    var neighborhood: CellularNeighborhood
}