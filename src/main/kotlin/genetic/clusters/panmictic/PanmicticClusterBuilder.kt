package genetic.clusters.panmictic

import genetic.clusters.base.builder.ClusterBuilder

interface PanmicticClusterBuilder<V, F> : ClusterBuilder<V, F, PanmicticLifecycle<V, F>> {
    var elitism: Int
}
