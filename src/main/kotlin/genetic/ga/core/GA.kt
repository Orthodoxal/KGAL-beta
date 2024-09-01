package genetic.ga.core

import genetic.ga.core.population.Population
import genetic.ga.core.state.GAState
import genetic.ga.core.state.StopPolicy
import genetic.stat.note.StatisticNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.job
import kotlinx.coroutines.runBlocking

interface GA<V, F> {
    val state: GAState

    val population: Population<V, F>
    val iteration: Int
    val maxIteration: Int

    suspend fun start()
    suspend fun resume()
    suspend fun restart(resetPopulation: Boolean = true)
    suspend fun stop(stopPolicy: StopPolicy = StopPolicy.Default)

    fun collectStat(collector: FlowCollector<StatisticNote<Any?>>): GA<V, F>
    fun statFlow(collector: suspend CoroutineScope.(stat: SharedFlow<StatisticNote<Any?>>) -> Unit): GA<V, F>
}

fun GA<*, *>.startBlocking() = runBlocking { start() }

inline fun <V, F> GA<V, F>.startBlocking(
    crossinline onFinish: GA<V, F>.() -> Unit,
) = runBlocking {
    start()
    coroutineContext.job.invokeOnCompletion { onFinish() }
}

val GA<*, *>.name get() = population.name
val GA<*, *>.currentSize get() = population.currentSize
val GA<*, *>.maxSize get() = population.maxSize
val GA<*, *>.factory get() = population.factory
