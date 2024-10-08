package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome
import kotlin.random.Random

data class ChromosomeFloatArray<F : Comparable<F>>(
    override var value: FloatArray,
    override var fitness: F? = null,
) : Chromosome<FloatArray, F> {
    override fun compareTo(other: Chromosome<FloatArray, F>): Int = compareValues(fitness, other.fitness)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChromosomeFloatArray<*>

        if (fitness != other.fitness) return false
        return value.contentEquals(other.value)
    }

    override fun hashCode(): Int {
        var result = value.contentHashCode()
        result = 31 * result + (fitness?.hashCode() ?: 0)
        return result
    }

    override fun clone(): Chromosome<FloatArray, F> = copy(value = value.copyOf())
}

fun <F : Comparable<F>> Random.floats(size: Int) =
    ChromosomeFloatArray<F>(value = FloatArray(size) { nextFloat() })
