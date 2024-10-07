package genetic.ga.cellular.lifecycle

import genetic.chromosome.Chromosome
import kotlin.random.Random

interface CellLifecycle<V, F> : CellularLifecycle<V, F> {
    var cellChromosome: Chromosome<V, F>
    val neighbours: Array<Chromosome<V, F>>
}

internal class CellLifecycleInstance<V, F>(
    override var cellChromosome: Chromosome<V, F>,
    override val neighbours: Array<Chromosome<V, F>>,
    override val random: Random,
    cellularLifecycle: CellularLifecycle<V, F>,
) : CellLifecycle<V, F>, CellularLifecycle<V, F> by cellularLifecycle

fun <V, F> CellularLifecycle<V, F>.cellLifecycle(
    cellChromosome: Chromosome<V, F>,
    neighbours: Array<Chromosome<V, F>>,
    random: Random,
): CellLifecycle<V, F> = CellLifecycleInstance(cellChromosome, neighbours, random, this)
