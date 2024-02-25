package genetic.clusters.cluster

import genetic.chromosome.Chromosome
import genetic.chromosome.ChromosomeComparator
import genetic.clusters.state.ClusterState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.random.Random

interface Cluster<V, F> : ClusterApi {
    val state: MutableStateFlow<ClusterState>
    var population: Array<Chromosome<V, F>>
    var randomSeed: Int
    var random: Random

    var fitnessFunction: (Chromosome<V, F>) -> Unit
    var comparator: ChromosomeComparator<V, F>
    var clone: Chromosome<V, F>.() -> Chromosome<V, F>

    var generation: Int
    var maxGeneration: Int

    var clusterJob: Job?
}
