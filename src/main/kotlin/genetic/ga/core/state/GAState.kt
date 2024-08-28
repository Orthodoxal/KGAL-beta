package genetic.ga.core.state

enum class GAState {
    INITIALIZE,
    STARTED,
    STOPPED,
    FINISHED,
}

fun clusterStateMachine(previous: GAState, new: GAState): GAState =
    when (previous) {
        GAState.STOPPED -> when (new) {
            GAState.FINISHED -> previous
            else -> new
        }
        else -> new
    }
