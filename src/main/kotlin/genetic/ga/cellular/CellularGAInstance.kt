package genetic.ga.cellular

import genetic.chromosome.Chromosome
import genetic.clusters.Cluster
import genetic.clusters.simple_cluster.builder.SimpleClusterBuilder
import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.clusters.simple_cluster.lifecycle.utils.fitnessAll
import genetic.ga.AbstractGA
import genetic.ga.cellular.builder.CellularClusterConfig
import genetic.ga.cellular.builder.CellularGABuilder
import genetic.ga.cellular.lifecycle.CellularGALifecycle
import genetic.ga.cellular.lifecycle.CellularGALifecycleInstance
import genetic.ga.cellular.lifecycle.SimpleClusterCellLifecycle
import genetic.ga.cellular.lifecycle.simpleClusterCellLifecycle
import genetic.ga.cellular.neighborhood.CellularNeighborhood
import genetic.ga.cellular.neighborhood.toroidalShapeIndicesFilter
import genetic.ga.cellular.neighborhood.von_neumann.VonNeumann
import genetic.ga.cellular.type.CellularType
import genetic.ga.cellular.type.UpdatePolicy
import genetic.ga.lifecycle.LifecycleStartOption
import genetic.stat.StatisticsInstance
import genetic.utils.clusters.checkClusterNameOrTrySetDefaultName
import genetic.utils.clusters.runWithExtraDispatchersIterative
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.job
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext
import kotlin.random.Random

internal class CellularGAInstance<V, F> : AbstractGA<V, F>(), CellularGABuilder<V, F> {
    private var beforeLifecycle: suspend CellularGALifecycle<V, F>.() -> Unit = { }
    private var lifecycle: suspend CellularGALifecycle<V, F>.() -> Unit = BASE_LIFECYCLE
    private var afterLifecycle: suspend CellularGALifecycle<V, F>.() -> Unit = { }
    override var random: Random = Random
    private var neighborhoodChanged = true

    override fun CoroutineScope.startByOption(
        startOption: LifecycleStartOption,
        iterationFrom: Int,
        coroutineContext: CoroutineContext
    ) {
        val lifecycleScope = CellularGALifecycleInstance(
            builder = this@CellularGAInstance,
            lifecycleStartOption = startOption,
        )

        launchGA(iterationFrom, coroutineContext) { startGA(lifecycleScope) }
    }

    private suspend fun startGA(lifecycleScope: CellularGALifecycle<V, F>) {
        if (neighborhoodChanged) {
            val (indicesOneArray, indicesNArray) = neighborhood.neighboursIndicesMatrix(dimensions)
            val size = dimensions.fold(1) { acc, i -> acc * i }
            neighboursIndicesCache = Array(size) { position ->
                toroidalShapeIndicesFilter(position, dimensions, indicesOneArray, indicesNArray)
            }
        }

        for (config in customClusterConfigs) reEvaluateIfNeedByConfig(config)

        baseStartGA(lifecycleScope, beforeLifecycle, lifecycle, afterLifecycle)
    }

    override fun addCluster(cluster: Cluster<V, F>): Cluster<V, F> = cluster.apply {
        checkClusterNameOrTrySetDefaultName(cluster, clusters)
        clusters.add(cluster)
    }

    override fun CellularGABuilder<V, F>.lifecycle(
        before: (suspend CellularGALifecycle<V, F>.() -> Unit)?,
        after: (suspend CellularGALifecycle<V, F>.() -> Unit)?,
        lifecycle: suspend CellularGALifecycle<V, F>.() -> Unit,
    ) {
        before?.let { beforeLifecycle = before }
        after?.let { afterLifecycle = after }
        this@CellularGAInstance.lifecycle = lifecycle
    }

    override var randomSeed: Int = 0
        set(value) {
            random = Random(value)
        }
    override lateinit var populationFactory: (index: Int, random: Random) -> Chromosome<V, F>
    override lateinit var fitnessFunction: (V) -> F
    override var neighborhood: CellularNeighborhood = VonNeumann(radius = 1)
        set(value) {
            neighborhoodChanged = true
            field = value
        }
    override var cellularType: CellularType = CellularType.Synchronous
    override lateinit var dimensions: IntArray
    override lateinit var neighboursIndicesCache: Array<IntArray>
    override val customClusterConfigs = mutableListOf<CellularClusterConfig>()


    override fun setStatInstance(statisticsInstance: StatisticsInstance, coroutineContext: CoroutineContext) {
        gaStatisticsCoroutineContext = coroutineContext
        this.statisticsInstance = statisticsInstance
    }

    override fun applyCustomClusterConfig(cellularClusterConfig: CellularClusterConfig) {
        val index = customClusterConfigs.indexOfFirst { it.clusterName == cellularClusterConfig.clusterName }
        if (index != -1) {
            customClusterConfigs[index] = cellularClusterConfig
        } else {
            customClusterConfigs.add(cellularClusterConfig)
        }
    }

    override fun SimpleClusterBuilder<V, F>.lifecycleCellular(
        elitism: Boolean,
        before: (suspend SimpleClusterLifecycle<V, F>.() -> Unit)?,
        after: (suspend SimpleClusterLifecycle<V, F>.() -> Unit)?,
        afterLifecycleIteration: (suspend SimpleClusterLifecycle<V, F>.() -> Unit)?,
        lifecycle: suspend SimpleClusterCellLifecycle<V, F>.() -> Unit,
    ) = lifecycleCellularNoWrap(elitism, before, after, afterLifecycleIteration) { chromosome, neighbours ->
        with(simpleClusterCellLifecycle(chromosome, neighbours)) { lifecycle(); cellChromosome }
    }

    override fun SimpleClusterBuilder<V, F>.lifecycleCellularNoWrap(
        elitism: Boolean,
        before: (suspend SimpleClusterLifecycle<V, F>.() -> Unit)?,
        after: (suspend SimpleClusterLifecycle<V, F>.() -> Unit)?,
        afterLifecycleIteration: (suspend SimpleClusterLifecycle<V, F>.() -> Unit)?,
        lifecycle: suspend SimpleClusterLifecycle<V, F>.(chromosome: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
    ) {
        val config = customClusterConfigs.find { it.clusterName == name }
        val beforeCellular: suspend SimpleClusterLifecycle<V, F>.() -> Unit = {
            config?.apply(::reEvaluateIfNeedByConfig)
            fitnessAll()
            before?.invoke(this)
        }
        this.lifecycle(beforeCellular, after, lifecycleCellular(elitism, config, afterLifecycleIteration, lifecycle))
    }

    private fun lifecycleCellular(
        elitism: Boolean,
        config: CellularClusterConfig?,
        afterLifecycleIteration: (suspend SimpleClusterLifecycle<V, F>.() -> Unit)?,
        lifecycle: suspend SimpleClusterLifecycle<V, F>.(chromosome: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
    ): suspend SimpleClusterLifecycle<V, F>.() -> Unit = {
        val cellularType = config?.cellularType ?: cellularType
        val populationNeighboursIndices = config?.neighboursIndicesCache ?: neighboursIndicesCache

        when (cellularType) {
            is CellularType.Synchronous -> {
                if (isSingleRun) {
                    singleRunSynchronous(elitism, populationNeighboursIndices) { chromosome, neighbours ->
                        lifecycle(chromosome, neighbours)
                    }
                } else {
                    multiRunSynchronous(elitism, populationNeighboursIndices) { chromosome, neighbours ->
                        lifecycle(chromosome, neighbours)
                    }
                }
            }

            is CellularType.Asynchronous -> {
                val updatePolicy = cellularType.updatePolicy
                if (isSingleRun) {
                    singleRunAsynchronous(
                        elitism, updatePolicy, populationNeighboursIndices
                    ) { chromosome, neighbours ->
                        lifecycle(chromosome, neighbours)
                    }
                } else {
                    TODO("Реализовать многопоточную версию")
                }
            }
        }

        afterLifecycleIteration?.invoke(this)
    }

    private inline fun SimpleClusterLifecycle<V, F>.singleRunSynchronous(
        elitism: Boolean,
        populationNeighboursIndices: Array<IntArray>,
        lifecycleWorker: (chromosome: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
    ) {
        val tempPopulation = population.copyOf()
        population.forEachIndexed { index, chromosome ->
            val chromosomeNeighboursIndices = populationNeighboursIndices[index]
            val chromosomeNeighbours = Array(chromosomeNeighboursIndices.size) { indexNeighbour ->
                population[chromosomeNeighboursIndices[indexNeighbour]]
            }
            val result = lifecycleWorker(chromosome.clone(), chromosomeNeighbours)
            replaceWithElitism(elitism, tempPopulation, index, chromosome, result)
        }
        population = tempPopulation
    }

    private suspend inline fun SimpleClusterLifecycle<V, F>.multiRunSynchronous(
        elitism: Boolean,
        populationNeighboursIndices: Array<IntArray>,
        crossinline lifecycleWorker: suspend (chromosome: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
    ) {
        val tempPopulation = population.copyOf()

        runWithExtraDispatchersIterative(iterationStart = 0, maxIterationEnd = populationSize) { iteration ->
            val chromosomeNeighboursIndices = populationNeighboursIndices[iteration]
            val chromosomeNeighbours = Array(chromosomeNeighboursIndices.size) { indexNeighbour ->
                population[chromosomeNeighboursIndices[indexNeighbour]]
            }
            val result = lifecycleWorker(population[iteration].clone(), chromosomeNeighbours)
            replaceWithElitism(elitism, tempPopulation, iteration, tempPopulation[iteration], result)
        }

        population = tempPopulation
    }

    private suspend inline fun SimpleClusterLifecycle<V, F>.singleRunAsynchronous(
        elitism: Boolean,
        updatePolicy: UpdatePolicy,
        populationNeighboursIndices: Array<IntArray>,
        crossinline lifecycleWorker: suspend (chromosome: Chromosome<V, F>, neighbours: Array<Chromosome<V, F>>) -> Chromosome<V, F>,
    ) {
        when (updatePolicy) {
            is UpdatePolicy.LineSweep -> {
                runWithExtraDispatchersIterative(iterationStart = 0, maxIterationEnd = populationSize) { iteration ->
                    val chromosomeNeighboursIndices = populationNeighboursIndices[iteration]
                    val chromosomeNeighbours = Array(chromosomeNeighboursIndices.size) { indexNeighbour ->
                        population[chromosomeNeighboursIndices[indexNeighbour]]
                    }
                    val result = lifecycleWorker(population[iteration].clone(), chromosomeNeighbours)
                    replaceWithElitism(elitism, population, iteration, population[iteration], result)
                }
            }

            is UpdatePolicy.FixedRandomSweep -> {
                val indicesShuffled = IntArray(populationSize) { it }.apply { shuffle(Random(0)) }
                runWithExtraDispatchersIterative(iterationStart = 0, maxIterationEnd = populationSize) { iteration ->
                    val index = indicesShuffled[iteration]
                    val chromosomeNeighboursIndices = populationNeighboursIndices[index]
                    val chromosomeNeighbours = Array(chromosomeNeighboursIndices.size) { indexNeighbour ->
                        population[chromosomeNeighboursIndices[indexNeighbour]]
                    }
                    val result = lifecycleWorker(population[index].clone(), chromosomeNeighbours)
                    replaceWithElitism(elitism, population, index, population[index], result)
                }
            }

            is UpdatePolicy.NewRandomSweep -> {
                val indicesShuffled = IntArray(populationSize) { it }.apply { shuffle(random) }
                runWithExtraDispatchersIterative(iterationStart = 0, maxIterationEnd = populationSize) { iteration ->
                    val index = indicesShuffled[iteration]
                    val chromosomeNeighboursIndices = populationNeighboursIndices[index]
                    val chromosomeNeighbours = Array(chromosomeNeighboursIndices.size) { indexNeighbour ->
                        population[chromosomeNeighboursIndices[indexNeighbour]]
                    }
                    val result = lifecycleWorker(population[index].clone(), chromosomeNeighbours)
                    replaceWithElitism(elitism, population, index, population[index], result)
                }
            }

            is UpdatePolicy.UniformChoice -> {
                runWithExtraDispatchersIterative(iterationStart = 0, maxIterationEnd = populationSize) { _ ->
                    val index = random.nextInt()
                    val chromosomeNeighboursIndices = populationNeighboursIndices[index]
                    val chromosomeNeighbours = Array(chromosomeNeighboursIndices.size) { indexNeighbour ->
                        population[chromosomeNeighboursIndices[indexNeighbour]]
                    }
                    val result = lifecycleWorker(population[index].clone(), chromosomeNeighbours)
                    replaceWithElitism(elitism, population, index, population[index], result)
                }
            }
        }
    }

    private fun reEvaluateIfNeedByConfig(config: CellularClusterConfig) {
        if (config.neighborhoodChanged) {
            val dim = config.dimensions ?: dimensions
            val (indicesOneArray, indicesNArray) = neighborhood.neighboursIndicesMatrix(dim)
            val size = dim.fold(1) { acc, i -> acc * i }
            config.neighboursIndicesCache = Array(size) { position ->
                toroidalShapeIndicesFilter(position, dim, indicesOneArray, indicesNArray)
            }
            config.neighborhoodChanged = false
        }
    }

    private fun replaceWithElitism(
        elitism: Boolean,
        population: Array<Chromosome<V, F>>,
        index: Int,
        old: Chromosome<V, F>,
        new: Chromosome<V, F>,
    ) {
        if (elitism) {
            if (new > old) population[index] = new
        } else {
            population[index] = new
        }
    }

    companion object {
        private val BASE_LIFECYCLE: suspend CellularGALifecycle<*, *>.() -> Unit = {
            launchClusters(clusters)
            coroutineContext.job.children.forEach { it.join() }
        }
    }
}
