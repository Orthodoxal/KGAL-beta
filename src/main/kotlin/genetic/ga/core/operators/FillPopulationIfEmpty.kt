package genetic.ga.core.operators

import genetic.ga.core.lifecycle.Lifecycle
import genetic.ga.core.population.isEmpty

inline fun <V, F> Lifecycle<V, F>.fillPopulationIfEmpty() {
    with(population) {
        if (isEmpty) {
            val first = random.factory()
            population = Array(maxSize) { index ->
                when {
                    index == 0 -> first
                    index < size -> random.factory()
                    else -> first
                }
            }
        }
    }
}
