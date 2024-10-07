package genetic.ga.core.state

interface GAState {
    data object INITIALIZED : GAState
    data object STARTED : GAState
    data object STOPPED : GAState

    sealed interface FINISHED : GAState {
        data object ByMaxIteration : FINISHED
        data object ByStopConditions : FINISHED
    }
}
