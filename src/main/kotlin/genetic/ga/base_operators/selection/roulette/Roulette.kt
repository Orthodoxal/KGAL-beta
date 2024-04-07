package genetic.ga.base_operators.selection.roulette

import genetic.chromosome.Chromosome
import kotlin.random.Random

internal inline fun <V, N> roulette(
    source: Array<Chromosome<V, N>>,
    totalFitness: Long,
    random: Random,
    summator: (sum: Long, fitness: N) -> Long,
): Chromosome<V, N> {
    val randomSum = random.nextLong(totalFitness)
    var sum = 0L
    for (chromosome in source) {
        sum = summator(sum, chromosome.fitness ?: error("Fitness is null"))
        if (sum >= randomSum) {
            return chromosome.clone()
        }
    }
    error("Selection roulette critical exception")
}

internal fun <V> selectionRouletteFitDouble(
    source: Array<Chromosome<V, Double>>,
    totalFitness: Double,
    random: Random,
): Chromosome<V, Double> {
    val randomSum = random.nextDouble(totalFitness)
    var sum = 0.0
    for (chromosome in source) {
        sum += chromosome.fitness ?: error("Fitness is null")
        if (sum >= randomSum) {
            return chromosome.clone()
        }
    }
    error("selectionRoulette critical exception")
}


internal fun <V> selectionRouletteFitFloat(
    source: Array<Chromosome<V, Float>>,
    totalFitness: Float,
    random: Random,
): Chromosome<V, Float> {
    val randomSum = random.nextFloat() * totalFitness
    var sum = 0f
    for (chromosome in source) {
        sum += chromosome.fitness ?: error("Fitness is null")
        if (sum >= randomSum) {
            return chromosome.clone()
        }
    }
    error("selectionRoulette critical exception")
}

internal fun <V> selectionRouletteFitInt(
    source: Array<Chromosome<V, Int>>,
    totalFitness: Long,
    random: Random,
): Chromosome<V, Int> = roulette(source, totalFitness, random) { sum, fitness -> sum + fitness }

internal fun <V> selectionRouletteFitLong(
    source: Array<Chromosome<V, Long>>,
    totalFitness: Long,
    random: Random,
): Chromosome<V, Long> = roulette(source, totalFitness, random) { sum, fitness -> sum + fitness }

internal fun <V> selectionRouletteFitShort(
    source: Array<Chromosome<V, Short>>,
    totalFitness: Long,
    random: Random,
): Chromosome<V, Short> = roulette(source, totalFitness, random) { sum, fitness -> sum + fitness }

internal fun <V> selectionRouletteFitByte(
    source: Array<Chromosome<V, Byte>>,
    totalFitness: Long,
    random: Random,
): Chromosome<V, Byte> = roulette(source, totalFitness, random) { sum, fitness -> sum + fitness }
