package genetic.ga

import genetic.clusters.Cluster
import genetic.clusters.state.ClusterStopPolicy
import genetic.ga.lifecycle.LifecycleStartOption
import genetic.ga.state.GAState
import genetic.ga.state.gaStateMachine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.cancellation.CancellationException

abstract class AbstractGA<V, F> : GA<V, F> {
    protected var stopSignal: Boolean = false
    protected var state: GAState = GAState.INITIALIZE
        set(value) {
            field = gaStateMachine(field, value)
        }
    override var clusters: MutableList<Cluster<V, F>> = mutableListOf()
    private var gaJob: Job? = null
    override var iteration: Int = 0
    override var maxIteration: Int = 1

    protected abstract fun CoroutineScope.startByOption(
        startOption: LifecycleStartOption,
        iterationFrom: Int,
        coroutineContext: CoroutineContext,
    )

    override fun CoroutineScope.start(iterationFrom: Int, coroutineContext: CoroutineContext) {
        if (state == GAState.STARTED) return
        startByOption(LifecycleStartOption.START, iterationFrom, coroutineContext)
    }


    override fun CoroutineScope.resume(coroutineContext: CoroutineContext) {
        if (state != GAState.STOPPED) throw IllegalStateException("GA state $state incorrect for resuming: State must be STOPPED")

        startByOption(LifecycleStartOption.RESUME, iteration, coroutineContext)
    }

    override fun CoroutineScope.stop(stopPolicy: ClusterStopPolicy, coroutineContext: CoroutineContext) {
        if (stopSignal) return
        stopSignal = true

        clusters.forEach {
            launch(coroutineContext) { it.stop(stopPolicy) }
        }
    }

    override fun CoroutineScope.restart(iterationFrom: Int, coroutineContext: CoroutineContext) {
        try {
            gaJob?.cancel()
        } catch (_: CancellationException) {
            // nothing to do
        }

        startByOption(LifecycleStartOption.RESTART, iterationFrom, coroutineContext)
    }

    protected fun CoroutineScope.launchGA(
        iterationFrom: Int,
        coroutineContext: CoroutineContext,
        startGA: suspend () -> Unit,
    ) {
        iteration = iterationFrom
        state = GAState.STARTED
        gaJob = launch(coroutineContext) {
            startGA()
            state = GAState.FINISHED
        }
    }

    protected suspend inline fun <L> baseStartGA(
        lifecycleScope: L,
        beforeLifecycle: suspend L.() -> Unit,
        lifecycle: suspend L.() -> Unit,
        afterLifecycle: suspend L.() -> Unit,
    ) {
        with(lifecycleScope) {
            if (iteration == 0) {
                beforeLifecycle()
            }
            while (iteration < maxIteration) {
                lifecycle()
                if (clusters.all { it.generation == it.maxGeneration }) {
                    iteration++
                }
                if (stopSignal) {
                    state = GAState.STOPPED
                    stopSignal = false
                    return
                }
            }
            if (iteration == maxIteration) {
                afterLifecycle()
            }
        }
    }
}
