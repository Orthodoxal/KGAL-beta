package genetic.clusters.async_cluster.builder

import genetic.chromosome.Chromosome
import genetic.chromosome.ChromosomeComparator
import genetic.clusters.async_cluster.lifecycle.AsyncClusterLifecycle
import genetic.clusters.async_cluster.operator.AsyncOperator
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.random.Random

interface AsyncClusterBuilder<V, F> {
    var clusterId: String
    var population: Array<Chromosome<V, F>>
    var populationSize: Int
    var populationFactory: (index: Int, random: Random) -> Chromosome<V, F>
    var randomSeed: Int
    var fitnessFunction: (Chromosome<V, F>) -> Unit
    var comparator: ChromosomeComparator<V, F>
    var clone: Chromosome<V, F>.() -> Chromosome<V, F>
    var maxGeneration: Int
    var dispatchers: Array<CoroutineDispatcher>
    var operators: Array<AsyncOperator<V, F>>

    fun AsyncClusterBuilder<V, F>.lifecycle(lifecycle: AsyncClusterLifecycle<V, F>.() -> Unit)
}
