package genetic.ga

import genetic.ga.core.GA
import genetic.ga.cellular.CellularGA
import genetic.ga.cellular.CellularGABuilder
import genetic.ga.distributed.DistributedGA
import genetic.ga.distributed.DistributedGABuilder
import genetic.ga.panmictic.PanmicticGA
import genetic.ga.panmictic.PanmicticGABuilder
import kotlin.random.Random

inline fun <V, F> pGA(
    random: Random = Random,
    skipValidation: Boolean = false, // TODO добавить валидатор конфигурации
    builder: PanmicticGABuilder<V, F>.() -> Unit,
): GA<V, F> =
    PanmicticGA<V, F>(random).apply(builder)

inline fun <V, F> cGA(
    random: Random = Random,
    skipValidation: Boolean = false, // TODO добавить валидатор конфигурации
    builder: CellularGABuilder<V, F>.() -> Unit,
): GA<V, F> =
    CellularGA<V, F>(random).apply(builder)

inline fun <V, F> dGA(
    random: Random = Random,
    skipValidation: Boolean = false, // TODO добавить валидатор конфигурации
    builder: DistributedGABuilder<V, F>.() -> Unit,
): GA<V, F> =
    DistributedGA<V, F>(random).apply(builder)
