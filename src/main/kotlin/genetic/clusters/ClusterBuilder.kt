package genetic.clusters

import genetic.chromosome.Chromosome
import kotlin.random.Random

interface ClusterBuilder<V, F> {
    val random: Random
    var clusterId: String
    var population: Array<Chromosome<V, F>>
    var populationSize: Int
    var populationFactory: (index: Int, random: Random) -> Chromosome<V, F>
    var randomSeed: Int
    var fitnessFunction: (V) -> F
    var maxGeneration: Int
}
