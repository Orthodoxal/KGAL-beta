package genetic.ga.cellular.population

import genetic.chromosome.Chromosome
import genetic.ga.cellular.utils.Dimens
import genetic.ga.core.population.Population
import genetic.ga.core.population.Population.Companion.DEFAULT_POPULATION_NAME
import genetic.ga.core.population.PopulationFactory

interface CellularPopulation<V, F> : Population<V, F> {
    val dimens: Dimens

    override fun clone(newName: String): CellularPopulation<V, F>
}

fun <V, F> population(
    dimens: Dimens,
    name: String = DEFAULT_POPULATION_NAME,
    factory: PopulationFactory<V, F>,
): CellularPopulation<V, F> = CellularPopulationInstance(dimens, name, factory)

fun <V, F> population(
    dimens: Dimens,
    name: String = DEFAULT_POPULATION_NAME,
    population: Array<Chromosome<V, F>>,
    factory: PopulationFactory<V, F>,
): CellularPopulation<V, F> {
    if (dimens.size != population.size) throw IllegalStateException("Dimens size must be equal to population size")
    return CellularPopulationInstance(dimens, name, factory, population)
}
