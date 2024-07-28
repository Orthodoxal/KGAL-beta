package genetic.clusters.panmictic.operators.crossover

import genetic.chromosome.Chromosome
import genetic.clusters.panmictic.PanmicticLifecycle
import genetic.utils.clusters.*
import genetic.utils.randomByChance

suspend inline fun <V, F> PanmicticLifecycle<V, F>.crossover(
    chance: Double,
    onlySingleRun: Boolean,
    crossinline crossover: (chromosome1: Chromosome<V, F>, chromosome2: Chromosome<V, F>) -> Unit,
) {
    if (isSingleRun || onlySingleRun) {
        singleRunCrossover(chance, crossover)
    } else {
        multiRunCrossover(chance, crossover)
    }
}

inline fun <V, F> PanmicticLifecycle<V, F>.singleRunCrossover(
    chance: Double,
    crossover: (chromosome1: Chromosome<V, F>, chromosome2: Chromosome<V, F>) -> Unit,
) {
    val tempPopulation = population.copyOf()
    for (index in (elitism)..population.lastIndex step 2) {
        randomByChance(chance) {
            var index1 = random.nextInt(0, population.lastIndex - 1)
            val index2 = random.nextInt(0, population.lastIndex)
            if (index1 == index2) index1++

            if (population[index1] != population[index2]) {
                val child1 = population.cloneOf(index1)
                val child2 = population.cloneOf(index2)
                crossover(child1, child2)
                tempPopulation[index - 1] = child1
                tempPopulation[index] = child2
            }
        }
    }
    population.set(tempPopulation)
}

suspend inline fun <V, F> PanmicticLifecycle<V, F>.multiRunCrossover(
    chance: Double,
    crossinline crossover: (chromosome1: Chromosome<V, F>, chromosome2: Chromosome<V, F>) -> Unit,
) = runWithExtraDispatchersIterative(0, popCurrentSize / 2) { index ->
    randomByChance(chance) {
        val parent1 = if (index < elitism) population.cloneOf(index) else population[index]
        val parent2 = population[population.lastIndex - index]
        if (parent1 != parent2) crossover(parent1, parent2)
    }
}
