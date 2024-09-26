package genetic.ga.panmictic.operators.mutation

import genetic.chromosome.Chromosome
import genetic.ga.core.lifecycle.size
import genetic.ga.core.parallelism.processor.execute
import genetic.ga.core.population.get
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle
import genetic.utils.randomByChance

suspend inline fun <V, F> PanmicticLifecycle<V, F>.mutation(
    chance: Double,
    parallelWorkersLimit: Int,
    crossinline mutation: (chromosome: Chromosome<V, F>) -> Unit,
) {
    execute(
        parallelWorkersLimit = parallelWorkersLimit,
        startIteration = elitism,
        endIteration = size,
        action = { index -> randomByChance(chance) { mutation(population[index]) } },
    )
}
