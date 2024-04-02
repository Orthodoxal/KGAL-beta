package genetic.ga.cellular.lifecycle

import genetic.ga.cellular.builder.CellularGABuilder

interface CellularGALifecycle<V, F> : CellularGABuilder<V, F>

internal class CellularGALifecycleInstance<V, F>(
    builder: CellularGABuilder<V, F>
) : CellularGALifecycle<V, F>, CellularGABuilder<V, F> by builder
