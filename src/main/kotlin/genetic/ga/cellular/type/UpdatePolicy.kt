package genetic.ga.cellular.type

import kotlin.random.Random

sealed interface UpdatePolicy {
    data object LineSweep : UpdatePolicy
    data class FixedRandomSweep(val random: Random) : UpdatePolicy
    data object NewRandomSweep : UpdatePolicy
    data object UniformChoice : UpdatePolicy
}
