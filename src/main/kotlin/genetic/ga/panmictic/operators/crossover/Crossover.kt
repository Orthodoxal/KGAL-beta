package genetic.ga.panmictic.operators.crossover

import genetic.chromosome.Chromosome
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.panmictic.builder.PanmicticGABuilder
import genetic.utils.randomByChance

inline fun <V, F> SimpleClusterLifecycle<V, F>.crossover(
    panmicticGABuilder: PanmicticGABuilder<V, F>,
    chance: Double = 0.9,
    crossover: (chromosome1: Chromosome<V, F>, chromosome2: Chromosome<V, F>) -> Unit
) = repeat(populationSize / 2) { index ->
    randomByChance(chance) {
        val parent1 = population[index]
        val parent2 = population[population.lastIndex - index]
        if (parent1 != parent2) crossover(parent1, parent2)
    }
}
