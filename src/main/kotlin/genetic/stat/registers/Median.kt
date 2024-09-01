package genetic.stat.registers

import genetic.ga.core.GA
import genetic.ga.core.lifecycle.GALifecycle
import genetic.ga.core.population.Population
import genetic.ga.core.population.copyOfRange
import genetic.stat.note.Statistic

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
val GALifecycle<*, Int>.median
    get() = population.getMedian { toDouble() }

@get:JvmName("getMedianDouble")
val GALifecycle<*, Double>.median
    get() = population.getMedian { this }

@get:JvmName("getMedianLong")
val GALifecycle<*, Long>.median
    get() = population.getMedian { toDouble() }


@JvmName("medianInt")
fun GALifecycle<*, Int>.median() = Statistic(NAME, median)

@JvmName("medianDouble")
fun GALifecycle<*, Double>.median() = Statistic(NAME, median)

@JvmName("medianLong")
fun GALifecycle<*, Long>.median() = Statistic(NAME, median)


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
