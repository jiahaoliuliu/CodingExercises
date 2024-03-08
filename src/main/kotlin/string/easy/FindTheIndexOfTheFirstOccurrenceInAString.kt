package string.easy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Find the Index of the First Occurrence in a String
 *
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack,
 * or -1 if needle is not part of haystack.
 *
 * Example 1:
 *      Input: haystack = "sadbutsad", needle = "sad"
 *      Output: 0
 *      Explanation: "sad" occurs at index 0 and 6.
 *      The first occurrence is at index 0, so we return 0.
 *
 * Example 2:
 *      Input: haystack = "leetcode", needle = "leeto"
 *      Output: -1
 *      Explanation: "leeto" did not occur in "leetcode", so we return -1.
 *
 * Constraints:
 *      1 <= haystack.length, needle.length <= 10^4
 *      haystack and needle consist of only lowercase English characters.
 */
abstract class FindTheIndexOfTheFirstOccurrenceInAString {

    abstract fun strStr(haystack: String, needle: String): Int

    @Test
    fun test1() {
        // Given
        val haystack = "sadbutsad"
        val needle = "sad"

        // When
        val result = strStr(haystack, needle)

        // Then
        assertEquals(0, result)
    }

    @Test
    fun test2() {
        // Given
        val haystack = "leetcode"
        val needle = "leeto"

        // When
        val result = strStr(haystack, needle)

        // Then
        assertEquals(-1, result)
    }

    @Test
    fun test3() {
        // Given
        val haystack = "abc"
        val needle = "c"

        // When
        val result = strStr(haystack, needle)

        // Then
        assertEquals(2, result)
    }
}

class FindTheIndexOfTheFirstOccurrenceInAStringImpl: FindTheIndexOfTheFirstOccurrenceInAString() {

    /**
     * Initial thoughts
     * Using two pointer, i and j
     *
     * loop through haystack with i, until the next few strings matches
     * - If yes, check i + 1 till i + m
     * - If not, go to i + 1
     *
     * Complexity:
     * - Time: O(n * m)
     * - Space: O(1)
     */
    override fun strStr(haystack: String, needle: String): Int {
        // 0. Corner cases
        if (needle.length > haystack.length) return -1
        if (needle.length == haystack.length) {
            return if (needle == haystack) {
                0
            } else {
                -1
            }
        }

        // 1. Init the variables
        var i = 0
        var j = 0

        // 2. Loop
        while (i < haystack.length - needle.length + 1) {
            var iterator = i
            while (j < needle.length && haystack[iterator] == needle[j]) {
                iterator++
                j++
            }

            // All the checks were successful
            if (j == needle.length) {
                return i
            } else {
                j = 0
                i++
            }
        }

        // 3. return default value
        return -1
    }

}