package genetic.ga.panmictic.operators.selection

import genetic.chromosome.Chromosome
import genetic.ga.core.operators.selection.roulette.*
import genetic.ga.core.population.get
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

@JvmName("selRouletteFitInt")
suspend fun <V> PanmicticLifecycle<V, Int>.selRoulette(
    parallelismLimit: Int = parallelismConfig.workersCount,
) {
    val totalFitness = population.get().fold(0L) { acc: Long, chromosome: Chromosome<V, Int> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(parallelismLimit) { source, random -> selectionRouletteFitInt(source, totalFitness, random) }
}

@JvmName("selRouletteFitLong")
suspend fun <V> PanmicticLifecycle<V, Long>.selRoulette(
    parallelismLimit: Int = parallelismConfig.workersCount,
) {
    val totalFitness = population.get().fold(0L) { acc: Long, chromosome: Chromosome<V, Long> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(parallelismLimit) { source, random -> selectionRouletteFitLong(source, totalFitness, random) }
}

@JvmName("selRouletteFitShort")
suspend fun <V> PanmicticLifecycle<V, Short>.selRoulette(
    parallelismLimit: Int = parallelismConfig.workersCount,
) {
    val totalFitness = population.get().fold(0L) { acc: Long, chromosome: Chromosome<V, Short> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(parallelismLimit) { source, random -> selectionRouletteFitShort(source, totalFitness, random) }
}

@JvmName("selRouletteFitByte")
suspend fun <V> PanmicticLifecycle<V, Byte>.selRoulette(
    parallelismLimit: Int = parallelismConfig.workersCount,
) {
    val totalFitness = population.get().fold(0L) { acc: Long, chromosome: Chromosome<V, Byte> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(parallelismLimit) { source, random -> selectionRouletteFitByte(source, totalFitness, random) }
}

@JvmName("selRouletteFitDouble")
suspend fun <V> PanmicticLifecycle<V, Double>.selRoulette(
    parallelismLimit: Int = parallelismConfig.workersCount,
) {
    val totalFitness = population.get().fold(0.0) { acc: Double, chromosome: Chromosome<V, Double> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(parallelismLimit) { source, random -> selectionRouletteFitDouble(source, totalFitness, random) }
}

@JvmName("selRouletteFitFloat")
suspend fun <V> PanmicticLifecycle<V, Float>.selRoulette(
    parallelismLimit: Int = parallelismConfig.workersCount,
) {
    val totalFitness = population.get().fold(0f) { acc: Float, chromosome: Chromosome<V, Float> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selection(parallelismLimit) { source, random -> selectionRouletteFitFloat(source, totalFitness, random) }
}
