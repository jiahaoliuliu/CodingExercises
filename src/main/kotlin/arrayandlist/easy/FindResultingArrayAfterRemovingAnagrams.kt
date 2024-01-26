package arrayandlist.easy

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test

/**
 * You are given a 0-indexed string array words, where words[i] consists of lowercase English letters.
 * In one operation, select any index i such that 0 < i < words.length and words[i - 1] and words[i] are anagrams,
 * and delete words[i] from words. Keep performing this operation as long as you can select an index that satisfies
 * the conditions.

 * Return words after performing all operations. It can be shown that selecting the indices for each operation in
 * any arbitrary order will lead to the same result.

 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase using all the
 * original letters exactly once. For example, "dacb" is an anagram of "abdc".
 * Example 1:
 *  Input: words = ["abba","baba","bbaa","cd","cd"]
 *  Output: ["abba","cd"]
 *  Explanation:
 *      One of the ways we can obtain the resultant array is by using the following operations:
 *      - Since words[2] = "bbaa" and words[1] = "baba" are anagrams, we choose index 2 and delete words[2].
 *      Now words = ["abba","baba","cd","cd"].
 *      - Since words[1] = "baba" and words[0] = "abba" are anagrams, we choose index 1 and delete words[1].
 *      Now words = ["abba","cd","cd"].
 *      - Since words[2] = "cd" and words[1] = "cd" are anagrams, we choose index 2 and delete words[2].
 *      Now words = ["abba","cd"].
 *      We can no longer perform any operations, so ["abba","cd"] is the final answer.
 *
 * Example 2:
 *  Input: words = ["a","b","c","d","e"]
 *  Output: ["a","b","c","d","e"]
 *  Explanation:
 *      No two adjacent strings in words are anagrams of each other, so no operations are performed.
 */
class FindResultingArrayAfterRemovingAnagrams {

    /**
     * Initial thought
     * The idea is moving from position 0 until position n -1, where n is the size of the array
     *  because if words[i - 1] is not longer a valid anagram, words[i - n], until n = i, won't be a valid
     *  anagram for words[i]
     *
     *  Now, we are going to need an extra method to check if two strings are anagram
     *
     *  Algorithm:
     *  - Going from position n-1 to 1, where n is the size of the array
     *      - have 2 pointers:
     *          - i = current element pointer
     *          - j = next element to check
     *      - Init the value
     *          - i = 0
     *          - j = i + 1
     *      - loop (while j < array.size)
     *          - check if words[i] and words[j] are anagrams
     *              - if they are not anagrams
     *                  - add word[i] to the result
     *                  - reset i to j (i = j)
     *          - advance j (j = i + 1)
     *      - end loop
     *          - add word[i] to the beginning of the result
     *          - return the result

     */
    private fun removeAnagrams(words: Array<String>): List<String> {
        val result = mutableListOf<String>()
        var i = 0
        var j = i + 1
        // TODO optimize this
        while (j < words.size) {
            if (!words[i].isAnagramTo(words[j])) {
                result.add(words[i])
                i = j
            }
            j++
        }
        result.add(words[i])
        return result
    }

    private fun String.isAnagramTo(word2: String): Boolean {
        val dict = IntArray(26)
        // Build the dictionary
        this.forEach {
            dict[it - 'a']++
        }

        // Check
        word2.forEach {
            dict[it - 'a']--
        }

        return dict.all { it == 0 }
    }

    @Test
    fun testAnagram1() {
        // Given
        val string1 = "abba"
        val string2 = "bbaa"

        // When
        val result = string1.isAnagramTo(string2)

        // Then
        assertTrue(result)
    }

    @Test
    fun testAnagram2() {
        // Given
        val string1 = "car"
        val string2 = "rat"

        // When
        val result = string1.isAnagramTo(string2)

        // Then
        assertFalse(result)
    }

    @Test
    fun test1() {
        // Given
        val params = arrayOf("abba","baba","bbaa","cd","cd")

        // When
        val result = removeAnagrams(params)

        // Then
        assertTrue(listOf("abba", "cd") == result)
    }

    @Test
    fun test2() {
        // Given
        val params = arrayOf("a","b","c","d","e")

        // When
        val result = removeAnagrams(params)

        // Then
        assertTrue(listOf("a","b","c","d","e") == result)
    }
}

class FindResultingArrayAfterRemovingAnagramsOptimized {

    /**
     * Optimization 1:
     *      Since we are comparing the first string with other strings always,
     *      we can build a dictionary of dictionary to be used later
     *      The result is that since we are operating on the existing dictionary,
     *      so the content of that dictionary needs to be cloned every time
     *      thus this operation has the same cost than not optimization
     */
    private val dictOfDict = HashMap<String, IntArray>()
    private fun removeAnagrams(words: Array<String>): List<String> {
        val result = mutableListOf<String>()
        var i = 0
        words.forEachIndexed { index, element ->
            if (!words[i].isAnagramTo(element)) {
                result.add(words[i])
                i = index
            }
        }
        result.add(words[i])
        return result
    }

    // If the dictionary is null, init it and store it
    // if not, use it
    private fun String.isAnagramTo(word2: String): Boolean {
        val dict: IntArray by lazy {
            dictOfDict[this@isAnagramTo]
                ?: IntArray(26).apply {
                    this@isAnagramTo.forEach { char ->
                        this[char - 'a']++
                    }
                }
        }
        // the value of dict is going to be changed later
        // so it need to be cloned
        // This is an expensive operation, so at the end
        // the result is the same as before optimization :(
        dictOfDict[this] = dict.clone()

        // Check
        word2.forEach {
            dict[it - 'a']--
        }

        return dict.all { it == 0 }
    }

    @Test
    fun testAnagram1() {
        // Given
        val string1 = "abba"
        val string2 = "bbaa"

        // When
        val result = string1.isAnagramTo(string2)

        // Then
        assertTrue(result)
    }

    @Test
    fun testAnagram2() {
        // Given
        val string1 = "car"
        val string2 = "rat"

        // When
        val result = string1.isAnagramTo(string2)

        // Then
        assertFalse(result)
    }

    @Test
    fun test1() {
        // Given
        val params = arrayOf("abba","baba","bbaa","cd","cd")

        // When
        val result = removeAnagrams(params)

        // Then
        assertTrue(listOf("abba", "cd") == result)
    }

    @Test
    fun test2() {
        // Given
        val params = arrayOf("a","b","c","d","e")

        // When
        val result = removeAnagrams(params)

        // Then
        assertTrue(listOf("a","b","c","d","e") == result)
    }
}