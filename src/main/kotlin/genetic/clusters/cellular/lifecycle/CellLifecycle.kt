package genetic.clusters.cellular.lifecycle

import genetic.chromosome.Chromosome

interface CellLifecycle<V, F> : CellularLifecycle<V, F> {
    var cellChromosome: Chromosome<V, F>
    val neighbours: Array<Chromosome<V, F>>
}

internal class CellLifecycleInstance<V, F>(
    override var cellChromosome: Chromosome<V, F>,
    override val neighbours: Array<Chromosome<V, F>>,
    cellularLifecycle: CellularLifecycle<V, F>,
) : CellLifecycle<V, F>, CellularLifecycle<V, F> by cellularLifecycle

fun <V, F> CellularLifecycle<V, F>.cellLifecycle(
    cellChromosome: Chromosome<V, F>,
    neighbours: Array<Chromosome<V, F>>,
): CellLifecycle<V, F> = CellLifecycleInstance(cellChromosome, neighbours, this)
