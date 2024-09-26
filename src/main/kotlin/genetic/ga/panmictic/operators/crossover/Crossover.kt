package genetic.ga.panmictic.operators.crossover

import genetic.chromosome.Chromosome
import genetic.ga.core.lifecycle.size
import genetic.ga.core.parallelism.processor.execute
import genetic.ga.core.population.*
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle
import genetic.utils.randomByChance

suspend inline fun <V, F> PanmicticLifecycle<V, F>.crossover(
    chance: Double,
    parallelWorkersLimit: Int,
    crossoverType: CrossoverType,
    crossinline crossover: suspend (chromosome1: Chromosome<V, F>, chromosome2: Chromosome<V, F>) -> Unit,
) {
    when (crossoverType) {
        CrossoverType.Iterative -> {
            iterativeCrossover(chance, parallelWorkersLimit, crossover)
        }

        CrossoverType.Randomly -> {
            randomlyCrossover(chance, parallelWorkersLimit, crossover)
        }
    }
}

suspend inline fun <V, F> PanmicticLifecycle<V, F>.iterativeCrossover(
    chance: Double,
    parallelWorkersLimit: Int,
    crossinline crossover: suspend (chromosome1: Chromosome<V, F>, chromosome2: Chromosome<V, F>) -> Unit,
) {
    execute(
        parallelWorkersLimit = parallelWorkersLimit,
        startIteration = 0,
        endIteration = size / 2,
        action = { index ->
            randomByChance(chance) {
                val parent1 = if (index < elitism) population.cloneOf(index) else population[index]
                val parent2 = population[population.lastIndex - index]
                if (parent1 != parent2) crossover(parent1, parent2)
            }
        }
    )
}

suspend inline fun <V, F> PanmicticLifecycle<V, F>.randomlyCrossover(
    chance: Double,
    parallelWorkersLimit: Int,
    crossinline crossover: suspend (chromosome1: Chromosome<V, F>, chromosome2: Chromosome<V, F>) -> Unit,
) {
    val tempPopulation = population.copyOf()
    execute(
        parallelWorkersLimit = parallelWorkersLimit,
        startIteration = elitism + 1,
        endIteration = size,
        step = 2,
        action = { index ->
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
        },
    )
    population.set(tempPopulation)
}
