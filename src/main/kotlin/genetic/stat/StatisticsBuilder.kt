package genetic.stat

class StatisticsBuilder : MutableSet<StatisticRegister> by mutableSetOf() {
    operator fun StatisticRegister.unaryPlus() = add(this)
}
