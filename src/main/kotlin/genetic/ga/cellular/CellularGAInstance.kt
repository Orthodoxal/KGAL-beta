package genetic.ga.cellular

import genetic.ga.cellular.config.CellularConfig
import genetic.ga.cellular.lifecycle.CellularLifecycle
import genetic.ga.cellular.lifecycle.CellularLifecycleInstance
import genetic.ga.cellular.neighborhood.CellularNeighborhood
import genetic.ga.cellular.operators.cache.IS_CACHE_NEIGHBORHOOD_ACTUAL
import genetic.ga.cellular.population.CellularPopulation
import genetic.ga.cellular.type.CellularType
import genetic.ga.core.AbstractGA

internal class CellularGAInstance<V, F>(
    configuration: CellularConfig<V, F>,
    override val population: CellularPopulation<V, F>,
) : CellularGA<V, F>, AbstractGA<V, F, CellularLifecycle<V, F>>(configuration) {
    override val lifecycle: CellularLifecycle<V, F> by lazy {
        CellularLifecycleInstance(this, configuration.parallelismConfig)
    }

    override var elitism: Boolean = configuration.elitism
    override var cellularType: CellularType = configuration.cellularType
    override var neighborhood: CellularNeighborhood = configuration.neighborhood
        set(value) {
            if (field != value) lifecycle.store[IS_CACHE_NEIGHBORHOOD_ACTUAL] = false
            field = value
        }
}
