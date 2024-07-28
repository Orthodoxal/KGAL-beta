package genetic.stat.registers

import genetic.chromosome.Chromosome
import genetic.clusters.base.builder.ClusterBuilder
import genetic.ga.GABuilder
import genetic.stat.StatisticRegister
import genetic.stat.StatisticsBuilder

private const val REGISTER_NAME = "AVG"

fun avgInt(source: Array<out Chromosome<*, Int>>, size: Int) =
    source.fold(0) { acc, chromosome -> acc + chromosome.fitness!! }.toDouble() / size

fun avgDouble(source: Array<out Chromosome<*, Double>>, size: Int) =
    source.fold(0.0) { acc, chromosome -> acc + chromosome.fitness!! } / size

fun avgLong(source: Array<out Chromosome<*, Long>>, size: Int) =
    source.fold(0.0) { acc, chromosome -> acc + chromosome.fitness!! } / size

fun avgResult(sum: Double, size: Int) = if (size != 0) sum / size else null

fun StatisticsBuilder.avgInt(clusterBuilder: ClusterBuilder<*, Int>) =
    +StatisticRegister(REGISTER_NAME) { avgInt(clusterBuilder.populationOld, clusterBuilder.populationSize) }

fun StatisticsBuilder.avgDouble(clusterBuilder: ClusterBuilder<*, Double>) =
    +StatisticRegister(REGISTER_NAME) { avgDouble(clusterBuilder.populationOld, clusterBuilder.populationSize) }

fun StatisticsBuilder.avgLong(clusterBuilder: ClusterBuilder<*, Long>) =
    +StatisticRegister(REGISTER_NAME) { avgLong(clusterBuilder.populationOld, clusterBuilder.populationSize) }

fun StatisticsBuilder.avgInt(gaBuilder: GABuilder<*, Int>) =
    +StatisticRegister(REGISTER_NAME) {
        val sum = gaBuilder.clusters.sumOf { it.populationOld.sumOf { it.fitness!! } }.toDouble()
        val size = gaBuilder.clusters.sumOf { it.populationSize }
        avgResult(sum, size)
    }

fun StatisticsBuilder.avgDouble(gaBuilder: GABuilder<*, Double>) =
    +StatisticRegister(REGISTER_NAME) {
        val sum = gaBuilder.clusters.sumOf { it.populationOld.sumOf { it.fitness!! } }
        val size = gaBuilder.clusters.sumOf { it.populationSize }
        avgResult(sum, size)
    }

fun StatisticsBuilder.avgLong(gaBuilder: GABuilder<*, Long>) =
    +StatisticRegister(REGISTER_NAME) {
        val sum = gaBuilder.clusters.sumOf { it.populationOld.sumOf { it.fitness!! } }.toDouble()
        val size = gaBuilder.clusters.sumOf { it.populationSize }
        avgResult(sum, size)
    }
