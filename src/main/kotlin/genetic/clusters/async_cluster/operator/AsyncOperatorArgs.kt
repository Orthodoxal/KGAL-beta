package genetic.clusters.async_cluster.operator

import genetic.chromosome.Chromosome

sealed interface AsyncOperatorArgs<V, F> {
    class Single<V, F>(val chromosome: Chromosome<V, F>) : AsyncOperatorArgs<V, F>
    class Multiple<V, F>(val chromosomes: Array<Chromosome<V, F>>) : AsyncOperatorArgs<V, F>
}
