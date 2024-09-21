package genetic.ga.core.operators

import genetic.ga.core.lifecycle.Lifecycle
import genetic.statistics.registers.bestFitness

const val STEADY_GENERATIONS: String = "STEADY_GENERATIONS"
const val STEADY_GENERATIONS_CURRENT_ITERATION: String = "STEADY_GENERATIONS"

inline fun <V, reified F, L : Lifecycle<V, F>> L.isSteadyGenerations(
    targetIterationCount: Int,
    storeNamePrevious: String = STEADY_GENERATIONS,
    storeNameIteration: String = STEADY_GENERATIONS_CURRENT_ITERATION,
): Boolean = onSteadyGenerations(
    target = bestFitness!!,
    targetIterationCount = targetIterationCount,
    onSteadyGenerations = { },
    equalsPredicate = { t, p -> t == p },
    storeNamePrevious = storeNamePrevious,
    storeNameIteration = storeNameIteration,
)

inline fun <V, reified F, L : Lifecycle<V, F>> L.onSteadyGenerations(
    targetIterationCount: Int,
    storeNamePrevious: String = STEADY_GENERATIONS,
    storeNameIteration: String = STEADY_GENERATIONS_CURRENT_ITERATION,
    onSteadyGenerations: L.(target: F) -> Unit,
): Boolean = onSteadyGenerations(
    target = bestFitness!!,
    targetIterationCount = targetIterationCount,
    onSteadyGenerations = onSteadyGenerations,
    equalsPredicate = { t, p -> t == p },
    storeNamePrevious = storeNamePrevious,
    storeNameIteration = storeNameIteration,
)

inline fun <reified T : Comparable<T>, V, F, L : Lifecycle<V, F>> L.onSteadyGenerations(
    target: T,
    targetIterationCount: Int,
    storeNamePrevious: String = STEADY_GENERATIONS,
    storeNameIteration: String = STEADY_GENERATIONS_CURRENT_ITERATION,
    onSteadyGenerations: L.(target: T) -> Unit,
): Boolean = onSteadyGenerations(
    target = target,
    targetIterationCount = targetIterationCount,
    onSteadyGenerations = onSteadyGenerations,
    equalsPredicate = { t, p -> t == p },
    storeNamePrevious = storeNamePrevious,
    storeNameIteration = storeNameIteration,
)

inline fun <reified T, V, F, L : Lifecycle<V, F>> L.onSteadyGenerations(
    target: T,
    targetIterationCount: Int,
    storeNamePrevious: String = STEADY_GENERATIONS,
    storeNameIteration: String = STEADY_GENERATIONS_CURRENT_ITERATION,
    equalsPredicate: (current: T, previous: T) -> Boolean,
    onSteadyGenerations: L.(target: T) -> Unit,
): Boolean {
    val previous = store[storeNamePrevious] as? T
    val iteration = store[storeNameIteration] as? Int

    return when {
        previous == null || iteration == null -> {
            store[storeNamePrevious] = target
            store[storeNameIteration] = 1
            false
        }

        else -> {
            if (equalsPredicate(target, previous)) {
                if (iteration > targetIterationCount) {
                    store[storeNameIteration] = 0
                    onSteadyGenerations(target)
                    true
                } else {
                    store[storeNameIteration] = iteration + 1
                    false
                }
            } else {
                store[storeNamePrevious] = target
                store[storeNameIteration] = 1
                false
            }
        }
    }
}
