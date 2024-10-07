package genetic.ga.panmictic.operators.selection

import genetic.chromosome.Chromosome
import genetic.ga.core.lifecycle.size
import genetic.ga.core.population.copyOf
import genetic.ga.core.population.get
import genetic.ga.core.population.set
import genetic.ga.core.processor.process
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle
import genetic.ga.panmictic.operators.selection.elitism.moveToStartElitChromosomes
import kotlin.random.Random

suspend inline fun <V, F> PanmicticLifecycle<V, F>.selection(
    parallelismLimit: Int,
    crossinline selection: (source: Array<Chromosome<V, F>>, random: Random) -> Chromosome<V, F>,
) {
    if (elitism > 0) {
        moveToStartElitChromosomes()
    } else if (elitism < 0) {
        throw IllegalStateException("Elitism must be more or equal to zero")
    }

    val tempPopulation = population.copyOf()
    process(
        parallelismLimit = parallelismLimit,
        startIteration = elitism,
        endIteration = size,
        action = { index, random ->
            tempPopulation[index] = selection(population.get(), random)
        },
    )
    population.set(tempPopulation)
}

suspend inline fun <V, F> PanmicticLifecycle<V, F>.selectionWithIndex(
    parallelismLimit: Int,
    crossinline selection: (index: Int, source: Array<Chromosome<V, F>>, random: Random) -> Chromosome<V, F>,
) {
    if (elitism > 0) {
        moveToStartElitChromosomes()
    } else if (elitism < 0) {
        throw IllegalStateException("Elitism must be more or equal to zero")
    }

    val tempPopulation = population.copyOf()
    process(
        parallelismLimit = parallelismLimit,
        startIteration = elitism,
        endIteration = size,
        action = { index, random ->
            tempPopulation[index] = selection(index, population.get(), random)
        },
    )
    population.set(tempPopulation)
}
