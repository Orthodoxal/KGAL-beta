package genetic.stat

import genetic.ga.core.GA
import genetic.ga.core.name

internal fun GA<*, *>.StatisticNote(statisticRegister: StatisticRegister) = StatisticNote(
    ownerName = name,
    iteration = iteration,
    name = statisticRegister.name,
    value = statisticRegister.registrar(),
)

internal suspend inline fun StatisticsInstance.emitStat(
    statisticRegisters: Set<StatisticRegister>?,
    transformer: (StatisticRegister) -> StatisticNote,
) = statisticRegisters?.forEach { emit(transformer(it)) }

internal suspend fun GA<*, *>.statBefore(statisticsInstance: StatisticsInstance) =
    statisticsInstance.emitStat(statisticsInstance.beforeLifecycle, ::StatisticNote)

internal suspend fun GA<*, *>.statAfter(statisticsInstance: StatisticsInstance) =
    statisticsInstance.emitStat(statisticsInstance.afterLifecycle, ::StatisticNote)

internal suspend fun GA<*, *>.statOnLifecycleIteration(statisticsInstance: StatisticsInstance) =
    statisticsInstance.emitStat(statisticsInstance.onLifecycleIteration, ::StatisticNote)
