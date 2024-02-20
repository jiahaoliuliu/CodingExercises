package math.easy

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.absoluteValue
import kotlin.math.log10

/**
 * Power of three
 *
 * Given an integer n, return true if it is a power of three. Otherwise, return false.
 * An integer n is a power of three, if there exists an integer x such that n == 3x.
 *
 * Example 1:
 *      Input: n = 27
 *      Output: true
 *      Explanation: 27 = 33
 *
 * Example 2:
 *      Input: n = 0
 *      Output: false
 *      Explanation: There is no x where 3x = 0.
 * Example 3:
 *      Input: n = -1
 *      Output: false
 *      Explanation: There is no x where 3x = (-1).
 *
 * Constraints:
 *  -2^31 <= n <= 2^31 - 1
 * Follow up: Could you solve it without loops/recursion?
 */
class PowerOfThree {

    /**
     * Initial thought
     * - For any number negative till 0 -> return false
     * - For positive numbers
     * -> while it is divisible by 3, divide it
     * If it is not divisible by 3, the unique number valid is 1
     */
    private fun isPowerOfThree(n: Int): Boolean {
        if (n < 1) return false

        var divident = n
        // While the number is still divisible by 3
        while (divident % 3 == 0) {
            divident /= 3
        }

        return divident == 1
    }

    /**
     * This is a more accurate solution, but not better
     * because there are 2 logs here and logs are expensive
     */
    private fun isPowerOfThree2(n: Int): Boolean {
        val log3 = log10(n.toDouble()) / log10(3.0)
        // Check if the log3 does not have any decimals
        return  log3 - log3.toInt() == 0.0
    }

    @Test
    fun test1() {
        // Given
        val input = 27

        // When
        val result = isPowerOfThree(input)

        // Then
        assertTrue(result)
    }

    @Test
    fun test2() {
        // Given
        val input = 0

        // When
        val result = isPowerOfThree(input)

        // Then
        assertFalse(result)
    }


    @Test
    fun test3() {
        // Given
        val input = -1

        // When
        val result = isPowerOfThree(input)

        // Then
        assertFalse(result)
    }

    @Test
    fun test4() {
        // Given
        val input = 45

        // When
        val result = isPowerOfThree(input)

        // Then
        assertFalse(result)
    }

    @Test
    fun test5() {
        // Given
        val input = 27

        // When
        val result = isPowerOfThree2(input)

        // Then
        assertTrue(result)
    }

    @Test
    fun test6() {
        // Given
        val input = 0

        // When
        val result = isPowerOfThree2(input)

        // Then
        assertFalse(result)
    }


    @Test
    fun test7() {
        // Given
        val input = -1

        // When
        val result = isPowerOfThree2(input)

        // Then
        assertFalse(result)
    }

    @Test
    fun test8() {
        // Given
        val input = 45

        // When
        val result = isPowerOfThree2(input)

        // Then
        assertFalse(result)
    }

    @Test
    fun test9() {
        // Given
        val input = 243

        // When
        val result = isPowerOfThree2(input)

        // Then
        assertTrue(result)
    }

    @Test
    fun test10() {
        // Given
        val input = 19684

        // When
        val result = isPowerOfThree2(input)

        // Then
        assertFalse(result)
    }

}