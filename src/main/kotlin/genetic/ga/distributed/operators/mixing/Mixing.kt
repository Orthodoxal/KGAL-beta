package genetic.ga.distributed.operators.mixing

import genetic.ga.distributed.lifecycle.DistributedGALifecycle
import genetic.utils.indicesByRandom
import genetic.utils.randomWithIndices

internal fun <V, F> DistributedGALifecycle<V, F>.minCountByPercent(percent: Double) =
    clusters.fold(Int.MAX_VALUE) { acc, cluster ->
        val count = (cluster.populationSize * percent).toInt()
        if (count < acc) count else acc
    }

fun <V, F> DistributedGALifecycle<V, F>.mixingRandom(percent: Double) {
    val count = minCountByPercent(percent)
    var (chromosomes, indices) = clusters.last().population.randomWithIndices(count, random)
    for (clusterIndex in clusters.lastIndex - 1 downTo 0) {
        val indicesNext = clusters[clusterIndex].population.indicesByRandom(count, random)
        indices.forEachIndexed { index, i ->
            clusters[clusterIndex + 1].population[i] = clusters[clusterIndex].population[indicesNext[index]]
            indices = indicesNext
        }
    }
    indices.forEachIndexed { index, i ->
        clusters[0].population[i] = chromosomes[index]
    }
}
