package genetic.ga.panmictic.operators.mutation

import genetic.chromosome.Chromosome
import genetic.ga.core.lifecycle.size
import genetic.ga.core.population.get
import genetic.ga.core.processor.process
import genetic.ga.panmictic.lifecycle.PanmicticLifecycle
import genetic.utils.randomByChance
import kotlin.random.Random

suspend inline fun <V, F> PanmicticLifecycle<V, F>.mutation(
    chance: Double,
    parallelismLimit: Int,
    crossinline mutation: (chromosome: Chromosome<V, F>, random: Random) -> Unit,
) {
    process(
        parallelismLimit = parallelismLimit,
        startIteration = elitism,
        endIteration = size,
        action = { index, random ->
            randomByChance(chance, random) {
                mutation(population[index], random)
            }
        },
    )
}
