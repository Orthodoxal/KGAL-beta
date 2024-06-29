package genetic.clusters.simple_cluster.lifecycle.operators

import genetic.chromosome.Chromosome
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.utils.forEach
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

suspend fun <V, F> SimpleClusterLifecycle<V, F>.fitnessAll(onlySingleRun: Boolean = false) {
    if (isSingleRun || onlySingleRun) {
        singleRunFitnessAll()
    } else {
        multiRunSelection()
    }
}

fun <V, F> SimpleClusterLifecycle<V, F>.singleRunFitnessAll() =
    population.forEach(elitism, populationSize) { it.fitness = fitnessFunction(it.value) }

suspend fun <V, F> SimpleClusterLifecycle<V, F>.multiRunSelection() {
    maxIteration = populationSize
    currentIteration.set(elitism)

    coroutineScope {
        extraDispatchers?.map {
            launch(it) {
                var iteration = currentIteration.getAndIncrement()
                while (iteration < maxIteration) {
                    val chromosome = population[iteration]
                    chromosome.fitness = fitnessFunction(chromosome.value)
                    iteration = currentIteration.getAndIncrement()
                }
            }
        }
    }?.joinAll()
}

fun <V, F> SimpleClusterLifecycle<V, F>.fitness(chromosome: Chromosome<V, F>) {
    chromosome.fitness = fitnessFunction(chromosome.value)
}
