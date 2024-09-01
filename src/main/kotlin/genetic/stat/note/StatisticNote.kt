package genetic.stat.note

interface StatisticNote<V> {
    val statistic: Statistic<V>
    val ownerName: String
    val iteration: Int
}
