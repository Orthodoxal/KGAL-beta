package genetic.ga.cellular.operators.selection

import genetic.chromosome.Chromosome
import genetic.ga.cellular.lifecycle.CellLifecycle

@JvmName("selRouletteFitInt")
fun <V> CellLifecycle<V, Int>.selRoulette() = selection { source ->
    val totalFitness = source.fold(0L) { acc: Long, chromosome: Chromosome<V, Int> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    genetic.ga.core.operators.selection.roulette.selectionRouletteFitInt(source, totalFitness, random)
}

@JvmName("selRouletteFitLong")
fun <V> CellLifecycle<V, Long>.selRoulette() = selection { source ->
    val totalFitness = source.fold(0L) { acc: Long, chromosome: Chromosome<V, Long> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    genetic.ga.core.operators.selection.roulette.selectionRouletteFitLong(source, totalFitness, random)
}

@JvmName("selRouletteFitShort")
fun <V> CellLifecycle<V, Short>.selRoulette() = selection { source ->
    val totalFitness = source.fold(0L) { acc: Long, chromosome: Chromosome<V, Short> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    genetic.ga.core.operators.selection.roulette.selectionRouletteFitShort(source, totalFitness, random)
}

@JvmName("selRouletteFitByte")
fun <V> CellLifecycle<V, Byte>.selRoulette() = selection { source ->
    val totalFitness = source.fold(0L) { acc: Long, chromosome: Chromosome<V, Byte> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    genetic.ga.core.operators.selection.roulette.selectionRouletteFitByte(source, totalFitness, random)
}

@JvmName("selRouletteFitDouble")
fun <V> CellLifecycle<V, Double>.selRoulette() = selection { source ->
    val totalFitness = source.fold(0.0) { acc: Double, chromosome: Chromosome<V, Double> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    genetic.ga.core.operators.selection.roulette.selectionRouletteFitDouble(source, totalFitness, random)
}

@JvmName("selRouletteFitFloat")
fun <V> CellLifecycle<V, Float>.selRoulette() = selection { source ->
    val totalFitness = source.fold(0f) { acc: Float, chromosome: Chromosome<V, Float> ->
        acc + (chromosome.fitness ?: error("Fitness is null"))
    }
    genetic.ga.core.operators.selection.roulette.selectionRouletteFitFloat(source, totalFitness, random)
}
