package genetic.ga.distributed.operators.children

import genetic.ga.distributed.lifecycle.DistributedLifecycle
import genetic.ga.distributed.lifecycle.LifecycleStartOption
import kotlinx.coroutines.job
import kotlin.coroutines.coroutineContext

suspend fun <V, F> DistributedLifecycle<V, F>.startChildren() {
    when (val option = lifecycleStartOption) {
        LifecycleStartOption.Start -> gas.forEach { it.start() }
        LifecycleStartOption.Resume -> gas.forEach { it.resume() }
        is LifecycleStartOption.Restart -> gas.forEach { it.restart(option.resetPopulation) }
    }
    lifecycleStartOption = LifecycleStartOption.Start
    coroutineContext.job.children.forEach { it.join() }
}
