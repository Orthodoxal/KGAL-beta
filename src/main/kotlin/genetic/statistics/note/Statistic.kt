package genetic.statistics.note

import kotlin.reflect.KProperty

interface Statistic<out V> {
    val name: String
    val value: V

    operator fun getValue(thisRef: Any?, property: KProperty<*>): V = value

    operator fun component1() = name
    operator fun component2() = value
}

fun <V> Statistic(name: String, value: V): Statistic<V> = StatisticInstance(name, value)

internal class StatisticInstance<V>(
    override val name: String,
    override val value: V,
) : Statistic<V>
