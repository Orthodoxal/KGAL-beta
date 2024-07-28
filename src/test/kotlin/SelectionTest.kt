import genetic.chromosome.Chromosome
import genetic.clusters.base.operators.selection.comparable.selectionBest
import genetic.clusters.base.operators.selection.comparable.selectionWorst
import genetic.clusters.base.operators.selection.random.selectionRandom
import genetic.clusters.base.operators.selection.roulette.selectionRouletteFitInt
import genetic.clusters.base.operators.selection.tournament.selectionTournament
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

private const val randomSeed = 42

private data class ChromosomeInt(
    override var value: Int,
    override var fitness: Int?,
) : Chromosome<Int, Int> {
    override fun compareTo(other: Chromosome<Int, Int>): Int = compareValues(fitness, other.fitness)
    override fun clone(): Chromosome<Int, Int> = copy(value = value, fitness = fitness)
}

class SelectionTest {
    private val random get() = Random(randomSeed)
    private val population: Array<Chromosome<Int, Int>>
        get() = run {
            val rand = random
            Array(10) { ChromosomeInt(it, fitness = rand.nextInt(0, 100)) }
        }

    @Test
    fun bestTest() {
        val count = 3
        val selected = selectionBest(population, count)
        val expectedId = intArrayOf(2, 8, 9)
        assertContentEquals(selected.map { it.value }.toIntArray(), expectedId)
    }

    @Test
    fun worstTest() {
        val count = 3
        val selected = selectionWorst(population, count)
        val expectedId = intArrayOf(5, 6, 3)
        assertContentEquals(selected.map { it.value }.toIntArray(), expectedId)
    }

    @Test
    fun randomTest() {
        val selected = selectionRandom(population, random)
        val expectedId = 3
        assertEquals(selected.value, expectedId)
    }

    @Test
    fun rouletteTest() {
        val totalFitness = population.fold(0L) { acc: Long, chromosome: Chromosome<Int, Int> ->
            acc + (chromosome.fitness ?: error("Fitness is null"))
        }
        val selected = selectionRouletteFitInt(population, totalFitness, random)
        val expectedId = 8
        assertEquals(selected.value, expectedId)
    }

    @Test
    fun tournamentTest() {
        val tournamentSize = 3
        val selected = selectionTournament(population, tournamentSize, random)
        val expectedId = 1
        assertEquals(selected.value, expectedId)
    }
}
