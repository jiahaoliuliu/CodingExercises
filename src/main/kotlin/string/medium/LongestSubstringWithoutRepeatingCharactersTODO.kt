package string.medium

/**
 * Longest substring without repeating characters
 *
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *      Input: s = "abcabcbb"
 *      Output: 3
 *      Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 *      Input: s = "bbbbb"
 *      Output: 1
 *      Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 *      Input: s = "pwwkew"
 *      Output: 3
 *      Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 * Constraints:
 *      0 <= s.length <= 5 * 104
 *      s consists of English letters, digits, symbols and spaces.
 */
class LongestSubstringWithoutRepeatingCharactersTODO {

    /**
     * Initial though
     *
     * have 2 pointers
     *  -> Left = 0
     *  -> Right = 0
     * 1 hash set
     * - value: seen char
     * longest = 0
     *
     *  Loop
     *  while (right < s.length)
     *    check if s[right] exists in the hashset
     *     -> If so,
     *          - increase left until right
     *              - every time, remove s[left] from the hashmap
     *          - add s[right] in the hashset
     *     -> If not
     *          -> add s[right] in the hashset
     *          -> check if right - left + 1 is longer than longest
     *              -> if so, update longest
     *  return longest
     */
    private fun lengthOfLongestSubstring(s: String): Int {
        return -1
    }
}