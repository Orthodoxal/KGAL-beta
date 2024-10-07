package genetic.ga.distributed.population

import genetic.ga.core.population.Population
import genetic.ga.distributed.population.DistributedPopulation.Companion.DEFAULT_NAME_BUILDER

interface DistributedPopulation<V, F> : Population<V, F> {
    val childPopulations: List<Population<V, F>>

    companion object {
        const val DEFAULT_DISTRIBUTED_POPULATION_NAME = "DISTRIBUTED POPULATION 1"
        val DEFAULT_NAME_BUILDER: (childNames: List<String>) -> String = { childNames ->
            "$DEFAULT_DISTRIBUTED_POPULATION_NAME of: ${childNames.joinToString()}"
        }
    }
}

fun <V, F> population(
    childPopulations: List<Population<V, F>>,
    nameBuilder: (childNames: List<String>) -> String = DEFAULT_NAME_BUILDER,
): DistributedPopulation<V, F> = DistributedPopulationInstance(childPopulations, nameBuilder)
