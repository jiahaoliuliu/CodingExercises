package string.easy

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Is Palindrome
 *
 * Given an integer x, return true if x is a palindrome, and false otherwise.
 *
 * Example 1:
 *      Input: x = 121
 *      Output: true
 *      Explanation: 121 reads as 121 from left to right and from right to left.
 *
 * Example 2:
 *      Input: x = -121
 *      Output: false
 *      Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 *
 * Example 3:
 *      Input: x = 10
 *      Output: false
 *      Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 */
class PalindromeNumber {

    /**
     * Initial thought
     * Normal cases
     *  -
     *  - 1221 -> true
     *  - 121 -> true
     * Corner cases
     * - The number is negative -> false
     * - n < 10 ( 0, 1, 2, .. 9) -> True
     */
    private fun isPalindrome(x: Int): Boolean {
        if (x < 0 ) return false
        if (x < 10) return true

        val myString = x.toString()

        var left = 0
        var right = myString.length - 1
        while (left < right) {
            if (myString[left] != myString[right]) {
                return false
            }
            left ++
            right --
        }

        return true
    }

    /**
     * More efficient approach:
     * Building the reverse of the number and compare
     */
    private fun isPalindrome2(x: Int): Boolean {
        var reverse = 0
        var temp = x
        if (x < 0) return false
        while (temp != 0) {
            val remainder = temp % 10
            reverse = reverse * 10 + remainder
            temp /= 10
        }

        return reverse == x
    }

    @Test
    fun test1() {
        // Given
        val input = 121

        // When
        val result = isPalindrome(input)

        // Then
        assertTrue(result)
    }

    @Test
    fun test2() {
        // Given
        val input = -121

        // When
        val result = isPalindrome(input)

        // Then
        assertFalse(result)
    }

    @Test
    fun test3() {
        // Given
        val input = 10

        // When
        val result = isPalindrome(input)

        // Then
        assertFalse(result)
    }

    @Test
    fun test4() {
        // Given
        val input = 1221

        // When
        val result = isPalindrome(input)

        // Then
        assertTrue(result)
    }

    @Test
    fun test5() {
        // Given
        val input = 121

        // When
        val result = isPalindrome2(input)

        // Then
        assertTrue(result)
    }

    @Test
    fun test6() {
        // Given
        val input = -121

        // When
        val result = isPalindrome2(input)

        // Then
        assertFalse(result)
    }

    @Test
    fun test7() {
        // Given
        val input = 10

        // When
        val result = isPalindrome2(input)

        // Then
        assertFalse(result)
    }

    @Test
    fun test8() {
        // Given
        val input = 1221

        // When
        val result = isPalindrome2(input)

        // Then
        assertTrue(result)
    }

}