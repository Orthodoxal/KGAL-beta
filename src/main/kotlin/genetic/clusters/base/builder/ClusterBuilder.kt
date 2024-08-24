package genetic.clusters.base.builder

import genetic.clusters.base.lifecycle.ClusterLifecycle
import genetic.clusters.base.population.Population
import genetic.stat.StatisticsInstance
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random

interface ClusterBuilder<V, F, L : ClusterLifecycle<V, F>> {
    var name: String?
    val random: Random
    var randomSeed: Int

    var population: Population<V, F>
    var maxIteration: Int
    var fitnessFunction: ((V) -> F)?

    var stat: StatisticsInstance?

    var mainDispatcher: CoroutineDispatcher?
    var extraDispatchers: Array<CoroutineDispatcher>?

    fun lifecycle(
        before: (suspend L.() -> Unit)? = null,
        after: (suspend L.() -> Unit)? = null,
        lifecycle: suspend L.() -> Unit,
    )

    fun setStatInstance(statisticsInstance: StatisticsInstance, coroutineContext: CoroutineContext)
}

val ClusterBuilder<*, *, *>.isSingleRun get() = extraDispatchers?.isEmpty() ?: true
