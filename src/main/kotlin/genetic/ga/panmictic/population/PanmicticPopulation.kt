package genetic.ga.panmictic.population

import genetic.chromosome.Chromosome
import genetic.ga.core.population.Population
import genetic.ga.core.population.Population.Companion.DEFAULT_POPULATION_NAME
import genetic.ga.core.population.PopulationFactory

interface PanmicticPopulation<V, F> : Population<V, F> {
    var buffer: Int

    override fun clone(newName: String): PanmicticPopulation<V, F>
}

fun <V, F> population(
    size: Int,
    buffer: Int = 0,
    name: String = DEFAULT_POPULATION_NAME,
    factory: PopulationFactory<V, F>,
): PanmicticPopulation<V, F> = PanmicticPopulationInstance(name, size, buffer, factory)

fun <V, F> population(
    buffer: Int = 0,
    name: String = DEFAULT_POPULATION_NAME,
    population: Array<Chromosome<V, F>>,
    factory: PopulationFactory<V, F>,
): PanmicticPopulation<V, F> = PanmicticPopulationInstance(name, population.size, buffer, factory, population)
