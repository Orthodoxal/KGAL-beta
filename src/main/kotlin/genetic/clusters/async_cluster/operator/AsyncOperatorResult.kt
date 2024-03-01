package genetic.clusters.async_cluster.operator

import genetic.chromosome.Chromosome

data class AsyncOperatorResult<V, F>(
    val operatorId: Int,
    val result: Chromosome<V, F>,
)
