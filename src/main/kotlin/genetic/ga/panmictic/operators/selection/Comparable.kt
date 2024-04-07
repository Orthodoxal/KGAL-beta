package genetic.ga.panmictic.operators.selection

import genetic.clusters.simple_cluster.lifecycle.SimpleClusterLifecycle
import genetic.ga.base_operators.selection.comparable.selectionBest
import genetic.ga.base_operators.selection.comparable.selectionWorst
import genetic.ga.panmictic.builder.PanmicticGABuilder
import genetic.utils.fillArrayChromosomeBySubArray

fun <V, F> SimpleClusterLifecycle<V, F>.selectionBest(
    panmicticGABuilder: PanmicticGABuilder<V, F>,
    count: Int,
) = fillArrayChromosomeBySubArray(population, selectionBest(population, count))

fun <V, F> SimpleClusterLifecycle<V, F>.selectionWorst(
    panmicticGABuilder: PanmicticGABuilder<V, F>,
    count: Int,
) = fillArrayChromosomeBySubArray(population, selectionWorst(population, count))
