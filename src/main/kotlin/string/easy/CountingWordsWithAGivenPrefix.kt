package string.easy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Counting Words With a Given Prefix
 *
 * You are given an array of strings words and a string pref.
 *
 * Return the number of strings in words that contain pref as a prefix.
 *
 * A prefix of a string s is any leading contiguous substring of s.
 *
 * Example 1:
 *      Input: words = ["pay","attention","practice","attend"], pref = "at"
 *      Output: 2
 *      Explanation: The 2 strings that contain "at" as a prefix are: "attention" and "attend".
 *
 * Example 2:
 *      Input: words = ["leetcode","win","loops","success"], pref = "code"
 *      Output: 0
 *      Explanation: There are no strings that contain "code" as a prefix.
 *
 * Constraints:
 *      1 <= words.length <= 100
 *      1 <= words[i].length, pref.length <= 100
 * words[i] and pref consist of lowercase English letters.
 *
 */
class CountingWordsWithAGivenPrefix {

    /**
     * Initial thought
     * Just checking the first substrings will be enough
     */
    private fun prefixCount(words: Array<String>, pref: String): Int {
        // 1. Init the variables
        var result = 0

        // 2. Loop
        for (word in words) {
            if (word.startsWith(pref)) {
                result++
            }
        }

        // 3. Return result
        return result
    }

    @Test
    fun test1() {
        // Given
        val words = arrayOf("pay","attention","practice","attend")
        val pref = "at"

        // When
        val result = prefixCount(words, pref)

        // Then
        assertEquals(2, result)
    }

    @Test
    fun test2() {
        // Given
        val words = arrayOf( "leetcode","win","loops","success")
        val pref = "code"

        // When
        val result = prefixCount(words, pref)

        // Then
        assertEquals(0, result)
    }
}