package genetic.ga.cellular.utils

sealed interface Dimens {
    val value: IntArray
    val size: Int

    data class Line(val length: Int) : Dimens {
        override val value: IntArray = intArrayOf(length)
        override val size: Int = length
    }

    data class Square(val length: Int) : Dimens {
        override val value: IntArray = intArrayOf(length, length)
        override val size: Int = length * length
    }

    data class Cube(val length: Int) : Dimens {
        override val value: IntArray = intArrayOf(length, length, length)
        override val size: Int = length * length * length
    }

    class Custom(vararg dimens: Int) : Dimens {
        override val value: IntArray = dimens
        override val size: Int = dimens.fold(1) { acc, dimen -> acc * dimen }
    }

    companion object Default : Dimens {
        override val value: IntArray = intArrayOf()
        override val size: Int = 0
    }
}
