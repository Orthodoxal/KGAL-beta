package genetic.clusters.async_cluster.operator

data class AsyncOperatorParams(
    val iterationsCount: Int,
    val argsSelectionStrategy: ArgsSelectionStrategy,
)

sealed interface ArgsSelectionStrategy {
    data class Random(val count: Int = 1) : ArgsSelectionStrategy
    data class Iterative(val count: Int = 1) : ArgsSelectionStrategy
    data object All : ArgsSelectionStrategy
}
