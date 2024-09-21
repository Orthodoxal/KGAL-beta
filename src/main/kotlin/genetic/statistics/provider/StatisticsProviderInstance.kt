package genetic.statistics.provider

import genetic.statistics.config.StatisticsConfig
import genetic.statistics.note.SingleStatisticNote
import genetic.statistics.note.Statistic
import genetic.statistics.note.StatisticNote
import genetic.statistics.provider.StatisticsProvider.Companion.DEFAULT_COLLECTOR_ID
import genetic.statistics.provider.StatisticsProvider.Companion.STAT_STOP_COLLECT_FLAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.job
import kotlinx.coroutines.launch

internal class StatisticsProviderInstance(
    private val ownerName: String,
    private val statisticsConfig: StatisticsConfig,
) : StatisticsProvider {

    private val statistics: MutableSharedFlow<StatisticNote<Any?>> = statisticsConfig.flow
    private val collectors: MutableMap<String, STAT_COLLECTOR_SCOPE> = collectors()
    private var statisticsCoroutineScope: CoroutineScope = statisticsConfig.newCoroutineScope
        get() {
            if (field.coroutineContext.job.isCancelled) {
                field = statisticsConfig.newCoroutineScope
            }
            return field
        }

    override fun collect(id: String, collector: STAT_COLLECTOR_SCOPE) {
        if (collectors.contains(id))
            throw IllegalArgumentException("Collector with id $id has been already added, try to use unique ID")

        collectors[id] = collector
    }

    override suspend fun emit(value: StatisticNote<Any?>) =
        statistics.emit(value)

    override fun prepareStatistics() {
        with(statisticsCoroutineScope) {
            collectors.forEach { (_, collector) -> launch { collector(statisticsSafeWrapper) } }
        }
    }

    override suspend fun stopCollectors(force: Boolean) {
        if (!force) {
            // send STAT_STOP_COLLECT_FLAG as a terminal for all collectors
            emit(
                SingleStatisticNote(
                    statistic = Statistic(STAT_STOP_COLLECT_FLAG, null),
                    ownerName = ownerName,
                    iteration = -1,
                )
            )
            // wait for all collectors of statistics completed
            statisticsCoroutineScope.coroutineContext.job.children.forEach { it.join() }
        }
        // cancel statisticsCoroutineScope
        statisticsCoroutineScope.cancel()
    }

    override fun removeCollector(id: String) {
        collectors.remove(id)
    }

    override fun clearCollectors() {
        collectors.clear()
    }

    private val statisticsSafeWrapper
        get() = statistics.takeWhile { it.statistic.name != STAT_STOP_COLLECT_FLAG }

    private fun collectors(): MutableMap<String, STAT_COLLECTOR_SCOPE> =
        if (statisticsConfig.enableDefaultCollector) {
            mutableMapOf(DEFAULT_COLLECTOR_ID to { flow -> flow.collect(statisticsConfig.defaultCollector) })
        } else {
            mutableMapOf()
        }
}
