package genetic.ga.panmictic.operators.selection

import genetic.chromosome.Chromosome
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.panmictic.builder.PanmicticGABuilder
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

suspend inline fun <V, F> SimpleClusterLifecycle<V, F>.selection(
    panmicticGABuilder: PanmicticGABuilder<V, F>,
    onlySingleRun: Boolean,
    crossinline selection: (source: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) {
    val tempPopulation = population.copyOf()
    if (isSingleRun || onlySingleRun) {
        singleRunSelection(panmicticGABuilder, tempPopulation, selection)
    } else {
        multiRunSelection(panmicticGABuilder, tempPopulation, selection)
    }
    population = tempPopulation
}

inline fun <V, F> SimpleClusterLifecycle<V, F>.singleRunSelection(
    panmicticGABuilder: PanmicticGABuilder<V, F>,
    tempPopulation: Array<Chromosome<V, F>>,
    selection: (source: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) = repeat(populationSize) { index ->
    tempPopulation[index] = selection(population)
}

suspend inline fun <V, F> SimpleClusterLifecycle<V, F>.multiRunSelection(
    panmicticGABuilder: PanmicticGABuilder<V, F>,
    tempPopulation: Array<Chromosome<V, F>>,
    crossinline selection: (source: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) {
    maxIteration = populationSize
    currentIteration.set(0)

    coroutineScope {
        extraDispatchers?.map {
            launch(it) {
                var iteration = currentIteration.getAndIncrement()
                while (iteration < maxIteration) {
                    tempPopulation[iteration] = selection(population)
                    iteration = currentIteration.getAndIncrement()
                }
            }
        }
    }?.joinAll()
}
