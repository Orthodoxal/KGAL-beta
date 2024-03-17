package genetic.ga.panmictic.operators.mutation

import genetic.chromosome.Chromosome
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.panmictic.builder.PanmicticGABuilder
import genetic.utils.randomByChance

inline fun <V, F> SimpleClusterLifecycle<V, F>.mutation(
    panmicticGABuilder: PanmicticGABuilder<V, F>,
    chance: Double = 0.1,
    mutation: (chromosome: Chromosome<V, F>) -> Unit
) = population.forEach { chromosome -> randomByChance(chance) { mutation(chromosome) } }
