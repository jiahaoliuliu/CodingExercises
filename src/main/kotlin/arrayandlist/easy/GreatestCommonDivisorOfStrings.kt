package arrayandlist.easy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Greatest Common Divisor of Strings
 *
 * For two strings s and t, we say "t divides s" if and only if s = t + t + t + ... + t + t (i.e., t
 * is concatenated with itself one or more times).
 *
 * Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.
 *
 * Example 1:
 *      Input: str1 = "ABCABC", str2 = "ABC"
 *      Output: "ABC"
 *
 * Example 2:
 *      Input: str1 = "ABABAB", str2 = "ABAB"
 *      Output: "AB"
 *
 * Example 3:
 *      Input: str1 = "LEET", str2 = "CODE"
 *      Output: ""
 *
 * Constraints:
 *      1 <= str1.length, str2.length <= 1000
 *      str1 and str2 consist of English uppercase letters.
 */
abstract class GreatestCommonDivisorOfStrings {

    abstract fun gcdOfStrings(str1: String, str2: String): String

    @Test
    fun test1() {
        // Given
        val str1 = "ABCABC"
        val str2 = "ABC"

        // When
        val result = gcdOfStrings(str1, str2)

        // Then
        assertEquals("ABC", result)
    }

    @Test
    fun test2() {
        // Given
        val str1 = "ABABAB"
        val str2 = "ABAB"

        // When
        val result = gcdOfStrings(str1, str2)

        // Then
        assertEquals("AB", result)
    }

    @Test
    fun test3() {
        // Given
        val str1 = "LEET"
        val str2 = "CODE"

        // When
        val result = gcdOfStrings(str1, str2)

        // Then
        assertEquals("", result)
    }
}

class GreatestCommonDivisorOfStringsImpl: GreatestCommonDivisorOfStrings() {

    /**
     * Initial thoughts
     *
     * For any gcd of strings, we can start from the smallest of the both strings,
     * starting from back, reducing the size from back to front, then check the value with both
     * strings
     *
     * 1. Create a function that given string1 and string2, where string 2 is longer than string
     * 1, and a number, check if the substring from 0 to number is gcd of both strings
     * - Return true if so
     * - Return false if not
     *
     * 2. Using the function created on the step 1, iterate through all the possible substrings
     * of the shortest string, starting from the biggest substring, always starting from the position 0,
     * and reducing the size from the back.
     *
     * Complexity:
     * - Time: O(min(m, n) * (m + n)) There are min(m, n) prefix to be checked. And each check we are using
     *  m + n verifications
     * - Space: O(1)
     *
     */
    override fun gcdOfStrings(str1: String, str2: String): String {
        val string1 = if (str1.length < str2.length) str1 else str2
        val string2 = if (str1.length < str2.length) str2 else str1

        for (i in string1.length  downTo 1) {
            if (gcdOfStrings(string1, string2, i)) {
                return string1.substring(0, i)
            }
        }

        return ""
    }

    /**
     * str1 should be shorter than str2
     *
     */
    private fun gcdOfStrings(str1: String, str2: String, i: Int): Boolean {
        val prefix = str1.substring(0, i)

        // They are repetition of the prefix if by replacing them as ""
        // the result is an empty string
        return (str1.replace(prefix, "").isEmpty() &&
                str2.replace(prefix, "").isEmpty())
    }
}

class GreatestCommonDivisorOfStringsOptimal: GreatestCommonDivisorOfStrings() {

    /**
     * There is a common divisor if str1 + str2 == str2 + str1
     *
     * And the length of the gcd of str1 and str2 is the length of the gcd between str1.length
     * and str2.length
     *
     */
    override fun gcdOfStrings(str1: String, str2: String): String {
        // Check if the gcd exists
        if (str1 + str2 != str2 + str1) {
            return ""
        }

        // Get the length of the gcd
        val gcdLength = gcd(str1.length, str2.length)
        return str1.substring(0, gcdLength)

    }

    private fun gcd(x: Int, y:Int): Int {
        return if (y == 0) {
            x
        } else {
            gcd(y, x % y)
        }
    }
}