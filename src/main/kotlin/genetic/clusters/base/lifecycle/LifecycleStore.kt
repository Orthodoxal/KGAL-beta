package genetic.clusters.base.lifecycle

interface LifecycleStore {
    val store: MutableMap<Any, Any?>
}

class LifecycleStoreInstance(
    override val store: MutableMap<Any, Any?> = mutableMapOf(),
) : LifecycleStore
