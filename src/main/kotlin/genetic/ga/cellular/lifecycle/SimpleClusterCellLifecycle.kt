package genetic.ga.cellular.lifecycle

import genetic.chromosome.Chromosome
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle

interface SimpleClusterCellLifecycle<V, F> : SimpleClusterLifecycle<V, F> {
    var cellChromosome: Chromosome<V, F>
    val neighbours: Array<Chromosome<V, F>>
}

internal class SimpleClusterCellLifecycleInstance<V, F>(
    override var cellChromosome: Chromosome<V, F>,
    override val neighbours: Array<Chromosome<V, F>>,
    simpleClusterLifecycle: SimpleClusterLifecycle<V, F>,
) : SimpleClusterCellLifecycle<V, F>, SimpleClusterLifecycle<V, F> by simpleClusterLifecycle

fun <V, F> SimpleClusterLifecycle<V, F>.simpleClusterCellLifecycle(
    cellChromosome: Chromosome<V, F>,
    neighbours: Array<Chromosome<V, F>>,
): SimpleClusterCellLifecycle<V, F> =
    SimpleClusterCellLifecycleInstance(cellChromosome, neighbours, this)
