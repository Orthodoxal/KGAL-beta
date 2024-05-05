package genetic.ga.cellular.lifecycle

import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.lifecycle.GALifecycle
import genetic.ga.lifecycle.LifecycleStartOption

interface CellularGALifecycle<V, F> : CellularGABuilder<V, F>, GALifecycle<V, F>

internal class CellularGALifecycleInstance<V, F>(
    builder: CellularGABuilder<V, F>,
    override var lifecycleStartOption: LifecycleStartOption,
    override var stopSignal: Boolean = false,
) : CellularGALifecycle<V, F>, CellularGABuilder<V, F> by builder
