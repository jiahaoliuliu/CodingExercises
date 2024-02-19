package string.medium

/**
 * Longest Repeating Character Replacement
 *
 * You are given a string s and an integer k. You can choose any character of the string and change it
 * to any other uppercase English character. You can perform this operation at most k times.
 *
 * Return the length of the longest substring containing the same letter you can get after performing
 * the above operations.
 *
 * Example 1:
 *      Input: s = "ABAB", k = 2
 *      Output: 4
 *      Explanation: Replace the two 'A's with two 'B's or vice versa.
 *
 * Example 2:
 *      Input: s = "AABABBA", k = 1
 *      Output: 4
 *      Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 *      The substring "BBBB" has the longest repeating letters, which is 4.
 *      There may exists other ways to achieve this answer too.
 *
 * Constraints:
 *      1 <= s.length <= 10^5
 *      s consists of only uppercase English letters.
 *      0 <= k <= s.length
 */
class LongestRepeatingCharacterReplacementTODO {

    /**
     * Initial thoughts
     *
     * It is about substrings, so we cannot sort the items
     *
     * Brute force:
     * Per each char in the string
     * - We replace the K elements, starting from the first element
     * - Calculate the length of the max substring
     *
     * Better approach
     * Use sliding windows
     * - Is windows valid?
     *  -> Using max k characters to replace the chars in the windows, all the chars will be the same
     *
     * - Condition to increase left
     *  -> The number of elements to be replaced to have all them as same character is > k
     *
     *  - Condition to increase right
     *  -> the number of elements to be replaced to have all them as same character is <= k
     *
     * 1. Create a function that given a frequency map
     * - returns the char with max frequency
     *
     * 2. init the variables
     * Create empty frequency map
     * - Key: Char
     * - Value: Frequency
     * Left = 0
     * longest repeating char count = 0
     *
     * 3. loop
     * for r from 0 to s.length - 1
     * - Using s[r] to update the frequency map
     * - while the difference between the windows size and the max frequency of the existing char is > K
     *  -> remove char s[l] from frequency map
     *  -> Increase L
     * Once the windows is stablish
     *  - longest repeating char count = max (itself, windows size)
     *
     *  4. return
     *  return  longest repeating char count
     */
    private fun characterReplacement(s: String, k: Int): Int {
        return -1
    }
}