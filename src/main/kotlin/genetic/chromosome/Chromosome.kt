package genetic.chromosome

interface Chromosome<V, F> : Comparable<Chromosome<V, F>>, ChromosomeCloneable<Chromosome<V, F>> {
    var value: V
    var fitness: F?
}
