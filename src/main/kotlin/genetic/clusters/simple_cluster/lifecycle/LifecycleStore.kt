package genetic.clusters.simple_cluster.lifecycle

interface LifecycleStore {
    val store: MutableMap<Any, Any?>
}


class LifecycleStoreInstance(
    override val store: MutableMap<Any, Any?> = mutableMapOf(),
) : LifecycleStore
