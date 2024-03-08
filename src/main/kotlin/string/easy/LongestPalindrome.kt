package string.easy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Longest palindrome
 *
 * Given a string s which consists of lowercase or uppercase letters, return the length of the longest
 * palindrome that can be built with those letters.
 *
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome here.
 * Example 1:
 *      Input: s = "abccccdd"
 *      Output: 7
 *      Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
 *
 * Example 2:
 *      Input: s = "a"
 *      Output: 1
 *      Explanation: The longest palindrome that can be built is "a", whose length is 1.
 *
 * Constraints:
 *      1 <= s.length <= 2000
 *      s consists of lowercase and/or uppercase English letters only.
 */
abstract class LongestPalindrome {

    abstract fun longestPalindrome(s: String): Int

    @Test
    fun test1() {
        // Given
        val string = "abccccdd"

        // When
        val result = longestPalindrome(string)

        // Then
        assertEquals(7, result)
    }

    @Test
    fun test2() {
        // Given
        val string = "a"

        // When
        val result = longestPalindrome(string)

        // Then
        assertEquals(1, result)
    }
}

class LongestPalindromeImpl : LongestPalindrome() {

    /**
     * Initial thoughts
     *
     * Since the palindrome could be build with any element in the string
     * the order of them doesn't matter
     *
     * We can count the number of each char
     * - If it is even: add it
     * - If it is odd: add (it - 1)
     *
     * At the end, we can possibly append one char in the middle if possible
     * - If final length < s.length -> final length += 1
     *
     * Complexity:
     * - Time: O(n), where n is the length of the string
     * - Space: O(128)
     */
    override fun longestPalindrome(s: String): Int {
        // 1. Build word count
        val charCount = IntArray(128)
        s.forEach {
            charCount[it.code]++
        }

        // 2. Check for palindrome
        var maxLength = 0
        for (count in charCount) {
            if (count % 2 == 0) {
                maxLength += count
            } else {
                maxLength += count - 1
            }
        }

        // 3. Final check
        if (maxLength < s.length) {
            maxLength++
        }

        return maxLength
    }
}