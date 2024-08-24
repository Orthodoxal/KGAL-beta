package genetic.clusters.base

import genetic.clusters.base.population.Population
import genetic.clusters.base.state.ClusterState
import genetic.clusters.base.state.StopPolicy
import genetic.stat.StatisticNote
import genetic.stat.Statistics
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.runBlocking

interface Cluster<V, F> {
    val name: String?
    val state: ClusterState

    val population: Population<V, F>
    val iteration: Int
    val maxIteration: Int

    val stat: Statistics?

    suspend fun start()
    suspend fun resume()
    suspend fun restart(resetPopulation: Boolean = true)
    suspend fun stop(stopPolicy: StopPolicy = StopPolicy.Default)

    fun collectStat(collector: FlowCollector<StatisticNote>): Cluster<V, F>
    fun statFlow(collector: suspend CoroutineScope.(stat: Statistics) -> Unit): Cluster<V, F>
}

fun Cluster<*, *>.startBlocking() = runBlocking { start() }
