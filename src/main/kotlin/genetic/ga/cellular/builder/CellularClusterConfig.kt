package genetic.ga.cellular.builder

import genetic.clusters.ClusterBuilder
import genetic.ga.cellular.neighborhood.CellularNeighborhood
import genetic.ga.cellular.type.CellularType

class CellularClusterConfig(
    var clusterName: String? = null,
    var cellularType: CellularType? = null,
    var dimensions: IntArray? = null,
) {
    var neighborhoodChanged = true
    var neighbourhood: CellularNeighborhood? = null
        set(value) {
            if (field != null) neighborhoodChanged = true
            field = value
        }

    var neighboursIndicesCache: Array<IntArray>? = null
}

fun ClusterBuilder<*, *>.cellularCustomClusterConfig(
    cellularGABuilder: CellularGABuilder<*, *>,
    config: CellularClusterConfig.() -> Unit,
) {
    val cellularClusterConfig = CellularClusterConfig().apply {
        clusterName = name ?: throw IllegalStateException("Cluster name must be initialize, try to add cluster to GA first")
    }
    cellularClusterConfig.apply(config)
    cellularGABuilder.applyCustomClusterConfig(cellularClusterConfig)
}
