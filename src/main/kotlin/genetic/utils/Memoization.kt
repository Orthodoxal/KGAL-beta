package genetic.utils

class LRUCache<K, V>(private val capacity: Int) : LinkedHashMap<K, V>(capacity, 0.75f, true) {
    override fun removeEldestEntry(eldest: MutableMap.MutableEntry<K, V>?): Boolean = size > capacity

    @Synchronized
    override fun get(key: K): V? {
        return super.get(key)
    }

    @Synchronized
    override fun put(key: K, value: V): V? {
        return super.put(key, value)
    }
}

fun <T, R> memoizeLRU(capacity: Int, function: (T) -> R): (T) -> R {
    val cache = LRUCache<T, R>(capacity)
    return { input ->
        cache.getOrPut(input) { function(input) }
    }
}
