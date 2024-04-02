package genetic.ga.panmictic.operators.mutation

import genetic.chromosome.Chromosome
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.panmictic.builder.PanmicticGABuilder
import genetic.utils.randomByChance
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

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
) {
    for (chromosome in population) randomByChance(chance) { mutation(chromosome) }
}

suspend inline fun <V, F> SimpleClusterLifecycle<V, F>.multiRunMutation(
    panmicticGABuilder: PanmicticGABuilder<V, F>,
    chance: Double,
    crossinline mutation: (chromosome: Chromosome<V, F>) -> Unit,
) {
    maxIteration = populationSize
    currentIteration.set(0)

    coroutineScope {
        extraDispatchers?.map {
            launch(it) {
                var iteration = currentIteration.getAndIncrement()
                while (iteration < maxIteration) {
                    randomByChance(chance) { mutation(population[iteration]) }
                    iteration = currentIteration.getAndIncrement()
                }
            }
        }
    }?.joinAll()
}
