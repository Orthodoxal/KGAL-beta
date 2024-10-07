package genetic.ga.panmictic.population

import genetic.chromosome.Chromosome
import genetic.ga.core.population.PopulationFactory

internal class PanmicticPopulationInstance<V, F>(
    override val name: String,
    override var size: Int,
    buffer: Int,
    override var factory: PopulationFactory<V, F>,
    override var population: Array<Chromosome<V, F>> = emptyArray(),
) : PanmicticPopulation<V, F> {

    override var buffer: Int = buffer
        set(value) {
            field = value
            population = Array(maxSize) { index ->
                if (index < population.size) population[index] else population.first()
            }
        }

    override val maxSize: Int get() = size + buffer

    override fun clone(newName: String): PanmicticPopulation<V, F> =
        PanmicticPopulationInstance(
            name = newName,
            size = size,
            buffer = buffer,
            factory = factory,
            population = Array(maxSize) { index -> population[index].clone() },
        )
}
