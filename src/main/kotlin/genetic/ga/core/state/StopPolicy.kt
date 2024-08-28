package genetic.ga.core.state

sealed interface StopPolicy {
    data object Default : StopPolicy
    data object Immediately : StopPolicy
    data class Timeout(val millis: Long) : StopPolicy
}
