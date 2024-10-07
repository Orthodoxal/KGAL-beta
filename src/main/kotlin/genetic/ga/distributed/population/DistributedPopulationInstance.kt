package genetic.ga.distributed.population

import genetic.chromosome.Chromosome
import genetic.ga.core.population.Population
import genetic.ga.core.population.PopulationFactory
import genetic.ga.core.population.get
import genetic.ga.distributed.population.DistributedPopulation.Companion.DEFAULT_NAME_BUILDER

internal class DistributedPopulationInstance<V, F>(
    override val childPopulations: List<Population<V, F>>,
    private val nameBuilder: (childNames: List<String>) -> String = DEFAULT_NAME_BUILDER,
) : DistributedPopulation<V, F> {
    override val name: String
        get() = nameBuilder(childPopulations.map { it.name })

    override var size: Int
        get() = childPopulations.sumOf { it.size }
        set(_) {}

    override val maxSize: Int
        get() = childPopulations.sumOf { it.maxSize }

    override var factory: PopulationFactory<V, F>
        get() = childPopulations.first().factory
        set(_) {}

    override var population: Array<Chromosome<V, F>>
        get() {
            val sizes = childPopulations.map { it.size }
            val size = sizes.sum()
            var cursor = 0
            var realIndex = 0
            return Array(size) {
                if (sizes[cursor] == realIndex) {
                    realIndex = 0
                    cursor++
                }
                childPopulations[cursor][realIndex].also { realIndex++ }
            }
        }
        set(_) {}

    override fun clone(newName: String): Population<V, F> = this
}
