import genetic.chromosome.Chromosome
import genetic.chromosome.base_instances.ChromosomeIntArray
import genetic.clusters.simple_cluster.lifecycle.operators.fitness
import genetic.clusters.simple_cluster.lifecycle.operators.fitnessAll
import genetic.ga.simplePanmicticGA
import kotlinx.coroutines.job
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class FitnessTest {

    private val population: Array<Chromosome<IntArray, Int>>
        get() = Array(5) { ind -> ChromosomeIntArray(IntArray(5) { ind }) }
    private val expectedFitness = intArrayOf(0, 5, 10, 15, 20)
    private val fitnessFunction: (IntArray) -> Int = { it.sum() }

    @Test
    fun fitnessAllTest() {
        simplePanmicticGA<IntArray, Int> {
            populationOld = this@FitnessTest.population
            fitnessFunction = this@FitnessTest.fitnessFunction
            maxGeneration = 1

            lifecycle {
                fitnessAll()
            }
        }.apply {
            runBlocking {
                start()
                coroutineContext.job.children.forEach { it.join() }
            }
            assertContentEquals(expectedFitness, clusters[0].populationOld.mapNotNull { it.fitness }.toIntArray())
        }
    }

    @Test
    fun fitnessTest() {
        simplePanmicticGA<IntArray, Int> {
            populationOld = this@FitnessTest.population
            fitnessFunction = this@FitnessTest.fitnessFunction
            maxGeneration = 1

            lifecycle {
                populationOld.forEach { fitness(it) }
                assertContentEquals(expectedFitness, populationOld.mapNotNull { it.fitness }.toIntArray())
            }
        }.apply {
            runBlocking {
                start()
                coroutineContext.job.children.forEach { it.join() }
            }
        }
    }
}
