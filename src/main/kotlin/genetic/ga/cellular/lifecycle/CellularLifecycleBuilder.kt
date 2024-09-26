package genetic.ga.cellular.lifecycle

import genetic.chromosome.Chromosome
import genetic.ga.cellular.type.CellularType
import genetic.ga.cellular.type.UpdatePolicy
import genetic.ga.core.lifecycle.size
import genetic.ga.core.parallelism.processor.execute
import genetic.ga.core.population.copyOf
import genetic.ga.core.population.get
import genetic.ga.core.population.set

fun <V, F> buildCellularLifecycle(
    parallelWorkersLimit: Int,
    beforeLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)? = null,
    afterLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)? = null,
    cellLifecycle: suspend CellularLifecycle<V, F>.(chromosome: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
): suspend CellularLifecycle<V, F>.() -> Unit = {
    beforeLifecycleIteration?.invoke(this)
    when (val cellularType = cellularType) {
        is CellularType.Synchronous -> {
            synchronousExecute(parallelWorkersLimit, cellLifecycle)
        }

        is CellularType.Asynchronous -> {
            asynchronousExecute(cellularType.updatePolicy, parallelWorkersLimit, cellLifecycle)
        }
    }
    afterLifecycleIteration?.invoke(this)
}

private suspend inline fun <V, F> CellularLifecycle<V, F>.synchronousExecute(
    parallelWorkersLimit: Int,
    crossinline cellLifecycle: suspend CellularLifecycle<V, F>.(chromosome: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) {
    val tempPopulation = population.copyOf()
    execute(parallelWorkersLimit) { iteration ->
        executeCellLifecycle(index = iteration, target = tempPopulation, cellLifecycle = cellLifecycle)
    }
    population.set(tempPopulation)
}

private suspend inline fun <V, F> CellularLifecycle<V, F>.asynchronousExecute(
    updatePolicy: UpdatePolicy,
    parallelWorkersLimit: Int,
    crossinline cellLifecycle: suspend CellularLifecycle<V, F>.(chromosome: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) = when (updatePolicy) {
    is UpdatePolicy.LineSweep -> {
        execute(parallelWorkersLimit) { iteration ->
            executeCellLifecycle(index = iteration, target = population.get(), cellLifecycle = cellLifecycle)
        }
    }

    is UpdatePolicy.FixedRandomSweep -> {
        val indicesShuffled = updatePolicy.cacheIndices(size)
        execute(parallelWorkersLimit) { iteration ->
            val index = indicesShuffled[iteration]
            executeCellLifecycle(index = index, target = population.get(), cellLifecycle = cellLifecycle)
        }
    }

    is UpdatePolicy.NewRandomSweep -> {
        val indicesShuffled = IntArray(size) { it }.apply { shuffle(random) }
        execute(parallelWorkersLimit) { iteration ->
            val index = indicesShuffled[iteration]
            executeCellLifecycle(index = index, target = population.get(), cellLifecycle = cellLifecycle)
        }
    }

    is UpdatePolicy.UniformChoice -> {
        execute(parallelWorkersLimit) { _ ->
            val index = random.nextInt(size)
            executeCellLifecycle(index = index, target = population.get(), cellLifecycle = cellLifecycle)
        }
    }
}

private suspend inline fun <V, F> CellularLifecycle<V, F>.execute(
    parallelWorkersLimit: Int,
    crossinline action: suspend (iteration: Int) -> Unit,
) {
    execute(
        parallelWorkersLimit = parallelWorkersLimit,
        startIteration = 0,
        endIteration = size,
        action = action,
    )
}

private suspend inline fun <V, F> CellularLifecycle<V, F>.executeCellLifecycle(
    index: Int,
    target: Array<Chromosome<V, F>>,
    crossinline cellLifecycle: suspend CellularLifecycle<V, F>.(chromosome: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) {
    val chromosome = population[index]
    val chromosomeNeighboursIndices = neighboursIndicesCache[index]
    val chromosomeNeighbours = Array(chromosomeNeighboursIndices.size) { indexNeighbour ->
        population[chromosomeNeighboursIndices[indexNeighbour]]
    }
    val result = cellLifecycle(chromosome.clone(), chromosomeNeighbours)
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
