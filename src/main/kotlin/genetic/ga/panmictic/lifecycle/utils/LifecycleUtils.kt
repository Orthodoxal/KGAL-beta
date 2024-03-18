package genetic.ga.panmictic.lifecycle.utils

import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle

fun <V, F> SimpleClusterLifecycle<V, F>.fitnessAll() {
    for (chromosome in population) chromosome.fitness = fitnessFunction(chromosome.value)
}
