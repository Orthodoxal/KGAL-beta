package genetic.ga.core.lifecycle

import java.util.concurrent.atomic.AtomicInteger

interface MultiRunHelper {
    var maxIterationMultiRun: Int
    val currentIterationMultiRun: AtomicInteger
}

internal class MultiRunHelperInstance(
    override var maxIterationMultiRun: Int = 0,
    override val currentIterationMultiRun: AtomicInteger = AtomicInteger(),
) : MultiRunHelper
