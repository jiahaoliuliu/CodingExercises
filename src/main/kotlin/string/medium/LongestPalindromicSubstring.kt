package string.medium

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

/**
 * Longest palindromic substring
 *
 * Given a string s, return the longest palindromic substring in s.
 *
 * Example 1:
 *      Input: s = "babad"
 *      Output: "bab"
 *      Explanation: "aba" is also a valid answer.
 *
 * Example 2:
 *      Input: s = "cbbd"
 *      Output: "bb"
 *
 * Constraints:
 *      1 <= s.length <= 1000
 *      s consist of only digits and English letters.
 */
abstract class LongestPalindromicSubstring {

    abstract fun longestPalindrome(s: String): String

    @ParameterizedTest(name = "The longest palindromic substring of {0} should be {1}")
    @MethodSource("getData")
    fun test(input: String, expectedValue: String) {
        val result = longestPalindrome(input)
        assertEquals(expectedValue, result)
    }

    companion object {
        @JvmStatic
        fun getData(): List<Array<Any>> {
            return listOf(
                arrayOf("babad", "bab"),
                arrayOf("cbbd", "bb"),
                arrayOf("bb", "bb"),
                arrayOf("abbbaddaa", "abbba")
            )
        }
    }
}

class LongestPalindromicSubstringImpl: LongestPalindromicSubstring() {

    /**
     * Initial thought
     *
     * 1. Create a method that given a string, left and right pointer,
     * expand left and right and return the length of the longest string
     *
     * 2. Loop
     * - For each one of position in the string
     *  - 2.1 get the length of the longest string from the position i, i
     *  - 2.2 get the length of the longest string from the position i, i+1
     *  - get the max on step 2.1 and 2.2
     *  - if this max is longer than existing value
     *   -> Update the left pointer
     *   -> Update the right pointer
     *
     *  Return the substring between left pointer and right pointer
     *
     *  O(n^2)
     */
    override fun longestPalindrome(s: String): String {
        // 1. Prepare the data
        var longestPalindrome = ""

        // 2. Loop
        for (pos in s.indices) {
            val longestPalindromeTmp = longestPalindromeTmp(s, pos)
            if (longestPalindromeTmp.length > longestPalindrome.length) {
                longestPalindrome = longestPalindromeTmp
            }
        }

        return longestPalindrome
    }

    private fun longestPalindromeTmp(s: String, pos: Int): String {
        // 1. Init the values
        var longestPalindrome:String = s[pos].toString()
        var leftPointer = pos - 1
        var rightPointer = pos + 1

        // 2. Loop
        while(leftPointer >= 0 && rightPointer <= s.length) {
            val substring = getSubstring(s, leftPointer, rightPointer)
            if (isPalindrome(substring)) {
                if (substring.length > longestPalindrome.length) {
                    longestPalindrome = substring
                }
            } else {
                break // Break while because if the current substring is not palindrome
                // the next substrings won't be palindrome neither
            }

            leftPointer--
            rightPointer++
        }

        return longestPalindrome
    }

    private fun getSubstring(s: String, leftPointer: Int, rightPointer: Int): String {
        val substring: String = if (rightPointer < s.length) {
            val substringInclusive = s.substring(leftPointer, rightPointer + 1)
            if (isPalindrome(substringInclusive)) {
                substringInclusive
            } else s.substring(leftPointer, rightPointer)
        } else {
            s.substring(leftPointer, rightPointer)
        }
        return substring
    }
    private fun isPalindrome(s: String): Boolean {
        // 11 -> 5
        // 10 -> 5
        // 0 1 2 3 4 5 6 = Length = 7
        // a b b a b b a
        val middleLength = s.length / 2
        for (i in 0 .. middleLength) {
            if (s[i] != s[s.length - i - 1]) {
                return false
            }
        }

        return true
    }

    @Test
    fun isPalindromeTest1() {
        // Given
        val s = "abba"

        // When
        val result = isPalindrome(s)

        // Then
        assertTrue(result)
    }

    @Test
    fun isPalindromeTest2() {
        // Given
        val s = "abcba"

        // When
        val result = isPalindrome(s)

        // Then
        assertTrue(result)
    }

    @Test
    fun isPalindromeTest3() {
        // Given
        val s = "abcca"

        // When
        val result = isPalindrome(s)

        // Then
        assertFalse(result)
    }
}

class LongestPalindromicSubstringOptim: LongestPalindromicSubstring() {

    /**
     * An optimization over the previous solution is using the property of palindrome directly
     * For "abba", if "bb" is palindrome, then to check if "abba" is palindrome, only the initial
     * "a" and the last "a" needs to be checked.
     *
     * "abbbaddaa": "abbba"
     *
     * String        [ b a b d]
     * Init              ^
     * Left Pointer.   ^
     * Right pointer       ^
     *
     */
    override fun longestPalindrome(s: String): String {
        // 1. Init the values
        var leftPointer = 0
        var rightPointer = 0

        for (i in s.indices) {
            val (leftPointerTmp, rightPointerTmp) = getLongestPalindrome(s, i)
            if ((rightPointerTmp - leftPointerTmp) > (rightPointer - leftPointer)) {
                leftPointer = leftPointerTmp
                rightPointer = rightPointerTmp
            }
        }

        // Check the values. Substring does not include the rightest char
        return s.substring(leftPointer, rightPointer + 1)
    }

    private fun getLongestPalindrome(s: String, initialPosition: Int): Pair<Int, Int> {
        // Calculate for the current char
        val (leftPointerSingle, rightPointerSingle) = getLongestPalindrome(s, initialPosition, initialPosition)
        // Calculate for the current char and the previous char
        val (leftPointerDouble, rightPointerDouble) = if (initialPosition > 0 && s[initialPosition] == s[initialPosition - 1]) {
            getLongestPalindrome(s, initialPosition - 1, initialPosition)
        } else {
            Pair(initialPosition, initialPosition)
        }

        if ((rightPointerDouble - leftPointerDouble) > (rightPointerSingle - leftPointerSingle)) {
            return Pair(leftPointerDouble, rightPointerDouble)
        } else {
            return Pair(leftPointerSingle, rightPointerSingle)
        }
    }

    private fun getLongestPalindrome(s: String, leftPointer: Int, rightPointer: Int): Pair<Int, Int> {
        var nextLeftPointer = leftPointer
        var nextRightPointer = rightPointer

        // While left char exists and right char exists and they are the same
        // confirm and update the next values
        while (nextLeftPointer - 1 >= 0 && nextRightPointer + 1 < s.length &&
            s[nextLeftPointer - 1] == s[nextRightPointer + 1]) {
            nextLeftPointer--
            nextRightPointer++
        }

        return Pair(nextLeftPointer, nextRightPointer)
    }
}