package genetic.chromosome.base_instances

import genetic.chromosome.Chromosome
import kotlin.random.Random

data class ChromosomeDoubleArray<F : Comparable<F>>(
    override var value: DoubleArray,
    override var fitness: F? = null,
    private val clone: (ChromosomeDoubleArray<F>.() -> ChromosomeDoubleArray<F>)? = null,
) : Chromosome<DoubleArray, F> {
    override fun compareTo(other: Chromosome<DoubleArray, F>): Int = compareValues(fitness, other.fitness)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChromosomeDoubleArray<*>

        if (!value.contentEquals(other.value)) return false
        return fitness == other.fitness
    }

    override fun hashCode(): Int {
        var result = value.contentHashCode()
        result = 31 * result + (fitness?.hashCode() ?: 0)
        return result
    }

    override fun clone(): Chromosome<DoubleArray, F> = clone?.let { it() } ?: copy(value = value.copyOf())
}

fun <F : Comparable<F>> ChromosomeDoubleArray(
    valueSize: Int,
    from: Double? = null,
    until: Double? = null,
    random: Random = Random,
    fitness: F? = null,
    clone: (ChromosomeDoubleArray<F>.() -> ChromosomeDoubleArray<F>)? = null,
) = ChromosomeDoubleArray(
    value = DoubleArray(valueSize) {
        when {
            from != null && until != null -> random.nextDouble(from, until)
            from != null -> random.nextDouble(from, Double.MAX_VALUE)
            until != null -> random.nextDouble(Double.MIN_VALUE, until)
            else -> random.nextDouble()
        }
    },
    fitness,
    clone,
)
