package genetic.clusters.state

sealed interface ClusterStopPolicy {
    data object Default : ClusterStopPolicy
    data object Immediately : ClusterStopPolicy
    data class Timeout(val millis: Long) : ClusterStopPolicy
}
