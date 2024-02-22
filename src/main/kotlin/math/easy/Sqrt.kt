package math.easy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Given a non-negative integer x, return the square root of x rounded down to the nearest integer.
 * The returned integer should be non-negative as well.
 *
 * You must not use any built-in exponent function or operator.
 *
 * For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
 *
 * Example 1:
 *      Input: x = 4
 *      Output: 2
 *      Explanation: The square root of 4 is 2, so we return 2.
 *
 * Example 2:
 *      Input: x = 8
 *      Output: 2
 *      Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest
 *      integer, 2 is returned.
 *
 * Constraints:
 *      0 <= x <= 231 - 1
 */
abstract class Sqrt {

    abstract fun mySqrt(x: Int): Int

    @Test
    fun test1() {
        // Given
        val num = 4

        // When
        val result = mySqrt(num)

        // Then
        assertEquals(2, result)
    }

    @Test
    fun test2() {
        // Given
        val num = 8

        // When
        val result = mySqrt(num)

        // Then
        assertEquals(2, result)
    }

    @Test
    fun test3() {
        // Given
        val num = 9

        // When
        val result = mySqrt(num)

        // Then
        assertEquals(3, result)
    }

    @Test
    fun test4() {
        // Given
        val num = 2147395599

        // When
        val result = mySqrt(num)

        // Then
        assertEquals(46339, result)
    }
}

class SqrtImpl: Sqrt() {

    /**
     * Initial thought
     *
     * Using binary search for infinite approximation
     *
     * left = 1
     * right = x / 2
     */
    override fun mySqrt(x: Int): Int {
        var left = 0
        var right = x / 2 + 1

        while (left <= right) {
            val middle = left + (right - left)/2
            val middleSquare = middle.toLong()*middle.toLong()
            val middleSquareUp = (middle.toLong() + 1) * (middle.toLong() + 1)
            if (middleSquare == x.toLong()) {
                return middle
            } else if (x in (middleSquare + 1) until middleSquareUp) {
                return middle
            } else if (middleSquare > x) {
                right = middle - 1
            } else {
                left = middle + 1
            }
        }

        return 1
    }
}