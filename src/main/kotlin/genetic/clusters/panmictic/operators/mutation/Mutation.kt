package genetic.clusters.panmictic.operators.mutation

import genetic.chromosome.Chromosome
import genetic.clusters.base.lifecycle.isSingleRun
import genetic.clusters.panmictic.PanmicticLifecycle
import genetic.utils.clusters.forEach
import genetic.utils.clusters.get
import genetic.utils.clusters.popCurrentSize
import genetic.utils.clusters.runWithExtraDispatchersIterative
import genetic.utils.randomByChance

suspend inline fun <V, F> PanmicticLifecycle<V, F>.mutation(
    chance: Double,
    onlySingleRun: Boolean,
    crossinline mutation: (chromosome: Chromosome<V, F>) -> Unit,
) {
    if (isSingleRun || onlySingleRun) {
        singleRunMutation(chance, mutation)
    } else {
        multiRunMutation(chance, mutation)
    }
}

inline fun <V, F> PanmicticLifecycle<V, F>.singleRunMutation(
    chance: Double,
    mutation: (chromosome: Chromosome<V, F>) -> Unit,
) = population.forEach(elitism, popCurrentSize) { randomByChance(chance) { mutation(it) } }

suspend inline fun <V, F> PanmicticLifecycle<V, F>.multiRunMutation(
    chance: Double,
    crossinline mutation: (chromosome: Chromosome<V, F>) -> Unit,
) = runWithExtraDispatchersIterative(elitism, popCurrentSize) { iteration ->
    randomByChance(chance) { mutation(population[iteration]) }
}
