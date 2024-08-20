package genetic.clusters.base

import genetic.clusters.base.population.Population
import genetic.clusters.base.state.ClusterState
import genetic.clusters.base.state.StopPolicy
import genetic.stat.Statistics

interface Cluster<V, F> {
    val name: String?
    val state: ClusterState

    val population: Population<V, F>
    val generation: Int
    val maxGeneration: Int

    val stat: Statistics?

    suspend fun start(generationFrom: Int = 0)
    suspend fun resume()
    suspend fun restart()
    suspend fun stop(stopPolicy: StopPolicy = StopPolicy.Default)
}
