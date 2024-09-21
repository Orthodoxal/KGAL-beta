package genetic.ga.core

import genetic.ga.core.population.Population
import genetic.ga.core.state.GAState
import genetic.ga.core.state.StopPolicy
import genetic.statistics.note.StatisticNote
import genetic.statistics.provider.STAT_COLLECTOR
import genetic.statistics.provider.STAT_FLOW_COLLECTOR
import genetic.statistics.provider.StatisticsProvider
import genetic.statistics.provider.StatisticsProvider.Companion.BASE_COLLECTOR_ID
import genetic.statistics.provider.StatisticsProvider.Companion.BASE_FLOW_COLLECTOR_ID
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.job
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

interface GA<V, F> {
    val state: GAState
    val population: Population<V, F>
    var fitnessFunction: (V) -> F

    val random: Random
    val iteration: Int

    val mainDispatcher: CoroutineDispatcher?
    val extraDispatchers: Array<CoroutineDispatcher>?

    val statisticsProvider: StatisticsProvider

    suspend fun start()
    suspend fun resume()
    suspend fun restart(resetPopulation: Boolean = true)
    suspend fun stop(stopPolicy: StopPolicy = StopPolicy.Default)
}

fun <V, F> GA<V, F>.collectStat(
    id: String = BASE_COLLECTOR_ID,
    collector: STAT_COLLECTOR,
): GA<V, F> = apply {
    statisticsProvider.collect(id, collector)
}

fun <V, F> GA<V, F>.flowStat(
    id: String = BASE_FLOW_COLLECTOR_ID,
    collector: STAT_FLOW_COLLECTOR,
): GA<V, F> = apply {
    statisticsProvider.flow(id, collector)
}

fun GA<*, *>.startBlocking() = runBlocking { start() }

inline fun <V, F> GA<V, F>.startBlocking(
    crossinline onFinish: GA<V, F>.() -> Unit,
) = runBlocking {
    start()
    coroutineContext.job.invokeOnCompletion { onFinish() }
}

val GA<*, *>.name get() = population.name
val GA<*, *>.currentSize get() = population.size
val GA<*, *>.maxSize get() = population.maxSize
val GA<*, *>.factory get() = population.factory
