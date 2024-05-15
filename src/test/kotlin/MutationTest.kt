import genetic.ga.base_operators.mutation.flip_bit.mutationFlipBitBooleanArray
import genetic.ga.base_operators.mutation.gaussian.mutationGaussian
import genetic.ga.base_operators.mutation.polynomial_bounded.mutationPolynomialBounded
import genetic.ga.base_operators.mutation.shuffle_indexes.mutationShuffleIndexes
import genetic.ga.base_operators.mutation.uniform.mutationUniform
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.test.assertContentEquals

private const val randomSeed = 42

class MutationTest {
    private val random get() = Random(randomSeed)
    private val doubleArrayValue1 get() = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
    private val intArrayValue1 get() = intArrayOf(0, 1, 2, 3, 4)
    private val booleanArrayValue1 get() = booleanArrayOf(true, false, false, true, true)

    @Test
    fun flipBitTest() {
        val value1 = booleanArrayValue1
        val chance = 0.5
        mutationFlipBitBooleanArray(value1, chance, random)
        val result1 = booleanArrayOf(false, false, true, true, false)
        assertContentEquals(value1, result1)
    }

    @Test
    fun gaussianTest() {
        val value1 = doubleArrayValue1
        val mean = 2.0
        val stddev = 0.4
        val chance = 0.5
        mutationGaussian(value1, mean, stddev, chance, random)
        val result1 = doubleArrayOf(2.1865506681264106, 2.0, 2.293127525016459, 2.0707398495277136, 5.0)
        assertContentEquals(value1, result1)
    }

    @Test
    fun polynomialBoundedTest() {
        val value1 = doubleArrayValue1
        val eta = 10.0
        val low = 1.0
        val up = 5.0
        val chance = 0.5
        mutationPolynomialBounded(value1, eta, low, up, chance, random)
        val result1 = doubleArrayOf(1.560383059449459, 2.907336237606931, 3.0737669899402116, 3.558574282607116, 5.0)
        assertContentEquals(value1, result1)
    }

    @Test
    fun shuffleIndexesTest() {
        val value1 = intArrayValue1
        val chance = 0.5
        mutationShuffleIndexes(value1, chance, random)
        val result1 = intArrayOf(1, 4, 0, 3, 2)
        assertContentEquals(value1, result1)
    }

    @Test
    fun uniformTest() {
        val value1 = intArrayValue1
        val low = 0
        val up = 4
        val chance = 0.5
        mutationUniform(value1, low, up, chance, random)
        val result1 = intArrayOf(3, 1, 3, 2, 4)
        assertContentEquals(value1, result1)
    }
}
