package genetic.clusters.cluster

interface ClusterApi {
    fun <E> getParamValue(name: String): E
    fun <E> setParamValue(value: Pair<String, E>)
    fun removeParamValue(name: String)
    fun updateParams()
}
