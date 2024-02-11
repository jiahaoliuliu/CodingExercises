package string.medium

import junit.framework.Assert.assertTrue
import org.junit.Test

/**
 * Find all anagrams in a string
 *
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s.
 * You may return the answer in any order.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 * Example 1:
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 *  The substring with start index = 0 is "cba", which is an anagram of "abc".
 *  The substring with start index = 6 is "bac", which is an anagram of "abc".
 *
 * Example 2:
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 *  The substring with start index = 0 is "ab", which is an anagram of "ab".
 *  The substring with start index = 1 is "ba", which is an anagram of "ab".
 *  The substring with start index = 2 is "ab", which is an anagram of "ab".
 *
 */
class FindAllAnagramsInAString {

    /**
     * initial analysis
     * 1. Create a list of substring of s, where each
     *    one of the substring have size p.length
     * 2. For each one of the items, check if it is anagram with
     *    p. If so, add the position to the index.
     *       If not, continue
     * Optimal solution (Sliding windows)
     * - 2 pointers
     *   - i = 0
     *   - j = 0
     *   - Create empty result String
     * - Loop (while j < array.length)
     *   - Init Loop (until result.size = p.size)
     *      - add the value of array[j] to the list
     *      - j++
     *   - Check if result is anagram of p
     *     - if so, add i to the positions list
     *     - i++
     *     - drop the last char in the result
     *
     * Optimal solution over previous optimal solution
     *   - Update the result with maintaining the previous value
     *   - If a char is dropped, do not restart from beginning
     *   - If a char is added, do not restart from beginning
     */
    private fun findAnagrams(s: String, p: String): List<Int> {
        // Build the dict for p
        val dicP = IntArray(26)
        p.forEach {
            dicP[it - 'a']++
        }

        var i = 0
        var size = 0
        val result = mutableListOf<Int>()

        val dicS = IntArray(26)
        s.forEachIndexed {
                index, item ->
            // Add next element to the dictionary
            dicS[item - 'a'] ++
            size++
            // If the number of elements in the dict is the same as the p
            if (size == p.length) {
                // Check if they are equals
                if (dicS contentEquals  dicP) {
                    result.add(i)
                }

                // Drop the last element
                dicS[s[i] - 'a']--
                size--
                i++
            }
        }
        return result
    }

    @Test
    fun test1() {
        // Given
        val s = "cbaebabacd"
        val p = "abc"

        // When
        val result = findAnagrams(s, p)

        // Then
        assertTrue(listOf(0, 6) == result)
    }

    @Test
    fun test2() {
        // Given
        val s = "abab"
        val p = "ab"

        // When
        val result = findAnagrams(s, p)

        // Then
        assertTrue(listOf(0, 1, 2) == result)
    }
}