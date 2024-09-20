package genetic.ga.distributed.operators.children

import genetic.ga.core.GA
import genetic.ga.core.state.GAState
import genetic.ga.distributed.lifecycle.DistributedLifecycle
import genetic.ga.distributed.lifecycle.LifecycleStartOption
import kotlinx.coroutines.job
import kotlin.coroutines.coroutineContext

private val List<GA<*, *>>.anyFinishedByStopConditions
    get() = any { it.state is GAState.FINISHED.ByStopConditions }

suspend fun <V, F> DistributedLifecycle<V, F>.startChildren() {
    when (val option = startOption) {
        LifecycleStartOption.Start -> clusters.forEach { it.start() }
        LifecycleStartOption.Resume -> clusters.forEach { it.resume() }
        is LifecycleStartOption.Restart -> clusters.forEach { it.restart(option.resetPopulation) }
    }
    startOption = LifecycleStartOption.Start
    coroutineContext.job.children.forEach { it.join() }
    if (clusters.anyFinishedByStopConditions) {
        finishByStopConditions = true
    }
}
