package genetic.clusters.cellular.type

sealed interface UpdatePolicy {
    data object LineSweep : UpdatePolicy
    data object FixedRandomSweep : UpdatePolicy
    data object NewRandomSweep : UpdatePolicy
    data object UniformChoice : UpdatePolicy
}
