package genetic.clusters.cellular.operators.selection

import genetic.chromosome.Chromosome
import genetic.clusters.cellular.lifecycle.CellLifecycle

fun <V> CellLifecycle<V, Int>.selRouletteFitInt() = selection { source ->
    val totalFitness = source.fold(0L) { acc: Long, chromosome: Chromosome<V, Int> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    genetic.clusters.base.operators.selection.roulette.selectionRouletteFitInt(source, totalFitness, random)
}

fun <V> CellLifecycle<V, Long>.selRouletteFitLong() = selection { source ->
    val totalFitness = source.fold(0L) { acc: Long, chromosome: Chromosome<V, Long> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    genetic.clusters.base.operators.selection.roulette.selectionRouletteFitLong(source, totalFitness, random)
}

fun <V> CellLifecycle<V, Short>.selRouletteFitShort() = selection { source ->
    val totalFitness = source.fold(0L) { acc: Long, chromosome: Chromosome<V, Short> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    genetic.clusters.base.operators.selection.roulette.selectionRouletteFitShort(source, totalFitness, random)
}

fun <V> CellLifecycle<V, Byte>.selRouletteFitByte() = selection { source ->
    val totalFitness = source.fold(0L) { acc: Long, chromosome: Chromosome<V, Byte> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    genetic.clusters.base.operators.selection.roulette.selectionRouletteFitByte(source, totalFitness, random)
}

fun <V> CellLifecycle<V, Double>.selRouletteFitDouble() = selection { source ->
    val totalFitness = source.fold(0.0) { acc: Double, chromosome: Chromosome<V, Double> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    genetic.clusters.base.operators.selection.roulette.selectionRouletteFitDouble(source, totalFitness, random)
}

fun <V> CellLifecycle<V, Float>.selRouletteFitFloat() = selection { source ->
    val totalFitness = source.fold(0f) { acc: Float, chromosome: Chromosome<V, Float> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    genetic.clusters.base.operators.selection.roulette.selectionRouletteFitFloat(source, totalFitness, random)
}
