package genetic.clusters.panmictic.operators.selection

import genetic.chromosome.Chromosome
import genetic.clusters.base.operators.selection.roulette.*
import genetic.clusters.panmictic.PanmicticLifecycle
import genetic.utils.clusters.get

@JvmName("selRouletteFitInt")
suspend fun <V> PanmicticLifecycle<V, Int>.selRoulette(
    onlySingleRun: Boolean = false,
) {
    val totalFitness = population.get().fold(0L) { acc: Long, chromosome: Chromosome<V, Int> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(onlySingleRun) { source -> selectionRouletteFitInt(source, totalFitness, random) }
}

@JvmName("selRouletteFitLong")
suspend fun <V> PanmicticLifecycle<V, Long>.selRoulette(
    onlySingleRun: Boolean = false,
) {
    val totalFitness = population.get().fold(0L) { acc: Long, chromosome: Chromosome<V, Long> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(onlySingleRun) { source -> selectionRouletteFitLong(source, totalFitness, random) }
}

@JvmName("selRouletteFitShort")
suspend fun <V> PanmicticLifecycle<V, Short>.selRoulette(
    onlySingleRun: Boolean = false,
) {
    val totalFitness = population.get().fold(0L) { acc: Long, chromosome: Chromosome<V, Short> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(onlySingleRun) { source -> selectionRouletteFitShort(source, totalFitness, random) }
}

@JvmName("selRouletteFitByte")
suspend fun <V> PanmicticLifecycle<V, Byte>.selRoulette(
    onlySingleRun: Boolean = false,
) {
    val totalFitness = population.get().fold(0L) { acc: Long, chromosome: Chromosome<V, Byte> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(onlySingleRun) { source -> selectionRouletteFitByte(source, totalFitness, random) }
}

@JvmName("selRouletteFitDouble")
suspend fun <V> PanmicticLifecycle<V, Double>.selRoulette(
    onlySingleRun: Boolean = false,
) {
    val totalFitness = population.get().fold(0.0) { acc: Double, chromosome: Chromosome<V, Double> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(onlySingleRun) { source -> selectionRouletteFitDouble(source, totalFitness, random) }
}

@JvmName("selRouletteFitFloat")
suspend fun <V> PanmicticLifecycle<V, Float>.selRoulette(
    onlySingleRun: Boolean = false,
) {
    val totalFitness = population.get().fold(0f) { acc: Float, chromosome: Chromosome<V, Float> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(onlySingleRun) { source -> selectionRouletteFitFloat(source, totalFitness, random) }
}
