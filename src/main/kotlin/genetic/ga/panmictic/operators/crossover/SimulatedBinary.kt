package genetic.ga.panmictic.operators.crossover

import genetic.ga.core.operators.crossover.simulated_binary.crossoverSimulatedBinary
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

@JvmName("cxSimulatedBinaryDoubleArray")
suspend fun <F> PanmicticLifecycle<DoubleArray, F>.cxSimulatedBinary(
    chance: Double,
    eta: Double,
    parallelWorkersLimit: Int = parallelismConfig.count,
    crossoverType: CrossoverType = CrossoverType.Iterative,
) = crossover(chance, parallelWorkersLimit, crossoverType) { chromosome1, chromosome2 ->
    crossoverSimulatedBinary(chromosome1.value, chromosome2.value, eta, random)
}
