import genetic.chromosome.base_instances.ChromosomeBooleanArray
import genetic.chromosome.base_instances.ChromosomeIntArray
import genetic.chromosome.base_instances.ChromosomeMutableList
import genetic.clusters.simple_cluster.lifecycle.utils.fitnessAll
import genetic.ga.panmictic.operators.crossover.crossoverOnePointBooleanArray
import genetic.ga.panmictic.operators.crossover.crossoverOnePointMutableList
import genetic.ga.panmictic.operators.mutation.mutation
import genetic.ga.panmictic.operators.mutation.mutationFlipBitBooleanArray
import genetic.ga.panmictic.operators.selection.selectionTournament
import genetic.ga.simplePanmicticGA
import genetic.utils.randomByChance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/*fun PanmicticGABuilder<IntArray, Int>.gaCluster() = simpleCluster<IntArray, Int> {
    randomSeed = 42
    fitnessFunction = { value -> value.sum() }
    comparator = comparator { c1, c2 -> c1.fitness!! - c2.fitness!! }
    clone = { Chromosome(value.copyOf(), fitness) }
    population = Array(200) { Chromosome(value = IntArray(100) { random.nextInt(0, 2) }) }
    maxGeneration = 50
    populationSize = 200

    lifecycle {
        selectionTournament(this@gaCluster, tournamentSize = 3)
        crossoverOnePointIntArray(this@gaCluster, chance = 0.9)
        mutationFlipBitIntArray(this@gaCluster, chance = 0.1)
        fitnessAll()
    }
}*/

fun oneMax() {
    /*val ga = panmicticGA<IntArray, Int> {
        +simpleCluster<IntArray, Int> {
            randomSeed = 42
            fitnessFunction = { value -> value.sum() }
            comparator = comparator { c1, c2 -> c1.fitness!! - c2.fitness!! }
            clone = { Chromosome(value.copyOf(), fitness) }
            population = Array(200) { Chromosome(value = IntArray(100) { random.nextInt(0, 2) }) }
            maxGeneration = 50
            populationSize = 200

            lifecycle {
                selectionTournament(this@panmicticGA, tournamentSize = 3)
                crossoverOnePointIntArray(this@panmicticGA, chance = 0.9)
                mutationFlipBitIntArray(this@panmicticGA, chance = 0.1)
                fitnessAll()
            }
        }
    }*/

    /*val ga2 = simplePanmicticGA<MutableList<Int>, Int> {
                randomSeed = 42
                fitnessFunction = { value -> value.sum() }
                population = Array(200) { ChromosomeMutableList(value = MutableList(100) { random.nextInt(0, 2) }) }
                maxGeneration = 50
                populationSize = 200
                //extraDispatchers = Array(5) { Dispatchers.IO }

                lifecycle {
                    selectionTournament(it, tournamentSize = 3)
                    crossoverOnePointMutableList(it, chance = 0.9)
                    mutation(it, chance = 1.0, onlySingleRun = false) {
                        it.value.forEachIndexed { index, gene ->
                            randomByChance(0.01) { it.value[index] = if (gene == 1) 0 else 1 }
                        }
                    }
                }
            }*/

    val count = 1
    var time: Long = 0
    repeat(count) {
        val ga = simplePanmicticGA<BooleanArray, Int> {
            randomSeed = 42
            fitnessFunction = { value -> value.sumOf { (if (it) 1 else 0).toInt() } }
            population = Array(200) { ChromosomeBooleanArray(value = BooleanArray(100) { random.nextBoolean() }) }
            maxGeneration = 50
            populationSize = 200
            //extraDispatchers = Array(5) { Dispatchers.Default }

            lifecycle(
                before = {
                    fitnessAll()
                    println("Initial")
                    println(population.joinToString(", ") { it.fitness.toString() })
                }
            ) {
                selectionTournament(it, tournamentSize = 3)
                crossoverOnePointBooleanArray(it, chance = 0.9)
                mutationFlipBitBooleanArray(it, chance = 0.01)
                fitnessAll()

                println(generation)
                val avg = population.fold(0) { acc, chromosome -> acc + chromosome.fitness!! } / populationSize
                println("Max = ${population.max().fitness}, avg = $avg")
            }
        }

        runBlocking {
            time += measureTimeMillis {
                ga.start()
            }
            //ga2.start()
        }

        println(it)
        println(ga.clusters[0].population.joinToString(", ") { it.fitness.toString() })
    }
    time /= count

    println()
    println()
    println(time)
}
