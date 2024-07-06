package string.easy

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Anagram
 * Two words are anagrams of one another if their letters can be rearranged to form the other word.
 * Given a string, split it into two contiguous substrings of equal length. Determine the minimum number
 * of characters to change to make the two substrings into anagrams of one another.
 *
 * Example
 *  s = abccde
 *  Break  into two parts: 'abc' and 'cde'. Note that all letters have been used, the substrings are
 *  contiguous and their lengths are equal. Now you can change 'a' and 'b' in the first substring to 'd'
 *  and 'e' to have 'dec' and 'cde' which are anagrams. Two changes were necessary.
 *
 *  Check: This problem is not in Leet code but it is similar to ValidAnagram
 */
class Anagram {

    /**
     * Initial analysis
     * Corner cases
     * if the length of the string is odd -> return -1
     *   1. Build a dictionary with first half of the characters
     *   2. for the second half, extract 1 per character
     *   3. Sum all the characters of the dict that are positive
     **/
    private fun anagram(s: String): Int {
        if (s.length % 2 != 0) return -1

        val dict = IntArray(26)
        s.forEachIndexed { index, element ->
            // First half
            if (index < s.length / 2) {
                dict[element - 'a']++
            } else {
                // Second half
                dict[element - 'a']--
            }
        }

        return dict.filter{ it > 0}.sum()
    }

    @Test
    fun test1() {
        // Given
        val string = "aaabbb"

        // When
        val result = anagram(string)

        // Then
        assertEquals(3, result)
    }

    @Test
    fun test2() {
        // Given
        val string = "ab"

        // When
        val result = anagram(string)

        // Then
        assertEquals(1, result)
    }

    @Test
    fun test3() {
        // Given
        val string = "abc"

        // When
        val result = anagram(string)

        // Then
        assertEquals(-1, result)
    }

    @Test
    fun test4() {
        // Given
        val string = "mnop"

        // When
        val result = anagram(string)

        // Then
        assertEquals(2, result)
    }

    @Test
    fun test5() {
        // Given
        val string = "xyyx"

        // When
        val result = anagram(string)

        // Then
        assertEquals(0, result)
    }

    @Test
    fun test6() {
        // Given
        val string = "xaxbbbxx"

        // When
        val result = anagram(string)

        // Then
        assertEquals(1, result)
    }

}