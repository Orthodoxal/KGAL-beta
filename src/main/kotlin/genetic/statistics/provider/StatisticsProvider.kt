package genetic.statistics.provider

import genetic.statistics.config.StatisticsConfig
import genetic.statistics.note.StatisticNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

typealias STAT_COLLECTOR = FlowCollector<StatisticNote<Any?>>
typealias STAT_COLLECTOR_SCOPE = suspend CoroutineScope.(stat: Flow<StatisticNote<Any?>>) -> Unit

interface StatisticsProvider {

    fun collect(id: String, collector: STAT_COLLECTOR_SCOPE)

    suspend fun emit(value: StatisticNote<Any?>)

    fun prepareStatistics()

    suspend fun stopCollectors(force: Boolean)

    fun removeCollector(id: String)

    fun clearCollectors()

    companion object {
        const val DEFAULT_COLLECTOR_ID: String = "DEFAULT_COLLECTOR_ID"
        const val BASE_COLLECTOR_ID: String = "BASE_COLLECTOR_ID"
        const val STAT_STOP_COLLECT_FLAG = "STAT_STOP_COLLECT_FLAG"
    }
}

fun StatisticsProvider(
    ownerName: String,
    statisticsConfig: StatisticsConfig,
): StatisticsProvider = StatisticsProviderInstance(ownerName, statisticsConfig)
