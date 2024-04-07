package genetic.clusters

import genetic.chromosome.Chromosome
import genetic.clusters.state.ClusterStopPolicy

interface Cluster<V, F> {
    val name: String?
    val population: Array<Chromosome<V, F>>
    val generation: Int
    val maxGeneration: Int
    val populationSize: Int

    suspend fun start(generationFrom: Int = 0)
    suspend fun resume()
    suspend fun restart()
    suspend fun stop(stopPolicy: ClusterStopPolicy = ClusterStopPolicy.Default)
}
