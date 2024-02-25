package genetic.chromosome

interface ChromosomeComparator<V, F> : Comparator<Chromosome<V, F>>

fun <V, F> comparator(compare: (c1: Chromosome<V, F>, c2: Chromosome<V, F>) -> Int) =
    object : ChromosomeComparator<V, F> {
        override fun compare(c1: Chromosome<V, F>, c2: Chromosome<V, F>): Int = compare(c1, c2)
    }
