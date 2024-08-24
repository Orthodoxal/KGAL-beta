package genetic.clusters.cellular.type

sealed interface CellularType {
    data object Synchronous : CellularType
    class Asynchronous(val updatePolicy: UpdatePolicy = UpdatePolicy.LineSweep) : CellularType
}
