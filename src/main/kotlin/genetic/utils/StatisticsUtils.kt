package genetic.utils

import genetic.clusters.base.Cluster
import genetic.ga.GA
import genetic.stat.StatisticNote
import genetic.stat.StatisticRegister
import genetic.stat.StatisticsInstance

internal fun Cluster<*, *>.StatisticNote(statisticRegister: StatisticRegister) = StatisticNote(
    ownerName = name.orEmpty(),
    iteration = generation,
    name = statisticRegister.name,
    value = statisticRegister.registrar(),
)

internal fun GA<*, *>.StatisticNote(statisticRegister: StatisticRegister) = StatisticNote(
    ownerName = "GA",
    iteration = iteration,
    name = statisticRegister.name,
    value = statisticRegister.registrar(),
)

internal suspend inline fun StatisticsInstance.emitStat(
    statisticRegisters: Set<StatisticRegister>?,
    transformer: (StatisticRegister) -> StatisticNote,
) = statisticRegisters?.forEach { emit(transformer(it)) }

internal suspend fun Cluster<*, *>.statBefore(statisticsInstance: StatisticsInstance) =
    statisticsInstance.emitStat(statisticsInstance.beforeLifecycle, ::StatisticNote)

internal suspend fun Cluster<*, *>.statAfter(statisticsInstance: StatisticsInstance) =
    statisticsInstance.emitStat(statisticsInstance.afterLifecycle, ::StatisticNote)

internal suspend fun Cluster<*, *>.statOnLifecycleIteration(statisticsInstance: StatisticsInstance) =
    statisticsInstance.emitStat(statisticsInstance.onLifecycleIteration, ::StatisticNote)

suspend fun GA<*, *>.statBefore(statisticsInstance: StatisticsInstance) =
    statisticsInstance.emitStat(statisticsInstance.beforeLifecycle, ::StatisticNote)

suspend fun GA<*, *>.statAfter(statisticsInstance: StatisticsInstance) =
    statisticsInstance.emitStat(statisticsInstance.afterLifecycle, ::StatisticNote)

suspend fun GA<*, *>.statOnLifecycleIteration(statisticsInstance: StatisticsInstance) =
    statisticsInstance.emitStat(statisticsInstance.onLifecycleIteration, ::StatisticNote)
