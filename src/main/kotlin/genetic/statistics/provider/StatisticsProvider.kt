package genetic.statistics.provider

import genetic.statistics.config.StatisticsConfig
import genetic.statistics.note.StatisticNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

typealias STAT_FLOW_COLLECTOR = suspend CoroutineScope.(stat: Flow<StatisticNote<Any?>>) -> Unit
typealias STAT_COLLECTOR = FlowCollector<StatisticNote<Any?>>

interface StatisticsProvider {

    fun collect(
        id: String = BASE_COLLECTOR_ID,
        collector: STAT_COLLECTOR,
    )

    fun flow(
        id: String = BASE_FLOW_COLLECTOR_ID,
        collector: STAT_FLOW_COLLECTOR,
    )

    suspend fun emit(value: StatisticNote<Any?>)

    fun prepareStatistics()

    suspend fun stopCollectors(force: Boolean)

    companion object {
        const val DEFAULT_COLLECTOR_ID: String = "DEFAULT_COLLECTOR_ID"
        const val BASE_COLLECTOR_ID: String = "BASE_COLLECTOR_ID"
        const val BASE_FLOW_COLLECTOR_ID: String = "BASE_FLOW_COLLECTOR_ID"
        const val STAT_STOP_COLLECT_FLAG = "STAT_STOP_COLLECT_FLAG"
    }
}

fun StatisticsProvider(
    ownerName: String,
    statisticsConfig: StatisticsConfig,
): StatisticsProvider = StatisticsProviderInstance(ownerName, statisticsConfig)
