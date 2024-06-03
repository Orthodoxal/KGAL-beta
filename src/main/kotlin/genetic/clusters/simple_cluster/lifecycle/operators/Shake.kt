package genetic.clusters.simple_cluster.lifecycle.operators

import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.stat.registers.max
import genetic.utils.moreOrEquals

const val SHAKE_BY_REPEATABLE_MAX_FITNESS = "ShakeByRepeatableMaxFitness"
const val SHAKE_BY_REPEATABLE_EQUALS_ITERATION = "ShakeByRepeatableEqualsIteration"

inline fun <V, F> SimpleClusterLifecycle<V, F>.shakeBy(
    percent: Double,
    predicate: (SimpleClusterLifecycle<V, F>) -> Boolean
) {
    if (predicate(this)) {
        if (percent moreOrEquals 1.0) {
            for (i in population.indices) population[i] = populationFactory(i, random)
        } else {
            val count = (percent * populationSize).toInt()
            for (i in 1..count) population[populationSize - i] = populationFactory(i, random)
        }
    }
}

inline fun <V, reified F> SimpleClusterLifecycle<V, F>.shakeByRepeatableForMax(
    percent: Double,
    maxEqualsIteration: Int,
    equalsPredicate: (max: F, prevMax: F) -> Boolean,
) = shakeBy(percent) {
    val maxFitness = max().fitness!!
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