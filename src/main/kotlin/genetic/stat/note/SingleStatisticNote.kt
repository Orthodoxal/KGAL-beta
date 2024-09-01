package genetic.stat.note

import genetic.ga.core.lifecycle.GALifecycle
import genetic.ga.core.lifecycle.name

data class SingleStatisticNote<V>(
    override val statistic: Statistic<V>,
    override val ownerName: String,
    override val iteration: Int,
) : StatisticNote<V> {
    override fun toString(): String = "$ownerName\t $iteration\t ${statistic.name}\t ${statistic.value}"
}

suspend fun GALifecycle<*, *>.stat(
    name: String,
    value: Any?,
) = emitStat(
    SingleStatisticNote(
        statistic = Statistic(name, value),
        ownerName = this.name,
        iteration = this.iteration,
    )
)

@JvmName("statValue")
suspend fun GALifecycle<*, *>.stat(
    pair: Pair<String, Any?>,
) = emitStat(
    SingleStatisticNote(
        statistic = Statistic(pair.first, pair.second),
        ownerName = this.name,
        iteration = this.iteration,
    )
)

suspend inline fun GALifecycle<*, *>.stat(
    name: String,
    registrar: () -> Any?,
) = emitStat(
    SingleStatisticNote(
        statistic = Statistic(name, registrar()),
        ownerName = this.name,
        iteration = this.iteration,
    )
)

@JvmName("statRegister")
suspend fun GALifecycle<*, *>.stat(
    pair: Pair<String, () -> Any?>,
) = emitStat(
    SingleStatisticNote(
        statistic = Statistic(pair.first, pair.second()),
        ownerName = this.name,
        iteration = this.iteration,
    )
)

suspend fun GALifecycle<*, *>.stat(
    statistic: Statistic<Any?>,
) = emitStat(
    SingleStatisticNote(
        statistic = statistic,
        ownerName = this.name,
        iteration = this.iteration,
    )
)
