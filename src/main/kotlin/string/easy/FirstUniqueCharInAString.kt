package string.easy

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * First unique char in a string
 * Given a string s, find the first non-repeating character in it and return its index. If it does not exist,
 * return -1.
 *
 * Example 1:
 *      Input: s = "leetcode"
 *      Output: 0
 *
 * Example 2:
 *      Input: s = "loveleetcode"
 *      Output: 2
 *
 * Example 3:
 *      Input: s = "aabb"
 *      Output: -1
 *
 * Constraints:
 *      1 <= s.length <= 10^5
 *      s consists of only lowercase English letters.
 */
class FirstUniqueCharInAString {

    /**
     * Initial thought
     * We can use a hashmap to know if a char has been repeated inside the string, but the order
     * will be lost in that case.
     * So in order to keep the order, we need another data structure like list, which the item will be
     * inserted one by one
     *
     * 1. Init the variables
     * - Create a hashmap (or a intArray)
     * - Create a list of chars (with positions)
     *
     * 2. Loop (through s)
     * - If the hashMap does not have the char
     *  - add the char and its position into the list
     * end if
     * - increase the frequency
     * end loop
     *
     * 3. Showing results
     * - Going through the list
     *  - If the char exist in the hashMap once
     *  - Return its position
     */
    private fun firstUniqChar(s: String): Int {
        /**
        * 1. Init the variables
        * - Create a hashmap (or a intArray)
        * - Create a list of chars (with positions)
        *
        **/
        val frequencyMap = HashMap<Char, Int>()
        val positionsList = mutableListOf<Pair<Char, Int>>()

        /**
        * 2. Loop (through s)
        * - If the hashMap does not have the char
        *  - add the char and its position into the list
        * end if
        * - increase the frequency
        * end loop
        **/
        for (pos in s.indices) {
            val char = s[pos]
            var frequency = frequencyMap.getOrDefault(char, 0)
            if (frequency == 0) {
                positionsList.add(Pair(char, pos))
            }
            frequencyMap[char] = ++frequency
        }

        /**
        * 3. Showing results
        * - Going through the list
        *  - If the char exist in the hashMap once
        *  - Return its position
        **/
        for (pair in positionsList) {
            val char = pair.first
            if (frequencyMap[char] == 1) {
                return pair.second
            }
        }
        return -1
    }

    @Test
    fun test1() {
        // Given
        val input = "leetcode"

        // When
        val result = firstUniqChar(input)

        // Then
        assertEquals(0, result)
    }

    @Test
    fun test2() {
        // Given
        val input = "loveleetcode"

        // When
        val result = firstUniqChar(input)

        // Then
        assertEquals(2, result)
    }

    @Test
    fun test3() {
        // Given
        val input = "aabb"

        // When
        val result = firstUniqChar(input)

        // Then
        assertEquals(-1, result)
    }
}