package genetic.clusters.distributed.operators.children

import genetic.clusters.distributed.lifecycle.DistributedLifecycle
import genetic.clusters.distributed.lifecycle.LifecycleStartOption
import kotlinx.coroutines.job
import kotlin.coroutines.coroutineContext

suspend fun <V, F> DistributedLifecycle<V, F>.startChildren() {
    when (val option = lifecycleStartOption) {
        LifecycleStartOption.Start -> clusters.forEach { it.start() }
        LifecycleStartOption.Resume -> clusters.forEach { it.resume() }
        is LifecycleStartOption.Restart -> clusters.forEach { it.restart(option.resetPopulation) }
    }
    lifecycleStartOption = LifecycleStartOption.Start
    coroutineContext.job.children.forEach { it.join() }
}
