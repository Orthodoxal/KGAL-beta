package genetic.clusters.simple_cluster.lifecycle

import genetic.clusters.simple_cluster.builder.SimpleClusterBuilder

interface SimpleClusterLifecycle<V, F> : SimpleClusterBuilder<V, F>

internal class SimpleClusterLifecycleInstance<V, F>(
    builder: SimpleClusterBuilder<V, F>,
) : SimpleClusterLifecycle<V, F>, SimpleClusterBuilder<V, F> by builder
