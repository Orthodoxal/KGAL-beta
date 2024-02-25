package genetic.chromosome

interface Chromosome<V, F> {
    val value: V
    val fitness: F?
}

private class ChromosomeInstance<V, F>(
    override val value: V,
    override val fitness: F?,
) : Chromosome<V, F>

fun <V, F> Chromosome(value: V): Chromosome<V, F> = ChromosomeInstance(value, null)

fun <V, F> Chromosome(value: V, fitness: F): Chromosome<V, F> = ChromosomeInstance(value, fitness)
