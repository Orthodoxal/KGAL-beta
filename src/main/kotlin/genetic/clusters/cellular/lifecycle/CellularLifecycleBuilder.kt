package genetic.clusters.cellular.lifecycle

import genetic.chromosome.Chromosome
import genetic.clusters.base.lifecycle.isSingleRun
import genetic.clusters.base.population.Population
import genetic.clusters.cellular.type.CellularType
import genetic.clusters.cellular.type.UpdatePolicy
import genetic.utils.clusters.*
import kotlin.random.Random

fun <V, F> buildCellularLifecycle(
    beforeLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)? = null,
    afterLifecycleIteration: (suspend CellularLifecycle<V, F>.() -> Unit)? = null,
    cellLifecycle: suspend CellularLifecycle<V, F>.(chromosome: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
): suspend CellularLifecycle<V, F>.() -> Unit = {
    beforeLifecycleIteration?.invoke(this)
    when (val cellularType = cellularType) {
        is CellularType.Synchronous -> {
            if (isSingleRun) {
                singleRunSynchronous { chromosome, neighbours -> cellLifecycle(chromosome, neighbours) }
            } else {
                multiRunSynchronous { chromosome, neighbours -> cellLifecycle(chromosome, neighbours) }
            }
        }

        is CellularType.Asynchronous -> {
            val updatePolicy = cellularType.updatePolicy
            if (isSingleRun) {
                singleRunAsynchronous(updatePolicy) { chromosome, neighbours -> cellLifecycle(chromosome, neighbours) }
            } else {
                multiRunAsynchronous(updatePolicy) { chromosome, neighbours -> cellLifecycle(chromosome, neighbours) }
            }
        }
    }
    afterLifecycleIteration?.invoke(this)
}

private inline fun <V, F> CellularLifecycle<V, F>.singleRunSynchronous(
    lifecycleExecutor: CellularLifecycle<V, F>.(chromosome: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) {
    val tempPopulation = population.copyOf()

    population.forEachIndexed { index, chromosome ->
        val chromosomeNeighboursIndices = neighboursIndicesCache[index]
        val chromosomeNeighbours = Array(chromosomeNeighboursIndices.size) { indexNeighbour ->
            population[chromosomeNeighboursIndices[indexNeighbour]]
        }
        val result = lifecycleExecutor(chromosome.clone(), chromosomeNeighbours)
        replaceWithElitism(elitism, tempPopulation, index, chromosome, result)
        if (stopSignal) return@forEachIndexed
    }

    population.set(tempPopulation)
}

private suspend inline fun <V, F> CellularLifecycle<V, F>.multiRunSynchronous(
    crossinline lifecycleExecutor: suspend (chromosome: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) {
    val tempPopulation = population.copyOf()

    runWithExtraDispatchersIterative(0, popCurrentSize) { iteration ->
        val chromosomeNeighboursIndices = neighboursIndicesCache[iteration]
        val chromosomeNeighbours = Array(chromosomeNeighboursIndices.size) { indexNeighbour ->
            population[chromosomeNeighboursIndices[indexNeighbour]]
        }
        val result = lifecycleExecutor(population[iteration].clone(), chromosomeNeighbours)
        replaceWithElitism(elitism, tempPopulation, iteration, tempPopulation[iteration], result)
    }

    population.set(tempPopulation)
}

private inline fun <V, F> CellularLifecycle<V, F>.singleRunAsynchronous(
    updatePolicy: UpdatePolicy,
    lifecycleExecutor: (chromosome: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) = when (updatePolicy) {
    is UpdatePolicy.LineSweep -> {
        population.forEachIndexed { index, chromosome ->
            val chromosomeNeighboursIndices = neighboursIndicesCache[index]
            val chromosomeNeighbours = Array(chromosomeNeighboursIndices.size) { indexNeighbour ->
                population[chromosomeNeighboursIndices[indexNeighbour]]
            }
            val result = lifecycleExecutor(chromosome.clone(), chromosomeNeighbours)
            replaceWithElitism(elitism, population, index, chromosome, result)
            if (stopSignal) return@forEachIndexed
        }
    }

    is UpdatePolicy.FixedRandomSweep -> {
        val indicesShuffled = IntArray(popCurrentSize) { it }.apply { shuffle(Random(randomSeed)) }
        population.forEachIndexed { index, _ ->
            val indexR = indicesShuffled[index]
            val chromosomeNeighboursIndices = neighboursIndicesCache[indexR]
            val chromosomeNeighbours = Array(chromosomeNeighboursIndices.size) { indexNeighbour ->
                population[chromosomeNeighboursIndices[indexNeighbour]]
            }
            val result = lifecycleExecutor(population[indexR].clone(), chromosomeNeighbours)
            replaceWithElitism(elitism, population, indexR, population[indexR], result)
            if (stopSignal) return@forEachIndexed
        }
    }

    is UpdatePolicy.NewRandomSweep -> {
        val indicesShuffled = IntArray(popCurrentSize) { it }.apply { shuffle(random) }
        population.forEachIndexed { index, _ ->
            val indexR = indicesShuffled[index]
            val chromosomeNeighboursIndices = neighboursIndicesCache[indexR]
            val chromosomeNeighbours = Array(chromosomeNeighboursIndices.size) { indexNeighbour ->
                population[chromosomeNeighboursIndices[indexNeighbour]]
            }
            val result = lifecycleExecutor(population[indexR].clone(), chromosomeNeighbours)
            replaceWithElitism(elitism, population, indexR, population[indexR], result)
            if (stopSignal) return@forEachIndexed
        }
    }

    is UpdatePolicy.UniformChoice -> {
        population.forEachIndexed { _, _ ->
            val indexR = random.nextInt(popCurrentSize)
            val chromosomeNeighboursIndices = neighboursIndicesCache[indexR]
            val chromosomeNeighbours = Array(chromosomeNeighboursIndices.size) { indexNeighbour ->
                population[chromosomeNeighboursIndices[indexNeighbour]]
            }
            val result = lifecycleExecutor(population[indexR].clone(), chromosomeNeighbours)
            replaceWithElitism(elitism, population, indexR, population[indexR], result)
            if (stopSignal) return@forEachIndexed
        }
    }
}

private suspend inline fun <V, F> CellularLifecycle<V, F>.multiRunAsynchronous(
    updatePolicy: UpdatePolicy,
    crossinline lifecycleExecutor: suspend (chromosome: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
) = when (updatePolicy) {
    is UpdatePolicy.LineSweep -> {
        runWithExtraDispatchersIterative(0, popCurrentSize) { iteration ->
            val chromosomeNeighboursIndices = neighboursIndicesCache[iteration]
            val chromosomeNeighbours = Array(chromosomeNeighboursIndices.size) { indexNeighbour ->
                population[chromosomeNeighboursIndices[indexNeighbour]]
            }
            val result = lifecycleExecutor(population[iteration].clone(), chromosomeNeighbours)
            replaceWithElitism(elitism, population, iteration, population[iteration], result)
        }
    }

    is UpdatePolicy.FixedRandomSweep -> {
        val indicesShuffled = IntArray(popCurrentSize) { it }.apply { shuffle(Random(randomSeed)) }
        runWithExtraDispatchersIterative(0, popCurrentSize) { iteration ->
            val index = indicesShuffled[iteration]
            val chromosomeNeighboursIndices = neighboursIndicesCache[index]
            val chromosomeNeighbours = Array(chromosomeNeighboursIndices.size) { indexNeighbour ->
                population[chromosomeNeighboursIndices[indexNeighbour]]
            }
            val result = lifecycleExecutor(population[index].clone(), chromosomeNeighbours)
            replaceWithElitism(elitism, population, index, population[index], result)
        }
    }

    is UpdatePolicy.NewRandomSweep -> {
        val indicesShuffled = IntArray(popCurrentSize) { it }.apply { shuffle(random) }
        runWithExtraDispatchersIterative(0, popCurrentSize) { iteration ->
            val index = indicesShuffled[iteration]
            val chromosomeNeighboursIndices = neighboursIndicesCache[index]
            val chromosomeNeighbours = Array(chromosomeNeighboursIndices.size) { indexNeighbour ->
                population[chromosomeNeighboursIndices[indexNeighbour]]
            }
            val result = lifecycleExecutor(population[index].clone(), chromosomeNeighbours)
            replaceWithElitism(elitism, population, index, population[index], result)
        }
    }

    is UpdatePolicy.UniformChoice -> {
        runWithExtraDispatchersIterative(0, popCurrentSize) { _ ->
            val index = random.nextInt(popCurrentSize)
            val chromosomeNeighboursIndices = neighboursIndicesCache[index]
            val chromosomeNeighbours = Array(chromosomeNeighboursIndices.size) { indexNeighbour ->
                population[chromosomeNeighboursIndices[indexNeighbour]]
            }
            val result = lifecycleExecutor(population[index].clone(), chromosomeNeighbours)
            replaceWithElitism(elitism, population, index, population[index], result)
        }
    }
}

private fun <V, F> replaceWithElitism(
    elitism: Boolean,
    population: Population<V, F>,
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
