package genetic.statistics.registers

import genetic.ga.core.GA
import genetic.ga.core.lifecycle.Lifecycle
import genetic.ga.core.population.Population
import genetic.ga.core.population.copyOfRange
import genetic.statistics.note.Statistic

private const val NAME = "MEDIAN"

@get:JvmName("getMedianInt")
val GA<*, Int>.median
    get() = population.getMedian { toDouble() }

@get:JvmName("getMedianDouble")
val GA<*, Double>.median
    get() = population.getMedian { this }

@get:JvmName("getMedianLong")
val GA<*, Long>.median
    get() = population.getMedian { toDouble() }


@get:JvmName("getMedianInt")
val Lifecycle<*, Int>.median
    get() = population.getMedian { toDouble() }

@get:JvmName("getMedianDouble")
val Lifecycle<*, Double>.median
    get() = population.getMedian { this }

@get:JvmName("getMedianLong")
val Lifecycle<*, Long>.median
    get() = population.getMedian { toDouble() }


@JvmName("medianInt")
fun Lifecycle<*, Int>.median() = Statistic(NAME, median)

@JvmName("medianDouble")
fun Lifecycle<*, Double>.median() = Statistic(NAME, median)

@JvmName("medianLong")
fun Lifecycle<*, Long>.median() = Statistic(NAME, median)


private inline fun <F> Population<*, F>.getMedian(
    transformer: F.() -> Double,
): Double {
    val source = this.copyOfRange().apply { sort() }
    val indexMedian = source.size / 2
    return if (source.size % 2 == 1) {
        source[indexMedian].fitness!!.transformer()
    } else {
        val first = source[indexMedian]
        val second = source[indexMedian - 1]
        (first.fitness!!.transformer() + second.fitness!!.transformer()) / 2
    }
}
