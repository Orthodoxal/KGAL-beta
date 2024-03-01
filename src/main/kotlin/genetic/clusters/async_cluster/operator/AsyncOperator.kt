package genetic.clusters.async_cluster.operator

import genetic.chromosome.Chromosome
import genetic.clusters.async_cluster.AsyncClusterApi

data class AsyncOperator<V, F>(
    private val operatorId: Int,
    val params: AsyncOperatorParams,
    val operatorAction: AsyncOperatorAction<V, F>,
) {

    suspend operator fun <A : AsyncOperatorArgs<V, F>> AsyncClusterApi<V, F>.invoke(args: A) = when (operatorAction) {
        is STSAction -> operatorAction.action.invoke(chromosome(args)).run { send(this) }
        is STMAction -> operatorAction.action.invoke(chromosome(args)).run { send(this) }
        is MTSAction -> operatorAction.action.invoke(chromosomes(args)).run { send(this) }
        is MTMAction -> operatorAction.action.invoke(chromosomes(args)).run { send(this) }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <A : AsyncOperatorArgs<V, F>> chromosome(args: A): Chromosome<V, F> =
        (args as? AsyncOperatorArgs.Single<V, F>)?.chromosome ?: error("XXX")

    @Suppress("UNCHECKED_CAST")
    private fun <A : AsyncOperatorArgs<V, F>> chromosomes(args: A): Array<Chromosome<V, F>> =
        (args as? AsyncOperatorArgs.Multiple<V, F>)?.chromosomes ?: error("XXX")

    private suspend fun AsyncClusterApi<V, F>.send(chromosome: Chromosome<V, F>) =
        sendResult(AsyncOperatorResult(operatorId, chromosome))

    // FIXME Подумать над отправкой всего массива
    private suspend fun AsyncClusterApi<V, F>.send(chromosomes: Array<Chromosome<V, F>>) =
        chromosomes.forEach { sendResult(AsyncOperatorResult(operatorId, it)) }
}
