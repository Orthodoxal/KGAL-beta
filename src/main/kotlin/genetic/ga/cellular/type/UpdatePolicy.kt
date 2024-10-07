package genetic.ga.cellular.type

import kotlin.random.Random

sealed interface UpdatePolicy {
    data object LineSweep : UpdatePolicy
    data object NewRandomSweep : UpdatePolicy
    data object UniformChoice : UpdatePolicy
    data class FixedRandomSweep(val random: Random) : UpdatePolicy {
        private var size = -1
        private var indicesShuffled: IntArray = intArrayOf()

        fun cacheIndices(size: Int): IntArray {
            if (size != this.size) {
                this.size = size
                indicesShuffled = IntArray(size) { it }.apply { shuffle(random) }
            }
            return indicesShuffled
        }
    }
}
