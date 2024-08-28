package genetic.ga.panmictic.operators.selection

import genetic.chromosome.Chromosome
import genetic.ga.core.lifecycle.currentSize
import genetic.ga.core.lifecycle.isSingleRun
import genetic.ga.core.lifecycle.runWithExtraDispatchersIterative
import genetic.ga.core.population.copyOf
import genetic.ga.core.population.get
import genetic.ga.core.population.set
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle
import genetic.ga.panmictic.operators.selection.elitism.moveToStartElitChromosomes
import genetic.utils.loop

suspend inline fun <V, F> PanmicticLifecycle<V, F>.selection(
    onlySingleRun: Boolean,
    crossinline selection: (source: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) {
    if (elitism > 0) {
        moveToStartElitChromosomes()
    } else if (elitism < 0) {
        throw IllegalStateException("Elitism must be more or equal to zero")
    }

    val tempPopulation = population.copyOf()
    if (isSingleRun || onlySingleRun) {
        singleRunSelection(tempPopulation, selection)
    } else {
        multiRunSelection(tempPopulation, selection)
    }
    population.set(tempPopulation)
}

inline fun <V, F> PanmicticLifecycle<V, F>.singleRunSelection(
    tempPopulation: Array<Chromosome<V, F>>,
    selection: (source: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) = loop(elitism, currentSize) { index ->
    tempPopulation[index] = selection(population.get())
}

suspend inline fun <V, F> PanmicticLifecycle<V, F>.multiRunSelection(
    tempPopulation: Array<Chromosome<V, F>>,
    crossinline selection: (source: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) = runWithExtraDispatchersIterative(elitism, currentSize) { iteration ->
    tempPopulation[iteration] = selection(population.get())
}

suspend inline fun <V, F> PanmicticLifecycle<V, F>.selectionWithIndex(
    onlySingleRun: Boolean,
    crossinline selection: (index: Int, source: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) {
    if (elitism > 0) {
        moveToStartElitChromosomes()
    } else if (elitism < 0) {
        throw IllegalStateException("Elitism must be more or equal to zero")
    }

    val tempPopulation = population.copyOf()
    if (isSingleRun || onlySingleRun) {
        singleRunSelectionWithIndex(tempPopulation, selection)
    } else {
        multiRunSelectionWithIndex(tempPopulation, selection)
    }
    population.set(tempPopulation)
}

inline fun <V, F> PanmicticLifecycle<V, F>.singleRunSelectionWithIndex(
    tempPopulation: Array<Chromosome<V, F>>,
    selection: (index: Int, source: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) = loop(elitism, currentSize) { index ->
    tempPopulation[index] = selection(index, population.get())
}

suspend inline fun <V, F> PanmicticLifecycle<V, F>.multiRunSelectionWithIndex(
    tempPopulation: Array<Chromosome<V, F>>,
    crossinline selection: (index: Int, source: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) = runWithExtraDispatchersIterative(elitism, currentSize) { iteration ->
    tempPopulation[iteration] = selection(iteration, population.get())
}

