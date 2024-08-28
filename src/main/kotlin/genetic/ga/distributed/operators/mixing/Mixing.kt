package genetic.ga.distributed.operators.mixing

import genetic.ga.distributed.lifecycle.DistributedLifecycle
import genetic.ga.core.population.get
import genetic.ga.core.population.set
import genetic.utils.indicesByRandom
import genetic.utils.randomWithIndices

internal fun <V, F> DistributedLifecycle<V, F>.minCountByPercent(percent: Double) =
    gas.fold(Int.MAX_VALUE) { acc, cluster ->
        val count = (cluster.population.maxSize * percent).toInt()
        if (count < acc) count else acc
    }

fun <V, F> DistributedLifecycle<V, F>.mixingRandom(percent: Double) {
    val count = minCountByPercent(percent)
    var (chromosomes, indices) = gas.last().population.get().randomWithIndices(count, random)
    for (clusterIndex in gas.lastIndex - 1 downTo 0) {
        val indicesNext = gas[clusterIndex].population.get().indicesByRandom(count, random)
        indices.forEachIndexed { index, i ->
            gas[clusterIndex + 1].population[i] = gas[clusterIndex].population[indicesNext[index]]
            indices = indicesNext
        }
    }
    indices.forEachIndexed { index, i ->
        gas[0].population[i] = chromosomes[index]
    }
}
