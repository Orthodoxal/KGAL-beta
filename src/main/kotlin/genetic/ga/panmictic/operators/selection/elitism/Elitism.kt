package genetic.ga.panmictic.operators.selection.elitism

import genetic.chromosome.Chromosome
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import java.util.*

private val comparator = Comparator<Pair<Int, Chromosome<*, *>>> { p1, p2 -> compareValues(p1.second, p2.second) }

fun <V, F> elitChromosomeComparable(
    source: Array<Chromosome<V, F>>,
    count: Int,
    priority: PriorityQueue<Pair<Int, Chromosome<V, F>>>,
): IntArray {
    source.forEachIndexed { index, chromosome ->
        priority.offer(index to chromosome)
        if (priority.size > count) {
            priority.poll()
        }
    }
    return IntArray(count) { priority.poll().first }
}

fun <V, F> elitChromosomeIndices(
    source: Array<Chromosome<V, F>>,
    count: Int,
): IntArray {
    if (count <= 0) error("Count must be more than zero")
    if (source.size < count) error("Count must be less or equal to source size")
    val priority = PriorityQueue<Pair<Int, Chromosome<V, F>>>(count + 1, comparator)
    val r = elitChromosomeComparable(source, count, priority)
    return r
}

fun <V, F> SimpleClusterLifecycle<V, F>.moveToStartElitChromosomes() {
    val elitIndices = elitChromosomeIndices(population, elitism)
    val elitChromosomes = Array(elitism) { index -> population[elitIndices[elitism - 1 - index]] }
    val elitOld = population.copyOf(elitism)
    val replaceIndicesList = mutableListOf<Int>()
    val replaceIndicesOldElitList = mutableListOf<Int>()
    elitIndices.forEachIndexed { index, elitIndex ->
        if (elitIndex >= elitism) {
            replaceIndicesList.add(elitIndex)
        }
        if (index !in elitIndices) {
            replaceIndicesOldElitList.add(index)
        }
        population[index] = elitChromosomes[index]
    }
    replaceIndicesList.forEachIndexed { index, replaceIndex ->
        population[replaceIndex] = elitOld[replaceIndicesOldElitList[index]]!!
    }
}
