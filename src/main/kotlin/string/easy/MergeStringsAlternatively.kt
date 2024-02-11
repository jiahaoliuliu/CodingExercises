package string.easy

import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.Integer.min

/**
 * Merging strings alternatively
 *
 * You are given two strings word1 and word2. Merge the strings by adding letters in alternating order,
 * starting with word1. If a string is longer than the other, append the additional letters onto the end
 * of the merged string.
 *
 * Return the merged string.
 *
 * Example 1:
 *      Input: word1 = "abc", word2 = "pqr"
 *      Output: "apbqcr"
 *      Explanation: The merged string will be merged as so:
 *          word1:  a   b   c
 *          word2:    p   q   r
 *          merged: a p b q c r
 *
 * Example 2:
 *      Input: word1 = "ab", word2 = "pqrs"
 *      Output: "apbqrs"
 *      Explanation: Notice that as word2 is longer, "rs" is appended to the end.
 *          word1:  a   b
 *          word2:    p   q   r   s
 *          merged: a p b q   r   s
 *
 * Example 3:
 *      Input: word1 = "abcd", word2 = "pq"
 *      Output: "apbqcd"
 *      Explanation: Notice that as word1 is longer, "cd" is appended to the end.
 *          word1:  a   b   c   d
 *          word2:    p   q
 *          merged: a p b q c   d
 */
class MergeStringsAlternatively {

    /**
     * Initial through
     * 1. Create an array of char
     * 2. Place word1 in the array
     * 3. Place word2 in the array
     * 4. Return the array joining as string
     *
     * Corner cases
     * When the length of word 1 is bigger than the length of word 2
     *  - word1
     *      - For words inside the predictive length
     *          - Do normal calculation
     *      - For position > length(word1) + length(word2)
     *          - array[position] = length(word2) - length(word1) + position
     *  - word2
     *      - Placed all the words
     *  ...
     * Alternative
     * - Iterate through the array
     * For position in Array
     * - If position < length(word1) + length(word2)
     *  - Place the words alternatively
     * - else
     *      - if length(word1) > length(word2)
     *          -> array[position] = word1[position - length(word2)]
     *      - else
     *          -> array[position] = word2[position - length(word1)]
     */
    private fun mergeAlternately(word1: String, word2: String): String {
        val result = CharArray(word1.length + word2.length)
        val minLength = min(word1.length, word2.length) * 2
        result.forEachIndexed {index, _ ->
            if (index < minLength) {
                if (index % 2 == 0) {
                    result[index] = word1[index / 2 ]
                } else {
                    result[index] = word2[index / 2 ]
                }
            } else {
                // if the word 1 is longer than word 2
                // get only position from word 1
                if (word1.length > word2.length) {
                    result[index] = word1[index - word2.length]
                } else {
                    result[index] = word2[index - word1.length]
                }
            }
        }

        return result.joinToString("")
    }

    /**
     * Final alternative (from others. More efficient)
     * - Use string builder, instead of an array of chars
     * 1. Init
     * i1 = 0
     * i2 = 0
     * 2. loop (while i1 has not exceeded length of word1 and i2 has not exceed length of word 2)
     *  - sb append word1[i1]
     *  - i1++
     *  - sb append word2[i2]
     *  - i2++
     * 3. Get the rest of the words
     * If i1 has not exceeded the length of word1
     *      sb append word1 from i1
     * if i2 has not exceeded the length of word2
     *      sb append word2 from i2
     * return sb.toString()
     */
    private fun mergeAlternately2(word1: String, word2: String): String {
        var i1 = 0
        var i2 = 0
        val sb = StringBuilder()

        while (i1 < word1.length && i2 < word2.length) {
            sb.append(word1[i1])
            i1++
            sb.append(word2[i2])
            i2++
        }

        if (i1 < word1.length) {
            sb.append(word1.substring(i1))
        }

        if (i2 < word2.length) {
            sb.append(word2.substring(i2))
        }

        return sb.toString()
    }


    @Test
    fun test1() {
        // Given
        val word1 = "abc"
        val word2 = "pqr"

        // When
        val result = mergeAlternately(word1, word2)

        // Then
        val expected = "apbqcr"
        assertEquals(expected, result)
    }

    @Test
    fun test2() {
        // Given
        val word1 = "ab"
        val word2 = "pqrs"

        // When
        val result = mergeAlternately(word1, word2)

        // Then
        val expected = "apbqrs"
        assertEquals(expected, result)
    }

    @Test
    fun test3() {
        // Given
        val word1 = "pqrs"
        val word2 = "ab"

        // When
        val result = mergeAlternately(word1, word2)

        // Then
        val expected = "paqbrs"
        assertEquals(expected, result)
    }

    @Test
    fun test4() {
        // Given
        val word1 = "abc"
        val word2 = "pqr"

        // When
        val result = mergeAlternately2(word1, word2)

        // Then
        val expected = "apbqcr"
        assertEquals(expected, result)
    }

    @Test
    fun test5() {
        // Given
        val word1 = "ab"
        val word2 = "pqrs"

        // When
        val result = mergeAlternately2(word1, word2)

        // Then
        val expected = "apbqrs"
        assertEquals(expected, result)
    }

    @Test
    fun test6() {
        // Given
        val word1 = "pqrs"
        val word2 = "ab"

        // When
        val result = mergeAlternately2(word1, word2)

        // Then
        val expected = "paqbrs"
        assertEquals(expected, result)
    }
}