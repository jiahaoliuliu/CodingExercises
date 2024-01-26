package hashmap.easy

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test

/**
 *
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.

 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 *  typically using all the original letters exactly once.
 */
class ValidAnagram {

    /**
     * Optimal solution for when the parameter are only lower case
     */
    private fun solution1(s: String, t: String): Boolean {
        // Corner case check
        if (s.length != t.length) {
            return false
        }

        // Create the map of 26 chars, since there are only 26 chars in English
        val map = IntArray(26)

        // Build the map with the chars
        s.toCharArray().forEach {
            map[it - 'a'] ++
        }

        // Check the chars against the map
        t.toCharArray().forEach {
            map[it - 'a'] --
        }

        // Check the result
        map.forEach{
            if (it != 0) return false
        }
        return true
    }

    private fun solution2(s: String, t:String): Boolean {
        // Corner case
        if (s.length != t.length) {
            return false
        }

        val charHashMap = HashMap<Char, Int>()

        // Build the char
        s.toCharArray().forEach {
            charHashMap[it] = charHashMap.getOrDefault(it, 0) + 1
        }

        // Check the result
        t.toCharArray().forEach {char ->
            var value = charHashMap.getOrDefault(char, 0)
            charHashMap[char] = --value
        }

        return charHashMap.filter {(_, v) ->
            v != 0
        }.isEmpty()
    }

    @Test
    fun test1() {
        // Given
        val original = "anagram"
        val check = "nagaram"

        // When
        val result1 = solution1(original, check)
        val result2 = solution2(original, check)

        // Then
        assertTrue(result1)
        assertTrue(result2)
    }

    @Test
    fun test2() {
        // Given
        val original = "rat"
        val check = "cat"

        // When
        val result1 = solution1(original, check)
        val result2 = solution2(original, check)

        // Then
        assertFalse(result1)
        assertFalse(result2)
    }

    @Test
    fun test3() {
        // Given
        val original = "CNBC"
        val check = "BCCN"

        // When
        // The solution 1 only works with lower case characters
//        val result1 = solution1(original, check)
        val result2 = solution2(original, check)

        // Then
//        assertTrue(result1)
        assertTrue(result2)
    }

    @Test
    fun test4() {
        // Given
        val original = "CNaBC"
        val check = "BCaCN"

        // When
        // The solution 1 only works with lower case characters
//        val result1 = solution1(original, check)
        val result2 = solution2(original, check)

        // Then
//        assertTrue(result1)
        assertTrue(result2)
    }
}