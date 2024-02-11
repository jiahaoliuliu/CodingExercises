package string.easy

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Valid word abbreviation
 *
 * A string can be abbreviated by replacing any number of non-adjacent, non-empty substrings with their lengths.
 * The lengths should not have leading zeros.
 *
 * For example, a string such as "substitution" could be abbreviated as (but not limited to):
 *     - "s10n" ("s ubstitutio n")
 *     - "sub4u4" ("sub stit u tion")
 *     - "12" ("substitution")
 *     - "su3i1u2on" ("su bst i t u ti on")
 *     - "substitution" (no substrings replaced)
 * The following are not valid abbreviations:
 *     - "s55n" ("s ubsti tutio n", the replaced substrings are adjacent)
 *     - "s010n" (has leading zeros)
 *     - "s0ubstitution" (replaces an empty substring)
 * Given a string word and an abbreviation abbr, return whether the string matches the given abbreviation.
 *
 * A substring is a contiguous non-empty sequence of characters within a string.
 *
 * Example 1:
 *     Input: word = "internationalization", abbr = "i12iz4n"
 *     Output: true
 *     Explanation: The word "internationalization" can be abbreviated as "i12iz4n" ("i nternational iz atio n").
 *
 * Example 2:
 *    Input: word = "apple", abbr = "a2e"
 *    Output: false
 *    Explanation: The word "apple" cannot be abbreviated as "a2e".
 */
class ValidWordAbbreviation {

    /**
     * Initial thoughts
     * Have a pointer going through the word, iterate it as a list
     *  - variable i = 0
     * Have a pointer going through the abbr, iterate it as a list
     *  - variable j = 0
     *
     * For all the chars in j
     * if it is character
     *  Check if it is the same as word[i]
     *  -> If so, continue
     *  -> If not, return false
     *
     *  if it is number
     *  - create a variable k that is equals to j
     *  - until abbr[k] is not a number or end of the list
     *      - k++
     *  - Parse the data from j to k to a number
     *    - If the first char is 0 -> return false
     *  - on the number parsed (n)
     *    - advance i n position
     *    - if n is bigger than the length of the word
     *      - return false
     *
     *  Corner cases
     *  - When the number is 1
     *  - When the number is 0
     *
     * Complexity:
     * Time: O(n) where n is the size of abbr string
     * Space: O(1) since the check is done in-place
     */
    private fun validWordAbbreviation(word: String, abbr: String): Boolean {
        var i = 0
        var extraLength = 0
        abbr.forEach {
            if (it.isDigit()) {
                val number:Int = Character.getNumericValue(it)
                if (number == 0 && extraLength == 0) {
                    return false
                } else {
                    extraLength = extraLength * 10 + number
                }
            } else {
                if (extraLength != 0) {
                    i += extraLength
                    extraLength = 0
                }
                // If the position is bigger than the word length, return false
                if (i > word.length - 1) {
                    return false
                }
                if (word[i] != it) {
                    return false
                }
                // Check for next position
                i++
            }
        }
        // Return true only if all the chars in word has been checked
        // As the abbr could end up as number, the extra length will be checked as well
        return (i + extraLength) == word.length
    }

    @Test
    fun test1() {
        // Given
        val word = "internationalization"
        val abbr = "i12iz4n"

        // When
        val result = validWordAbbreviation(word, abbr)

        // Then
        assertTrue(result)
    }

    @Test
    fun test2() {
        // Given
        val word = "internationalization"
        val abbr = "i012iz4n"

        // When
        val result = validWordAbbreviation(word, abbr)

        // Then
        assertFalse(result)
    }

    @Test
    fun test3() {
        // Given
        val word = "apple"
        val abbr = "a3e"

        // When
        val result = validWordAbbreviation(word, abbr)

        // Then
        assertTrue(result)
    }

    @Test
    fun test4() {
        // Given
        val word = "apple"
        val abbr = "a2e"

        // When
        val result = validWordAbbreviation(word, abbr)

        // Then
        assertFalse(result)
    }

    @Test
    fun test5() {
        // Given
        val word = "a"
        val abbr = "2"

        // When
        val result = validWordAbbreviation(word, abbr)

        // Then
        assertFalse(result)
    }

    @Test
    fun test6() {
        // Given
        val word = "internationalization"
        val abbr = "i5a11o1"

        // When
        val result = validWordAbbreviation(word, abbr)

        // Then
        assertTrue(result)
    }

}