package arrayandlist.easy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Longest common prefix
 *
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 *      Input: strs = ["flower","flow","flight"]
 *      Output: "fl"
 *
 * Example 2:
 *      Input: strs = ["dog","racecar","car"]
 *      Output: ""
 *      Explanation: There is no common prefix among the input strings.
 *
 * Constraints:
 *      1 <= strs.length <= 200
 *      0 <= strs[i].length <= 200
 *      strs[i] consists of only lowercase English letters.
 */
class LongestCommonPrefix {

    /**
     * Initial thoughts
     * ["flower","flow","flight"]
     *
     * Given the list
     * Carry the common prefix to the last element
     * 1. common prefix = first element
     *
     * 2. Loop (from 2nd element to the last)
     * two pointers
     * - common prefix pointer
     * - current string pointer
     *   Second loop to all the characters
     *       Stop when
     *       - || common prefix poiner reaches to the end
     *       - || current String pointer reaches to the end
     *       - || prefix[common] != currentString[pointer]
     *       Update the prefix with prefix.substring(0, common)
     *       optimization: if prefix == ""
     *       break the whole loop
     *
     * 3. Return
     * return the prefix
     *
     * Solution 2:
     * Corner cases:
     *      If the strs is empty return ""
     *      If the strs only have 1 item, return that item
     *
     * 1. Find the shortest string
     * 2. Create a function that by  given an array of strings and a prefix, check if
     * that prefix is valid for the array of strings
     * 3. Loop through the shortest string, across the whole array, using the function
     * created on step 2. Going from the whole string, shorting one char from the back
     * every time
     */
    private fun longestCommonPrefix(strs: Array<String>): String {
        if (strs.isEmpty()) {
            return ""
        }

        if (strs.size == 1) {
            return strs[0]
        }

        // Find the shortest element
        var shortestStringSize = Integer.MAX_VALUE
        var shortestString = ""
        strs.forEachIndexed{_, element ->
            if (element.length < shortestStringSize) {
                shortestStringSize = element.length
                shortestString = element

            }}

        for (charPosition in shortestString.length downTo 0) {
            val prefix = shortestString.substring(0, charPosition)
            if (samePrefix(strs, prefix)) {
                return prefix
            }
        }

        return ""
    }

    private fun samePrefix(strs: Array<String>, prefix: String): Boolean {
        for (string in strs) {
            if (prefix != string.substring(0, prefix.length)) {
                return false
            }
        }

        return true
    }

    @Test
    fun test1() {
        // Given
        val strs = arrayOf("flower","flow","flight")

        // When
        val result = longestCommonPrefix(strs)

        // Then
        assertEquals("fl", result)
    }

    @Test
    fun test2() {
        // Given
        val strs = arrayOf("dog","racecar","car")

        // When
        val result = longestCommonPrefix(strs)

        // Then
        assertEquals("", result)
    }
}