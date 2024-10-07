package genetic.ga.cellular.type

sealed interface CellularType {
    data object Synchronous : CellularType
    data class Asynchronous(val updatePolicy: UpdatePolicy = UpdatePolicy.LineSweep) : CellularType
}
