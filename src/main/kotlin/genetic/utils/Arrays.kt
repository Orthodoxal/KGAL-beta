package genetic.utils

import genetic.chromosome.Chromosome

internal fun <V, F> fillArrayChromosomeBySubArray(
    array: Array<Chromosome<V, F>>,
    subArray: Array<Chromosome<V, F>>
) {
    repeat(array.size) { index ->
        if (index < subArray.size) {
            array[index] = subArray[index]
        } else {
            array[index] = subArray[index % subArray.size].clone()
        }
    }
}
