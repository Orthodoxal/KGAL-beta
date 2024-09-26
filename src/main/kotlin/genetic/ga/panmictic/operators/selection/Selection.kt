package genetic.ga.panmictic.operators.selection

import genetic.chromosome.Chromosome
import genetic.ga.core.lifecycle.size
import genetic.ga.core.parallelism.processor.execute
import genetic.ga.core.population.copyOf
import genetic.ga.core.population.get
import genetic.ga.core.population.set
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle
import genetic.ga.panmictic.operators.selection.elitism.moveToStartElitChromosomes

suspend inline fun <V, F> PanmicticLifecycle<V, F>.selection(
    parallelWorkersLimit: Int,
    crossinline selection: (source: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) {
    if (elitism > 0) {
        moveToStartElitChromosomes()
    } else if (elitism < 0) {
        throw IllegalStateException("Elitism must be more or equal to zero")
    }

    val tempPopulation = population.copyOf()
    execute(
        parallelWorkersLimit = parallelWorkersLimit,
        startIteration = elitism,
        endIteration = size,
        action = { index -> tempPopulation[index] = selection(population.get()) },
    )
    population.set(tempPopulation)
}

suspend inline fun <V, F> PanmicticLifecycle<V, F>.selectionWithIndex(
    parallelWorkersLimit: Int,
    crossinline selection: (index: Int, source: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) {
    if (elitism > 0) {
        moveToStartElitChromosomes()
    } else if (elitism < 0) {
        throw IllegalStateException("Elitism must be more or equal to zero")
    }

    val tempPopulation = population.copyOf()
    execute(
        parallelWorkersLimit = parallelWorkersLimit,
        startIteration = elitism,
        endIteration = size,
        action = { tempPopulation[it] = selection(it, population.get()) },
    )
    population.set(tempPopulation)
}
