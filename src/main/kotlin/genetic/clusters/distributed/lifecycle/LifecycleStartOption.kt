package genetic.clusters.distributed.lifecycle

sealed interface LifecycleStartOption {
    data object Start : LifecycleStartOption
    data class Restart(val resetPopulation: Boolean = true) : LifecycleStartOption
    data object Resume : LifecycleStartOption
}
