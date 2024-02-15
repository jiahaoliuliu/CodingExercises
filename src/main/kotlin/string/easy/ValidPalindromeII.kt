package string.easy

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 * Example 1:
 *      Input: s = "aba"
 *      Output: true
 *
 * Example 2:
 *      Input: s = "abca"
 *      Output: true
 *      Explanation: You could delete the character 'c'.
 *
 * Example 3:
 *      Input: s = "abc"
 *      Output: false
 *
 * Constraints:
 *      1 <= s.length <= 10^5
 *      s consists of lowercase English letters.
 */
class ValidPalindromeII {

    /**
     * Initial thought
     *
     * What we know
     * - A Palindrome is a word that could be read the same way from left to right
     * and from right to left. ie. aba -> aba
     * - A palindrome's length could be even or odd
     *  - aba -> aba, abba -> abba
     * Another property of the palindrome is that the number of char should be odd except 1
     * -> aba -> a:2, b:1,  abba -> a:2, b:2
     *
     * Problem:
     * The palindrome could have max 1 extra char, that could be anywhere.
     * 1. Discard words that has more than 2 char that is odd
     *  aba -> correct, abca -> correct by discarding c or b. [a:2, b:1, c:1]
     *
     * 2. Check by removing any of the odd chars, the word is a palindrome or not
     * -> We need to generate many words
     * abbcbdba: [a:2, b:4, c:1, d:1]
     *      -> Removing c: not valid palindrome
     *      -> Removing d: not valid palindrome
     * abbcbbba: [a:2, b:5, c:1]
     *      -> Remove first b -> Not valid
     *      -> Remove second b -> Not valid
     *      -> Remove third b -> valid
     * It improves the algorithm, but not much
     *
     * 3. how about checking it as normal palindrome, and if the char does not match
     * -> try to move left one position to see if the char matches
     *  -> If so, continue (the app needs to go to another state)
     *  -> If not, stay on the left
     * -> try to move right one position to see if the char matches
     *  -> If so, continue (the app needs to go to another state)
     *  -> If not, return false
     * return true
     *
     * Optimized logic
     * 1. Create a basic palindrome function
     * 2. Loop
     * - for each left and right
     *   - If s[left] is equals to s[right]
     *      - true -> continue
     *      - false -> return validPalindrome(left + 1, right) or validPalindrome(left, right - 1)
     *  3. return
     *  by default, return true
     */
    private fun validPalindrome(s: String): Boolean {
        var left = 0
        var right = s.length - 1
        while (left < right) {
            if (s[left] == s[right]) {
                left++
                right--
            } else {
                return validPalindrome(s, left + 1, right) || validPalindrome(s, left, right - 1)
            }
        }

        return true
    }

    // Normal palindrome function
    private fun validPalindrome(s: String, i: Int, j: Int): Boolean {
        var myLeft = i
        var myRight = j
        while (myLeft < myRight) {
            if (s[myLeft] == s[myRight]) {
                myLeft++
                myRight--
            } else {
                return false
            }
        }

        return true
    }

    @Test
    fun test1() {
        // Given
        val input = "aba"

        // When
        val result = validPalindrome(input)

        // Then
        assertTrue(result)
    }

    @Test
    fun test2() {
        // Given
        val input = "abca"

        // When
        val result = validPalindrome(input)

        // Then
        assertTrue(result)
    }

    @Test
    fun test3() {
        // Given
        val input = "abc"

        // When
        val result = validPalindrome(input)

        // Then
        assertFalse(result)
    }

    @Test
    fun test4() {
        // Given
        val input = "deeee"

        // When
        val result = validPalindrome(input)

        // Then
        assertTrue(result)
    }

    @Test
    fun test5() {
        // Given
        val input = "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"

        // When
        val result = validPalindrome(input)

        // Then
        assertTrue(result)
    }
}