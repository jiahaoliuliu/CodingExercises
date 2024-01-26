package string.easy

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 *
 * A student is taking a cryptography class and has found anagrams to be very useful. Two strings are
 * anagrams of each other if the first string's letters can be rearranged to form the second string.
 * In other words, both strings must contain the same exact letters in the same exact frequency.
 * For example, bacdc and dcbac are anagrams, but bacdc and dcbad are not.
 *
 * The student decides on an encryption scheme that involves two large strings. The encryption is dependent
 * on the minimum number of character deletions required to make the two strings anagrams. Determine this number.
 *
 * Given two strings,  and , that may or may not be of the same length, determine the minimum number of character
 * required to make  and  anagrams. Any characters can be deleted from either of the strings.
 *
 * Example
 *  a = 'cde'
 *  b = 'def'
 *  Delete  from  and  from  so that the remaining strings are  and  which are anagrams.
 *  This takes 4 character deletions.
 */
class MakeAnagram {

    /**
     * Initial analysis
     * We can build a dictionary with all the characters that are different
     * - For missing characters from string 1 to string 2 the number will be positive
     * - For extra characters from string 2 to string 1 the number will be negative
     * - Result: Convert the negative numbers in the map as positive and sum them to the positive ones
     */
    fun makeAnagram(a: String, b: String): Int {
        // Create hashmap
        val dict = IntArray(26)
        a.forEach {
            dict[it - 'a'] ++
        }

        b.forEach {
            dict[it - 'a']--
        }

        return dict.sumOf { if (it < 0) it * -1 else it }
    }

    @Test
    fun test1() {
        // Given
        val string1 = "cde"
        val string2 = "abc"

        // When
        val result = makeAnagram(string1, string2)

        // Then
        assertEquals(4, result)
    }

    @Test
    fun test2() {
        // Given
        val string1 = "fcrxzwscanmligyxyvym"
        val string2 = "jxwtrhvujlmrpdoqbisbwhmgpmeoke"

        // When
        val result = makeAnagram(string1, string2)

        // Then
        assertEquals(30, result)
    }

    @Test
    fun test3() {
        // Given
        val string1 = "showman"
        val string2 = "woman"

        // When
        val result = makeAnagram(string1, string2)

        // Then
        assertEquals(2, result)
    }

}