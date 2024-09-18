package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome
import kotlin.random.Random

data class ChromosomeBooleanArray<F : Comparable<F>>(
    override var value: BooleanArray,
    override var fitness: F? = null,
) : Chromosome<BooleanArray, F> {
    override fun compareTo(other: Chromosome<BooleanArray, F>): Int = compareValues(fitness, other.fitness)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChromosomeBooleanArray<*>

        if (!value.contentEquals(other.value)) return false
        return fitness == other.fitness
    }

    override fun hashCode(): Int {
        var result = value.contentHashCode()
        result = 31 * result + (fitness?.hashCode() ?: 0)
        return result
    }

    override fun clone(): Chromosome<BooleanArray, F> = copy(value = value.copyOf())
}

fun <F : Comparable<F>> Random.booleans(size: Int) =
    ChromosomeBooleanArray<F>(value = BooleanArray(size) { nextBoolean() })
