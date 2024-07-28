package genetic.clusters.base.lifecycle

import java.util.concurrent.atomic.AtomicInteger

interface MultiRunHelper {
    var maxIteration: Int
    val currentIteration: AtomicInteger
}

class MultiRunHelperInstance(
    override var maxIteration: Int = 0,
    override val currentIteration: AtomicInteger = AtomicInteger(),
) : MultiRunHelper
