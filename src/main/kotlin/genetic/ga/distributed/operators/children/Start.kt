package genetic.ga.distributed.operators.children

import genetic.ga.core.GA
import genetic.ga.core.processor.process
import genetic.ga.core.state.GAState
import genetic.ga.distributed.lifecycle.DistributedLifecycle
import genetic.ga.distributed.lifecycle.LifecycleStartOption

private val List<GA<*, *>>.anyFinishedByStopConditions
    get() = any { it.state is GAState.FINISHED.ByStopConditions }

suspend fun <V, F> DistributedLifecycle<V, F>.startChildren(
    parallelismLimit: Int,
) {
    process(
        parallelismLimit = parallelismLimit,
        startIteration = 0,
        endIteration = clusters.size,
        action = { iteration, _ ->
            when (val startOption = startOption) {
                LifecycleStartOption.Start -> clusters[iteration].start()
                LifecycleStartOption.Resume -> clusters[iteration].resume()
                is LifecycleStartOption.Restart -> clusters[iteration].restart(startOption.resetPopulation)
            }
        },
    )
    startOption = LifecycleStartOption.Start
    if (clusters.anyFinishedByStopConditions) {
        finishByStopConditions = true
    }
}
