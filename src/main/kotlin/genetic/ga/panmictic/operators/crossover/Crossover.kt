package genetic.ga.panmictic.operators.crossover

import genetic.chromosome.Chromosome
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.panmictic.builder.PanmicticGABuilder
import genetic.utils.clusters.runWithExtraDispatchersIterative
import genetic.utils.randomByChance

suspend inline fun <V, F> SimpleClusterLifecycle<V, F>.crossover(
    panmicticGABuilder: PanmicticGABuilder<V, F>,
    chance: Double,
    onlySingleRun: Boolean,
    crossinline crossover: (chromosome1: Chromosome<V, F>, chromosome2: Chromosome<V, F>) -> Unit,
) {
    if (isSingleRun || onlySingleRun) {
        singleRunCrossover(panmicticGABuilder, chance, crossover)
    } else {
        multiRunCrossover(panmicticGABuilder, chance, crossover)
    }
}

inline fun <V, F> SimpleClusterLifecycle<V, F>.singleRunCrossover(
    panmicticGABuilder: PanmicticGABuilder<V, F>,
    chance: Double,
    crossover: (chromosome1: Chromosome<V, F>, chromosome2: Chromosome<V, F>) -> Unit,
) = repeat(populationSize / 2) { index ->
    randomByChance(chance) {
        val parent1 = if (index < elitism) population[index].clone() else population[index]
        val parent2 = population[population.lastIndex - index]
        if (parent1 != parent2) crossover(parent1, parent2)
    }
}

suspend inline fun <V, F> SimpleClusterLifecycle<V, F>.multiRunCrossover(
    panmicticGABuilder: PanmicticGABuilder<V, F>,
    chance: Double,
    crossinline crossover: (chromosome1: Chromosome<V, F>, chromosome2: Chromosome<V, F>) -> Unit,
) = runWithExtraDispatchersIterative(0, populationSize / 2) { index ->
    randomByChance(chance) {
        val parent1 = if (index < elitism) population[index].clone() else population[index]
        val parent2 = population[population.lastIndex - index]
        if (parent1 != parent2) crossover(parent1, parent2)
    }
}
