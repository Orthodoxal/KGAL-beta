package genetic.ga.core.operators

import genetic.ga.core.lifecycle.Lifecycle
import genetic.ga.core.population.*
import genetic.utils.moreOrEquals

inline fun <V, F> Lifecycle<V, F>.shake(
    percent: Double,
) {
    if (percent moreOrEquals 1.0) {
        for (i in population.indices) population[i] = population.factory(random)
    } else {
        val count = (percent * population.size).toInt()
        for (i in 1..count) population[population.size - i] = population.factory(random)
    }
}

inline fun <V, F, L : Lifecycle<V, F>> L.shakeBy(
    percent: Double,
    predicate: L.() -> Boolean,
) {
    if (predicate(this)) {
        shake(percent)
    }
}
