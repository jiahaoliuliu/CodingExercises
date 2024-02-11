package string.easy

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Two Strings
 * Given two strings, determine if they share a common substring. A substring may be as small as one character.
 * Example 1
 *      s1 = 'and'
 *      s2 = 'art'
 * These share the common substring .
 *
 *  Example 2
 *      s1 = 'be'
 *      s2 = 'cat'
 * These do not share a substring.
 */
class TwoStrings {

    private fun twoStrings(s1: String, s2: String): String {
        // Corner cases
        if (s1.isEmpty()) {
            return "NO"
        }

        if (s2.isEmpty()) {
            return "NO"
        }

        // Build simple array
        val dictionary = ByteArray(26) { 0 }
        s1.toCharArray().forEach {
            dictionary[it - 'a'] = 1
        }

        s2.toCharArray().forEach {
            val one: Byte = 1
            if (dictionary[it - 'a'] == one) {
                return "YES"
            }
        }

        return "NO"

    }

    @Test
    fun test1() {
        // Given
        val string1 = "hello"
        val string2 = "world"

        // When
        val result = twoStrings(string1, string2)

        // Then
        assertEquals("YES", result)
    }

    @Test
    fun test2() {
        // Given
        val string1 = "hi"
        val string2 = "world"

        // When
        val result = twoStrings(string1, string2)

        // Then
        assertEquals("NO", result)
    }
}