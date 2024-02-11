package arrayandlist.medium

import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Group anagram
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 *
 * typically using all the original letters exactly once.
 * Example 1:
 *      Input: strs = ["eat","tea","tan","ate","nat","bat"]
 *      Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * Example 2:
 *      Input: strs = [""]
 *      Output: [[""]]
 * Example 3:
 *      Input: strs = ["a"]
 *      Output: [["a"]]
 */
class GroupAnagram {
    /**
     * Idea: Group them in a hash map with their unique signature
     *       then return each list
     * This unique code is their hashcode
     */
    private fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val group = HashMap<String, MutableList<String>>()

        // Build the hashmap
        strs.forEach {
            val hash = createHash(it)
            val myList = group.getOrDefault(hash, mutableListOf())
            myList.add(it)
            group[hash] = myList
        }

        // Return the content
        return group.values.toList()
    }

    private fun createHash(word: String): String {
        // Create a dictionary
        val dict = IntArray(26)
        word.forEach {
            dict[it - 'a']++
        }

        val stringBuilder = StringBuilder()
        dict.foldIndexed(stringBuilder) {
            index, total, item -> if (item != 0) {
                repeat(item) {
                    total.append('a' + index)
                }
            total
            } else total
        }

        return stringBuilder.toString()
    }

    @Test
    fun test1() {
        // Given
        val params = arrayOf("bdddddddddd","bbbbbbbbbbc")

        // When
        val result = groupAnagrams(params)

        // Then
        assertEquals(listOf(listOf("bbbbbbbbbbc"),listOf("bdddddddddd")), result)
    }

    @Test
    fun test2() {
        // Given
        val params = arrayOf("eat","tea","tan","ate","nat","bat")

        // When
        val result = groupAnagrams(params)

        // Then
        assertEquals(listOf(listOf("bat"),listOf("nat", "tan"), listOf("ate", "eat", "tea")), result)
    }
}