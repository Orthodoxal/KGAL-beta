package genetic.ga.panmictic.operators.mutation

import genetic.chromosome.Chromosome
import genetic.ga.core.lifecycle.currentSize
import genetic.ga.core.lifecycle.isSingleRun
import genetic.ga.core.lifecycle.runWithExtraDispatchersIterative
import genetic.ga.core.population.forEach
import genetic.ga.core.population.get
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle
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
) = population.forEach(elitism, currentSize) { randomByChance(chance) { mutation(it) } }

suspend inline fun <V, F> PanmicticLifecycle<V, F>.multiRunMutation(
    chance: Double,
    crossinline mutation: (chromosome: Chromosome<V, F>) -> Unit,
) = runWithExtraDispatchersIterative(elitism, currentSize) { iteration ->
    randomByChance(chance) { mutation(population[iteration]) }
}
