package string.easy

/**
 * Reverse words in a String III
 *
 * Given a string s, reverse the order of characters in each word within a sentence while
 * still preserving whitespace and initial word order.
 *
 * Example 1:
 *      Input: s = "Let's take LeetCode contest"
 *      Output: "s'teL ekat edoCteeL tsetnoc"
 *
 * Example 2:
 *      Input: s = "Mr Ding"
 *      Output: "rM gniD"
 *
 * Constraints:
 *      1 <= s.length <= 5 * 10^4
 *      s contains printable ASCII characters.
 *      s does not contain any leading or trailing spaces.
 *      There is at least one word in s.
 *      All the words in s are separated by a single space.
 */
class ReverseWordsInAStringIIITODO {

    /**
     * Initial thoughts
     *
     * 1. Create a list of String by split the original text by " "
     * 2. Create a String builder
     * 3. Per each string in the list, reverse it and add it to the string builder
     * 4. Return string builder to string
     *
     * Complexity:
     * - Time: O(n)
     * - Space: O(n)
     *
     * A better approach:
     * 1. Using sliding windows to find the limit of each word
     * 2. Use two pointers, one at the beginning of the word and one at the end of the word
     *  start reversing them
     * Complexity:
     * - Time: O(n)
     * - Space: O(1)
     */
    private fun reverseWords(s: String): String {
        return ""
    }
}