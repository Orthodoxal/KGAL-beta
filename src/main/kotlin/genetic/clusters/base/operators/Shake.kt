package genetic.clusters.base.operators

import genetic.clusters.base.lifecycle.ClusterLifecycle
import genetic.clusters.base.population.Population
import genetic.utils.clusters.*
import genetic.utils.moreOrEquals
import kotlin.collections.set
import kotlin.random.Random

const val SHAKE_BY_REPEATABLE_MAX_FITNESS = "ShakeByRepeatableMaxFitness"
const val SHAKE_BY_REPEATABLE_EQUALS_ITERATION = "ShakeByRepeatableEqualsIteration"

inline fun <V, F, L : ClusterLifecycle> L.shakeBy(
    percent: Double,
    random: Random,
    population: Population<V, F>,
    predicate: (L) -> Boolean,
) {
    if (predicate(this)) {
        if (percent moreOrEquals 1.0) {
            for (i in population.indices) population[i] = population.factory(i, random)
        } else {
            val count = (percent * population.currentSize).toInt()
            for (i in 1..count) population[population.lastIndex] = population.factory(i, random)
        }
    }
}

inline fun <V, reified F, L : ClusterLifecycle> L.shakeByRepeatableForMax(
    percent: Double,
    maxEqualsIteration: Int,
    random: Random,
    population: Population<V, F>,
    equalsPredicate: (max: F, prevMax: F) -> Boolean,
) = shakeBy(percent, random, population) {
    val maxFitness = population.max().fitness!!
    val storeMaxFitness = store[SHAKE_BY_REPEATABLE_MAX_FITNESS] as? F
    var equalsIteration = store[SHAKE_BY_REPEATABLE_EQUALS_ITERATION] as? Int

    when {
        storeMaxFitness == null || equalsIteration == null -> {
            store[SHAKE_BY_REPEATABLE_MAX_FITNESS] = maxFitness
            store[SHAKE_BY_REPEATABLE_EQUALS_ITERATION] = 1
            false
        }

        else -> {
            if (equalsPredicate(maxFitness, storeMaxFitness)) {
                if (equalsIteration > maxEqualsIteration) {
                    store[SHAKE_BY_REPEATABLE_EQUALS_ITERATION] = 0
                    true
                } else {
                    store[SHAKE_BY_REPEATABLE_EQUALS_ITERATION] = ++equalsIteration
                    false
                }
            } else {
                store[SHAKE_BY_REPEATABLE_MAX_FITNESS] = maxFitness
                store[SHAKE_BY_REPEATABLE_EQUALS_ITERATION] = 1
                false
            }
        }
    }
}
