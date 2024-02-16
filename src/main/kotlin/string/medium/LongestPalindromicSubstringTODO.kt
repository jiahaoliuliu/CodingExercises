package string.medium

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
class LongestPalindromicSubstringTODO {

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
    private fun longestPalindrome(s: String): String {
        return ""
    }
}