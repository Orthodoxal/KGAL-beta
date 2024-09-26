package genetic.ga.core.parallelism.helper

import java.util.concurrent.atomic.AtomicInteger

internal class ParallelismIterativeHelperInstance(
    override val currentIteration: AtomicInteger,
    override var endIteration: Int,
) : ParallelismIterativeHelper {
    override fun set(startIteration: Int, endIteration: Int) {
        this.currentIteration.set(startIteration)
        this.endIteration = endIteration
    }
}
