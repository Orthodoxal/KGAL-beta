package genetic.ga.cellular.operators.selection

import genetic.chromosome.Chromosome
import genetic.ga.base_operators.selection.roulette.*
import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle

fun <V> SimpleClusterCellLifecycle<V, Int>.selectionRouletteFitInt(
    cellularGABuilder: CellularGABuilder<V, Int>,
) = selection(cellularGABuilder) { source ->
    val totalFitness = source.fold(0L) { acc: Long, chromosome: Chromosome<V, Int> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selectionRouletteFitInt(source, totalFitness, random)
}

fun <V> SimpleClusterCellLifecycle<V, Long>.selectionRouletteFitLong(
    cellularGABuilder: CellularGABuilder<V, Long>,
) = selection(cellularGABuilder) { source ->
    val totalFitness = source.fold(0L) { acc: Long, chromosome: Chromosome<V, Long> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selectionRouletteFitLong(source, totalFitness, random)
}

fun <V> SimpleClusterCellLifecycle<V, Short>.selectionRouletteFitShort(
    cellularGABuilder: CellularGABuilder<V, Short>,
) = selection(cellularGABuilder) { source ->
    val totalFitness = source.fold(0L) { acc: Long, chromosome: Chromosome<V, Short> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selectionRouletteFitShort(source, totalFitness, random)
}

fun <V> SimpleClusterCellLifecycle<V, Byte>.selectionRouletteFitByte(
    cellularGABuilder: CellularGABuilder<V, Byte>,
) = selection(cellularGABuilder) { source ->
    val totalFitness = source.fold(0L) { acc: Long, chromosome: Chromosome<V, Byte> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selectionRouletteFitByte(source, totalFitness, random)
}

fun <V> SimpleClusterCellLifecycle<V, Double>.selectionRouletteFitDouble(
    cellularGABuilder: CellularGABuilder<V, Double>,
) = selection(cellularGABuilder) { source ->
    val totalFitness = source.fold(0.0) { acc: Double, chromosome: Chromosome<V, Double> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selectionRouletteFitDouble(source, totalFitness, random)
}

fun <V> SimpleClusterCellLifecycle<V, Float>.selectionRouletteFitFloat(
    cellularGABuilder: CellularGABuilder<V, Float>,
) = selection(cellularGABuilder) { source ->
    val totalFitness = source.fold(0f) { acc: Float, chromosome: Chromosome<V, Float> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    selectionRouletteFitFloat(source, totalFitness, random)
}
