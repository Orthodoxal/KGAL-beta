package genetic.ga.core.config

import genetic.ga.core.lifecycle.Lifecycle
import genetic.stat.config.StatisticsConfig
import kotlinx.coroutines.CoroutineDispatcher

abstract class AbstractConfigGAScope<V, F, L : Lifecycle<V, F>> : ConfigGA<V, F, L> {
    override var maxIteration: Int = 0
    override var mainDispatcher: CoroutineDispatcher? = null
    override var extraDispatchers: Array<CoroutineDispatcher>? = null
    override var statisticsConfig: StatisticsConfig = StatisticsConfig

    override var beforeEvolution: suspend L.() -> Unit = { }
    override var evolution: suspend L.() -> Unit = { }
    override var afterEvolution: suspend L.() -> Unit = { }

    fun AbstractConfigGAScope<V, F, L>.before(useDefault: Boolean = true, beforeEvolution: suspend L.() -> Unit) {
        this.beforeEvolution = beforeEvolution.takeIf { !useDefault } ?: { baseBefore(); beforeEvolution() }
    }

    fun AbstractConfigGAScope<V, F, L>.evolve(useDefault: Boolean = true, evolution: suspend L.() -> Unit) {
        this.evolution = evolution.takeIf { !useDefault } ?: { baseEvolve(); evolution() }
    }

    fun AbstractConfigGAScope<V, F, L>.after(useDefault: Boolean = true, afterEvolution: suspend L.() -> Unit) {
        this.afterEvolution = afterEvolution.takeIf { !useDefault } ?: { afterEvolution(); baseAfter() }
    }

    protected open val baseBefore: suspend L.() -> Unit = { }
    protected open val baseEvolve: suspend L.() -> Unit = { }
    protected open val baseAfter: suspend L.() -> Unit = { }
}
