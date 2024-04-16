package genetic.clusters.state

enum class ClusterState {
    INITIALIZE,
    CREATED,
    STARTED,
    STOPPED,
    FINISHED,
    CLEARED,
}

fun clusterStateMachine(previous: ClusterState, new: ClusterState): ClusterState =
    when (previous) {
        ClusterState.STOPPED -> when (new) {
            ClusterState.FINISHED -> previous
            else -> new
        }
        else -> new
    }
