package genetic.clusters.cluster.lifecycle

import genetic.clusters.cluster.builder.ClusterBuilder

interface ClusterLifecycle<V, F> : ClusterBuilder<V, F>

internal class ClusterLifecycleInstance<V, F>(
    builder: ClusterBuilder<V, F>,
) : ClusterLifecycle<V, F>, ClusterBuilder<V, F> by builder
