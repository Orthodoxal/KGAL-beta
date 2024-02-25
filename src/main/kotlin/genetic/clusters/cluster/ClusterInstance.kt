package genetic.clusters.cluster

import genetic.chromosome.Chromosome
import genetic.chromosome.ChromosomeComparator
import genetic.clusters.state.ClusterState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.random.Random
import kotlin.reflect.KProperty

internal class ClusterInstance<V, F>(
    override val state: MutableStateFlow<ClusterState> = MutableStateFlow(ClusterState.INITIALIZE),
    override var clusterJob: Job? = null,
) : Cluster<V, F> {

    private val params: MutableMap<String, Any?> = Params
    private val paramsFutureChange: MutableMap<String, Any?> = mutableMapOf()

    override lateinit var population: Array<Chromosome<V, F>>
    override var randomSeed: Int by this
    override var random: Random by this

    override var fitnessFunction: (Chromosome<V, F>) -> Unit by this
    override var comparator: ChromosomeComparator<V, F> by this
    override var clone: Chromosome<V, F>.() -> Chromosome<V, F> by this

    override var generation: Int by this
    override var maxGeneration: Int by this


    override fun <E> getParamValue(name: String): E =
        @Suppress("UNCHECKED_CAST") (params[name] as? E) ?: error("Unknown type")

    override fun <E> setParamValue(value: Pair<String, E>) {
        if (state.value == ClusterState.INITIALIZE) params else paramsFutureChange + value
    }

    override fun removeParamValue(name: String) {
        paramsFutureChange.remove(name)
        params.remove(name)
    }

    override fun updateParams() {
        params + paramsFutureChange
    }

    operator fun <E, V, F> ClusterInstance<V, F>.getValue(thisRef: Any?, property: KProperty<*>): E =
        @Suppress("UNCHECKED_CAST")
        (params[property.name] as? E) ?: error("Unknown type for property ${property.name}")

    operator fun <E, V, F> ClusterInstance<V, F>.setValue(thisRef: Any?, property: KProperty<*>, value: E) {
        val name = property.name
        if (state.value == ClusterState.INITIALIZE) {
            params[name] = value
        } else {
            paramsFutureChange[name] = value
        }
        if (name == "randomSeed") {
            random = Random(randomSeed)
        }
    }

    private companion object Default {
        val Params = mutableMapOf<String, Any?>(
            "state" to ClusterState.INITIALIZE,
            "random" to Random,
            "generation" to 0,
        )
    }
}
