package genetic.ga.core.operators.selection.comparable

import genetic.chromosome.Chromosome
import java.util.*

fun <V, F> selectionComparable(
    source: Array<Chromosome<V, F>>,
    count: Int,
    priority: PriorityQueue<Chromosome<V, F>>,
): Array<Chromosome<V, F>> {
    for (chromosome in source) {
        priority.offer(chromosome)
        if (priority.size > count) {
            priority.poll()
        }
    }
    return Array(count) { priority.poll().clone() }
}

fun <V, F> selectionBest(
    source: Array<Chromosome<V, F>>,
    count: Int,
): Array<Chromosome<V, F>> {
    if (count <= 0) error("Count must be more than zero")
    if (source.size < count) error("Count must be less or equal to source size")
    val priority = PriorityQueue<Chromosome<V, F>>(count + 1)
    return selectionComparable(source, count, priority)
}

fun <V, F> selectionWorst(
    source: Array<Chromosome<V, F>>,
    count: Int,
): Array<Chromosome<V, F>> {
    if (count <= 0) error("Count must be more than zero")
    if (source.size < count) error("Count must be less or equal to source size")
    val priority = PriorityQueue<Chromosome<V, F>>(count + 1, Collections.reverseOrder())
    return selectionComparable(source, count, priority)
}
