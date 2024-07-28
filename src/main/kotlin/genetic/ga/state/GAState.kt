package genetic.ga.state

import genetic.clusters.base.state.ClusterState

enum class GAState {
    INITIALIZE,
    CREATED,
    STARTED,
    STOPPED,
    FINISHED,
    CLEARED,
}

fun gaStateMachine(previous: GAState, new: GAState): GAState =
    when (previous) {
        GAState.STOPPED -> when (new) {
            GAState.FINISHED -> previous
            else -> new
        }
        else -> new
    }
