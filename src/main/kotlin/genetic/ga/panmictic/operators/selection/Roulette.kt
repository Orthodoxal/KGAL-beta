package genetic.ga.panmictic.operators.selection

import genetic.chromosome.Chromosome
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.base_operators.selection.roulette.selectionRouletteFitByte
import genetic.ga.base_operators.selection.roulette.selectionRouletteFitShort
import genetic.ga.base_operators.selection.roulette.selectionRouletteFitLong
import genetic.ga.base_operators.selection.roulette.selectionRouletteFitInt
import genetic.ga.base_operators.selection.roulette.selectionRouletteFitFloat
import genetic.ga.base_operators.selection.roulette.selectionRouletteFitDouble
import genetic.ga.panmictic.builder.PanmicticGABuilder

suspend fun <V> SimpleClusterLifecycle<V, Int>.selectionRouletteFitInt(
    panmicticGABuilder: PanmicticGABuilder<V, Int>,
    onlySingleRun: Boolean = false,
) {
    val totalFitness = population.fold(0L) { acc: Long, chromosome: Chromosome<V, Int> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(panmicticGABuilder, onlySingleRun) { source -> selectionRouletteFitInt(source, totalFitness, random) }
}

suspend fun <V> SimpleClusterLifecycle<V, Long>.selectionRouletteFitLong(
    panmicticGABuilder: PanmicticGABuilder<V, Long>,
    onlySingleRun: Boolean = false,
) {
    val totalFitness = population.fold(0L) { acc: Long, chromosome: Chromosome<V, Long> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(panmicticGABuilder, onlySingleRun) { source -> selectionRouletteFitLong(source, totalFitness, random) }
}

suspend fun <V> SimpleClusterLifecycle<V, Short>.selectionRouletteFitShort(
    panmicticGABuilder: PanmicticGABuilder<V, Short>,
    onlySingleRun: Boolean = false,
) {
    val totalFitness = population.fold(0L) { acc: Long, chromosome: Chromosome<V, Short> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(panmicticGABuilder, onlySingleRun) { source -> selectionRouletteFitShort(source, totalFitness, random) }
}

suspend fun <V> SimpleClusterLifecycle<V, Byte>.selectionRouletteFitByte(
    panmicticGABuilder: PanmicticGABuilder<V, Byte>,
    onlySingleRun: Boolean = false,
) {
    val totalFitness = population.fold(0L) { acc: Long, chromosome: Chromosome<V, Byte> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(panmicticGABuilder, onlySingleRun) { source -> selectionRouletteFitByte(source, totalFitness, random) }
}

suspend fun <V> SimpleClusterLifecycle<V, Double>.selectionRouletteFitDouble(
    panmicticGABuilder: PanmicticGABuilder<V, Double>,
    onlySingleRun: Boolean = false,
) {
    val totalFitness = population.fold(0.0) { acc: Double, chromosome: Chromosome<V, Double> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(panmicticGABuilder, onlySingleRun) { source -> selectionRouletteFitDouble(source, totalFitness, random) }
}

suspend fun <V> SimpleClusterLifecycle<V, Float>.selectionRouletteFitFloat(
    panmicticGABuilder: PanmicticGABuilder<V, Float>,
    onlySingleRun: Boolean = false,
) {
    val totalFitness = population.fold(0f) { acc: Float, chromosome: Chromosome<V, Float> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(panmicticGABuilder, onlySingleRun) { source -> selectionRouletteFitFloat(source, totalFitness, random) }
}
