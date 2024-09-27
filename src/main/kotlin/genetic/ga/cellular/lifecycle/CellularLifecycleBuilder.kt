package genetic.ga.cellular.lifecycle

import genetic.chromosome.Chromosome
import genetic.ga.cellular.type.CellularType
import genetic.ga.cellular.type.UpdatePolicy
import genetic.ga.core.lifecycle.size
import genetic.ga.core.population.copyOf
import genetic.ga.core.population.get
import genetic.ga.core.population.set
import genetic.ga.core.processor.process
import kotlin.random.Random

fun <V, F> buildCellularLifecycle(
    parallelismLimit: Int,
    beforeLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)? = null,
    afterLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)? = null,
    cellLifecycle: suspend CellularLifecycle<V, F>.(chromosome: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>, random: Random) -> Chromosome<V, F>,
): suspend CellularLifecycle<V, F>.() -> Unit = {
    beforeLifecycleIteration?.invoke(this)
    when (val cellularType = cellularType) {
        is CellularType.Synchronous -> {
            synchronousExecute(parallelismLimit, cellLifecycle)
        }

        is CellularType.Asynchronous -> {
            asynchronousExecute(cellularType.updatePolicy, parallelismLimit, cellLifecycle)
        }
    }
    afterLifecycleIteration?.invoke(this)
}

private suspend inline fun <V, F> CellularLifecycle<V, F>.synchronousExecute(
    parallelismLimit: Int,
    crossinline cellLifecycle: suspend CellularLifecycle<V, F>.(chromosome: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>, random: Random) -> Chromosome<V, F>,
) {
    val tempPopulation = population.copyOf()
    process(parallelismLimit) { iteration, random ->
        processCellLifecycle(random, iteration, tempPopulation, cellLifecycle)
    }
    population.set(tempPopulation)
}

private suspend inline fun <V, F> CellularLifecycle<V, F>.asynchronousExecute(
    updatePolicy: UpdatePolicy,
    parallelismLimit: Int,
    crossinline cellLifecycle: suspend CellularLifecycle<V, F>.(chromosome: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>, random: Random) -> Chromosome<V, F>,
) = when (updatePolicy) {
    is UpdatePolicy.LineSweep -> {
        process(parallelismLimit) { iteration, random ->
            processCellLifecycle(random, iteration, population.get(), cellLifecycle)
        }
    }

    is UpdatePolicy.FixedRandomSweep -> {
        val indicesShuffled = updatePolicy.cacheIndices(size)
        process(parallelismLimit) { iteration, random ->
            val index = indicesShuffled[iteration]
            processCellLifecycle(random, index, population.get(), cellLifecycle)
        }
    }

    is UpdatePolicy.NewRandomSweep -> {
        val indicesShuffled = IntArray(size) { it }.apply { shuffle(random) }
        process(parallelismLimit) { iteration, random ->
            val index = indicesShuffled[iteration]
            processCellLifecycle(random, index, population.get(), cellLifecycle)
        }
    }

    is UpdatePolicy.UniformChoice -> {
        process(parallelismLimit) { _, random ->
            val index = random.nextInt(size)
            processCellLifecycle(random, index, population.get(), cellLifecycle)
        }
    }
}

private suspend inline fun <V, F> CellularLifecycle<V, F>.process(
    parallelismLimit: Int,
    crossinline action: suspend (iteration: Int, random: Random) -> Unit,
) {
    process(
        parallelismLimit = parallelismLimit,
        startIteration = 0,
        endIteration = size,
        action = action,
    )
}

private suspend inline fun <V, F> CellularLifecycle<V, F>.processCellLifecycle(
    random: Random,
    index: Int,
    target: Array<Chromosome<V, F>>,
    crossinline cellLifecycle: suspend CellularLifecycle<V, F>.(chromosome: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>, random: Random) -> Chromosome<V, F>,
) {
    val chromosome = population[index]
    val chromosomeNeighboursIndices = neighboursIndicesCache[index]
    val chromosomeNeighbours = Array(chromosomeNeighboursIndices.size) { indexNeighbour ->
        population[chromosomeNeighboursIndices[indexNeighbour]]
    }
    val result = cellLifecycle(chromosome.clone(), chromosomeNeighbours, random)
    replaceWithElitism(elitism, target, index, chromosome, result)
}

private fun <V, F> replaceWithElitism(
    elitism: Boolean,
    population: Array<Chromosome<V, F>>,
    index: Int,
    old: Chromosome<V, F>,
    new: Chromosome<V, F>,
) {
    if (elitism) {
        if (new > old) population[index] = new
    } else {
        population[index] = new
    }
}
