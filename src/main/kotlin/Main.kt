import genetic.chromosome.Chromosome
import genetic.clusters.cluster.builder.cluster
import kotlinx.coroutines.runBlocking

fun test1() {
    val cluster = cluster<Int, Int> {
        population = arrayOf(
            Chromosome(1, 1),
            Chromosome(2, 2),
            Chromosome(3, 3),
        )
        lifecycle {

        }
    }
    runBlocking {
        cluster.start()
        cluster.stop()
    }
}

fun main(args: Array<String>) {
    test1()
}