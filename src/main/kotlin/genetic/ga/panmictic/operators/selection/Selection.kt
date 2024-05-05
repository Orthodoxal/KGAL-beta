package genetic.ga.panmictic.operators.selection

import genetic.chromosome.Chromosome
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.panmictic.builder.PanmicticGABuilder
import genetic.ga.panmictic.operators.selection.elitism.moveToStartElitChromosomes
import genetic.utils.clusters.runWithExtraDispatchersIterative
import genetic.utils.loop

suspend inline fun <V, F> SimpleClusterLifecycle<V, F>.selection(
    panmicticGABuilder: PanmicticGABuilder<V, F>,
    elitism: Int,
    onlySingleRun: Boolean,
    crossinline selection: (source: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) {
    this.elitism = elitism
    if (elitism > 0) {
        moveToStartElitChromosomes()
    } else if (elitism < 0) {
        throw IllegalStateException("Elitism must be more or equal to zero")
    }

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
) = loop(elitism, populationSize) { index ->
    tempPopulation[index] = selection(population)
}

suspend inline fun <V, F> SimpleClusterLifecycle<V, F>.multiRunSelection(
    panmicticGABuilder: PanmicticGABuilder<V, F>,
    tempPopulation: Array<Chromosome<V, F>>,
    crossinline selection: (source: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) = runWithExtraDispatchersIterative(elitism, populationSize) { iteration ->
    tempPopulation[iteration] = selection(population)
}
