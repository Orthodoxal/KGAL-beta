package genetic.ga.panmictic.operators.mutation

import genetic.chromosome.Chromosome
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.panmictic.builder.PanmicticGABuilder
import genetic.utils.clusters.runWithExtraDispatchersIterative
import genetic.utils.forEach
import genetic.utils.randomByChance

suspend inline fun <V, F> SimpleClusterLifecycle<V, F>.mutation(
    panmicticGABuilder: PanmicticGABuilder<V, F>,
    chance: Double,
    onlySingleRun: Boolean,
    crossinline mutation: (chromosome: Chromosome<V, F>) -> Unit,
) {
    if (isSingleRun || onlySingleRun) {
        singleRunMutation(panmicticGABuilder, chance, mutation)
    } else {
        multiRunMutation(panmicticGABuilder, chance, mutation)
    }
}

inline fun <V, F> SimpleClusterLifecycle<V, F>.singleRunMutation(
    panmicticGABuilder: PanmicticGABuilder<V, F>,
    chance: Double,
    mutation: (chromosome: Chromosome<V, F>) -> Unit,
) = population.forEach(elitism, populationSize) { randomByChance(chance) { mutation(it) } }

suspend inline fun <V, F> SimpleClusterLifecycle<V, F>.multiRunMutation(
    panmicticGABuilder: PanmicticGABuilder<V, F>,
    chance: Double,
    crossinline mutation: (chromosome: Chromosome<V, F>) -> Unit,
) = runWithExtraDispatchersIterative(elitism, populationSize) { iteration ->
    randomByChance(chance) { mutation(population[iteration]) }
}
