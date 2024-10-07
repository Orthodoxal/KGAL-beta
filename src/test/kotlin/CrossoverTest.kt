/*
import genetic.clusters.base.operators.crossover.blend.crossoverBlendDoubleArray
import genetic.clusters.base.operators.crossover.k_point.crossoverKPointDoubleArray
import genetic.clusters.base.operators.crossover.one_point.crossoverOnePointDoubleArray
import genetic.clusters.base.operators.crossover.ordered.crossoverOrdered
import genetic.clusters.base.operators.crossover.simulated_binary.crossoverSimulatedBinary
import genetic.clusters.base.operators.crossover.uniform.crossoverUniformIntArray
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.test.assertContentEquals

private const val randomSeed = 42

class CrossoverTest {
    private val random get() = Random(randomSeed)
    private val doubleArrayValue1 get() = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
    private val doubleArrayValue2 get() = doubleArrayOf(5.0, 4.0, 3.0, 2.0, 1.0)
    private val intArrayValue1 get() = intArrayOf(0, 1, 2, 3, 4)
    private val intArrayValue2 get() = intArrayOf(4, 3, 2, 1, 0)

    @Test
    fun blendTest() {
        val value1 = doubleArrayValue1
        val value2 = doubleArrayValue2
        val alpha = 2.0
        crossoverBlendDoubleArray(value1, value2, alpha, random)
        val result1 = doubleArrayOf(
            -2.4736946805536446,
            7.049568172356873,
            2.9999999999999996,
            -1.7049006786438667,
            9.327923219220962,
        )
        val result2 = doubleArrayOf(
            11.490333363980316,
            11.699501191282891,
            3.0,
            -8.568045197951758,
            -8.010653067088938,
        )
        assertContentEquals(value1, result1)
        assertContentEquals(value2, result2)
    }

    @Test
    fun kPointTest() {
        val value1 = doubleArrayValue1
        val value2 = doubleArrayValue2
        val count = 3
        crossoverKPointDoubleArray(value1, value2, count, random)
        val result1 = doubleArrayOf(1.0, 4.0, 3.0, 2.0, 1.0)
        val result2 = doubleArrayOf(5.0, 2.0, 3.0, 4.0, 5.0)
        assertContentEquals(value1, result1)
        assertContentEquals(value2, result2)
    }

    @Test
    fun onePointTest() {
        val value1 = doubleArrayValue1
        val value2 = doubleArrayValue2
        crossoverOnePointDoubleArray(value1, value2, random)
        val result1 = doubleArrayOf(1.0, 2.0, 3.0, 2.0, 1.0)
        val result2 = doubleArrayOf(5.0, 4.0, 3.0, 4.0, 5.0)
        assertContentEquals(value1, result1)
        assertContentEquals(value2, result2)
    }

    @Test
    fun orderedTest() {
        val value1 = intArrayValue1
        val value2 = intArrayValue2
        crossoverOrdered(value1, value2, random)
        val result1 = intArrayOf(0, 3, 2, 1, 4)
        val result2 = intArrayOf(4, 1, 2, 3, 0)
        assertContentEquals(value1, result1)
        assertContentEquals(value2, result2)
    }

    @Test
    fun simulatedBinaryTest() {
        val value1 = doubleArrayValue1
        val value2 = doubleArrayValue2
        val eta = 20.0
        crossoverSimulatedBinary(value1, value2, eta, random)
        val result1 = doubleArrayOf(1.0740861983279795, 1.9177298802418918, 3.0, 4.144257835283835, 4.906827943885093)
        val result2 = doubleArrayOf(4.927285992867694, 4.085654306060614, 3.0, 1.8453370031957768, 1.0910017981047377)
        assertContentEquals(value1, result1)
        assertContentEquals(value2, result2)
    }

    @Test
    fun uniformTest() {
        val value1 = intArrayValue1
        val value2 = intArrayValue2
        val chance = 0.8
        crossoverUniformIntArray(value1, value2, chance, random)
        val result1 = intArrayOf(4, 1, 2, 3, 0)
        val result2 = intArrayOf(0, 3, 2, 1, 4)
        assertContentEquals(value1, result1)
        assertContentEquals(value2, result2)
    }
}
*/
