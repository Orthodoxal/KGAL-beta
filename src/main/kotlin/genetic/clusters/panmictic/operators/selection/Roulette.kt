package genetic.clusters.panmictic.operators.selection

import genetic.chromosome.Chromosome
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.clusters.base.operators.selection.roulette.selectionRouletteFitByte
import genetic.clusters.base.operators.selection.roulette.selectionRouletteFitShort
import genetic.clusters.base.operators.selection.roulette.selectionRouletteFitLong
import genetic.clusters.base.operators.selection.roulette.selectionRouletteFitInt
import genetic.clusters.base.operators.selection.roulette.selectionRouletteFitFloat
import genetic.clusters.base.operators.selection.roulette.selectionRouletteFitDouble
import genetic.clusters.panmictic.PanmicticLifecycle
import genetic.ga.panmictic.builder.PanmicticGABuilder
import genetic.utils.clusters.get

suspend fun <V> PanmicticLifecycle<V, Int>.selRouletteFitInt(
    onlySingleRun: Boolean = false,
) {
    val totalFitness = population.get().fold(0L) { acc: Long, chromosome: Chromosome<V, Int> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(onlySingleRun) { source -> selectionRouletteFitInt(source, totalFitness, random) }
}

suspend fun <V> PanmicticLifecycle<V, Long>.selRouletteFitLong(
    onlySingleRun: Boolean = false,
) {
    val totalFitness = population.get().fold(0L) { acc: Long, chromosome: Chromosome<V, Long> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(onlySingleRun) { source -> selectionRouletteFitLong(source, totalFitness, random) }
}

suspend fun <V> PanmicticLifecycle<V, Short>.selRouletteFitShort(
    onlySingleRun: Boolean = false,
) {
    val totalFitness = population.get().fold(0L) { acc: Long, chromosome: Chromosome<V, Short> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(onlySingleRun) { source -> selectionRouletteFitShort(source, totalFitness, random) }
}

suspend fun <V> PanmicticLifecycle<V, Byte>.selRouletteFitByte(
    onlySingleRun: Boolean = false,
) {
    val totalFitness = population.get().fold(0L) { acc: Long, chromosome: Chromosome<V, Byte> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(onlySingleRun) { source -> selectionRouletteFitByte(source, totalFitness, random) }
}

suspend fun <V> PanmicticLifecycle<V, Double>.selRouletteFitDouble(
    onlySingleRun: Boolean = false,
) {
    val totalFitness = population.get().fold(0.0) { acc: Double, chromosome: Chromosome<V, Double> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(onlySingleRun) { source -> selectionRouletteFitDouble(source, totalFitness, random) }
}

suspend fun <V> PanmicticLifecycle<V, Float>.selRouletteFitFloat(
    onlySingleRun: Boolean = false,
) {
    val totalFitness = population.get().fold(0f) { acc: Float, chromosome: Chromosome<V, Float> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(onlySingleRun) { source -> selectionRouletteFitFloat(source, totalFitness, random) }
}
