package genetic.stat.note

import genetic.ga.core.lifecycle.GALifecycle
import genetic.ga.core.lifecycle.name

data class MultiStatisticNote(
    val statistics: List<Statistic<Any?>>,
    override val ownerName: String,
    override val iteration: Int,
) : StatisticNote<Any?> {
    override val statistic: Statistic<Any?>
        get() = Statistic(
            name = statistics.joinToString { it.name },
            value = statistics.map { it.value },
        )

    override fun toString(): String {
        return statistics.joinToString(separator = "") { (name, value) -> "$ownerName\t $iteration\t $name\t $value\n" }
    }
}

@JvmName("statValue")
suspend fun GALifecycle<*, *>.stat(
    vararg pairs: Pair<String, Any?>,
) = emitStat(
    MultiStatisticNote(
        statistics = pairs.map { Statistic(it.first, it.second) },
        ownerName = this.name,
        iteration = this.iteration,
    )
)

@JvmName("statRegister")
suspend fun GALifecycle<*, *>.stat(
    vararg pairs: Pair<String, () -> Any?>,
) = emitStat(
    MultiStatisticNote(
        statistics = pairs.map { Statistic(it.first, it.second()) },
        ownerName = this.name,
        iteration = this.iteration,
    )
)

suspend fun GALifecycle<*, *>.stat(
    vararg statistics: Statistic<Any?>,
) = emitStat(
    MultiStatisticNote(
        statistics = statistics.toList(),
        ownerName = this.name,
        iteration = this.iteration,
    )
)
