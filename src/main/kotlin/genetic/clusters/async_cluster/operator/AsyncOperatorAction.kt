package genetic.clusters.async_cluster.operator

import genetic.chromosome.Chromosome

sealed interface AsyncOperatorAction<V, F>

data class STSAction<V, F>(
    val action: (chromosome: Chromosome<V, F>) -> Chromosome<V, F>,
) : AsyncOperatorAction<V, F>

data class STMAction<V, F>(
    val action: (chromosome: Chromosome<V, F>) -> Array<Chromosome<V, F>>,
) : AsyncOperatorAction<V, F>

data class MTSAction<V, F>(
    val action: (chromosomes: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) : AsyncOperatorAction<V, F>

data class MTMAction<V, F>(
    val action: (chromosomes: Array<Chromosome<V, F>>) -> Array<Chromosome<V, F>>,
) : AsyncOperatorAction<V, F>
