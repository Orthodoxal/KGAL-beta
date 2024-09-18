package genetic.ga.core.lifecycle

interface LifecycleStore {
    val store: MutableMap<Any, Any?>
}

internal class LifecycleStoreInstance(
    override val store: MutableMap<Any, Any?> = mutableMapOf(),
) : LifecycleStore
