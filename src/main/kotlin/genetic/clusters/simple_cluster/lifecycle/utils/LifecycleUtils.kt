package genetic.clusters.simple_cluster.lifecycle.utils

import genetic.chromosome.Chromosome
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
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

fun <V, F> SimpleClusterLifecycle<V, F>.singleRunFitnessAll() {
    for (chromosome in population) chromosome.fitness = fitnessFunction(chromosome.value)
}

suspend fun <V, F> SimpleClusterLifecycle<V, F>.multiRunSelection() {
    maxIteration = populationSize
    currentIteration.set(0)

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
