package genetic.ga.panmictic.operators.crossover

import genetic.ga.core.operators.crossover.simulated_binary.crossoverSimulatedBinary
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle

@JvmName("cxSimulatedBinaryDoubleArray")
suspend fun <F> PanmicticLifecycle<DoubleArray, F>.cxSimulatedBinary(
    chance: Double,
    eta: Double,
    parallelismLimit: Int = parallelismConfig.workersCount,
    crossoverType: CrossoverType = CrossoverType.Iterative,
) = crossover(chance, parallelismLimit, crossoverType) { chromosome1, chromosome2, random ->
    crossoverSimulatedBinary(chromosome1.value, chromosome2.value, eta, random)
}
