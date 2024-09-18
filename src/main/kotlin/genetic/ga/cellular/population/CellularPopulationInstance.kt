package genetic.ga.cellular.population

import genetic.chromosome.Chromosome
import genetic.ga.cellular.utils.Dimens
import genetic.ga.core.population.PopulationFactory

internal class CellularPopulationInstance<V, F>(
    override val dimens: Dimens,
    override val name: String,
    override var factory: PopulationFactory<V, F>,
    override var population: Array<Chromosome<V, F>> = emptyArray(),
    override var size: Int = dimens.size,
) : CellularPopulation<V, F> {
    override val maxSize: Int get() = size

    override fun copy(): CellularPopulation<V, F> =
        CellularPopulationInstance(
            dimens = dimens,
            name = name,
            factory = factory,
            population = population.copyOf(),
            size = size,
        )
}
