package arrayandlist.hard

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

abstract class AverageOfTwoNumbers {

    abstract fun findAverage(num1: Int, num2: Int): Double

    @ParameterizedTest(name = "The average of {0} and {1} should be {2}")
    @MethodSource("getData")
    fun test(num1:  Int, num2: Int, expectedValue: Double) {
        val result = findAverage(num1, num2)
        assertEquals(expectedValue,result, 1.0)
    }

    companion object {

        @JvmStatic
        fun getData(): List<Array<Any>> {
            return listOf(
                arrayOf(1, 2, 1.5),
                arrayOf(2, 3, 2.5)
            )
        }
    }
}

class AverageOfTwoNumbersImpl: AverageOfTwoNumbers() {

    /**
     * Initial thoughts
     *
     * Based on the requirement of O(log(n + m)), it tells us that it is something similar to the binary search
     *
     * Due to that nums1 and nums2 are sorted, the key is search for the right element on every iteration
     * - Every step we need to get the half of n and half of m
     * Example:
     * - num1 = [1, 2, 3, 4], m = 4
     * - num2 = [1, 4, 5, 6, 7], n = 5
     *
     * Computing the half of each list
     *
     * 1. compute the half...
     *
     * https://www.youtube.com/watch?v=q6IEA26hvXc
     *
     */
    override fun findAverage(num1: Int, num2: Int): Double {
        return (num1.toDouble() + num2.toDouble())/2.0
    }
}
