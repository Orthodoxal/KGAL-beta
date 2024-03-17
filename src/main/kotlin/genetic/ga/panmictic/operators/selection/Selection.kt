package genetic.ga.panmictic.operators.selection

import genetic.chromosome.Chromosome
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.panmictic.builder.PanmicticGABuilder

inline fun <V, F> SimpleClusterLifecycle<V, F>.selection(
    panmicticGABuilder: PanmicticGABuilder<V, F>,
    selection: (source: Array<Chromosome<V, F>>) -> Chromosome<V, F>
) {
    val tempPopulation = population.copyOf()
    repeat(populationSize) { index ->
        tempPopulation[index] = selection(population)
    }
    population = tempPopulation
}
