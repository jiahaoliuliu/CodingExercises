package string.easy

import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.StringBuilder

/**
 * Rearrange spaces between words
 *
 * You are given a string text of words that are placed among some number of spaces. Each word consists of one
 * or more lowercase English letters and are separated by at least one space. It's guaranteed that text contains
 * at least one word.
 *
 * Rearrange the spaces so that there is an equal number of spaces between every pair of adjacent words and that
 * number is maximized. If you cannot redistribute all the spaces equally, place the extra spaces at the end,
 * meaning the returned string should be the same length as text.
 *
 * Return the string after rearranging the spaces.
 *
 * Example 1:
 *      Input: text = "  this   is  a sentence "
 *      Output: "this   is   a   sentence"
 *      Explanation: There are a total of 9 spaces and 4 words. We can evenly divide the 9 spaces between
 *      the words: 9 / (4-1) = 3 spaces.
 *
 * Example 2:
 *      Input: text = " practice   makes   perfect"
 *      Output: "practice   makes   perfect "
 *      Explanation: There are a total of 7 spaces and 3 words. 7 / (3-1) = 3 spaces plus 1 extra space.
 *      We place this extra space at the end of the string.
 *
 * Constraints:
 *      1 <= text.length <= 100
 *      text consists of lowercase English letters and ' '.
 *      text contains at least one word.
 *
 */
class RearrangeSpacesBetweenWords {

    /**
     * initial thoughts
     * 1. analyze
     * 1.1 Looping the string to find the number of spaces and the number of words
     * 1.2 Create a list of strings to store the words
     * 1.3.Calculate the number of spaces after each word
     *
     * 2. Create a new empty string (empty array of chars, where by default it is an empty char)
     * Per each word
     * 1. Add the word
     * 2. Add the number of spaces
     *
     * 3. Return the new array concatenated
     */
    private fun reorderSpaces(text: String): String {
        // 1. Analysis
        var numberOfSpaces = 0
        val wordsList = mutableListOf<String>()
        val sb = StringBuilder()

        for (char in text) {
            if (char != ' ') {
                sb.append(char)
            } else {
                numberOfSpaces++
                if (sb.isNotEmpty()) {
                    wordsList.add(sb.toString())
                    sb.clear()
                }
            }
        }
        if (sb.isNotEmpty()) {
            wordsList.add(sb.toString())
            sb.clear()
        }

        // Corner case. There is not spaces in the word
        val numberOfEmptySpacesAfterWord =
            if (numberOfSpaces == 0) {
                0
            } else if (wordsList.size == 1) {
                numberOfSpaces
            } else {
                numberOfSpaces / (wordsList.size - 1)
            }

        // 2. Write word
        var charPosition = 0
        val myCharArray = CharArray(text.length){' '}
        // Even when there are two loops, actually the max loop length is N, where
        // N is the length of the "text", so this is considered as O(N)
        // instead of O(N^2)
        for (word in wordsList) {
            for (i in word.indices) {
                myCharArray[charPosition] = word[i]
                charPosition++
            }
            charPosition += numberOfEmptySpacesAfterWord
        }

        return myCharArray.joinToString("")
    }

    @Test
    fun test1() {
        // Given
        val input = "  this   is  a sentence "

        // When
        val result = reorderSpaces(input)

        // Then
        assertEquals("this   is   a   sentence", result)
    }

    @Test
    fun test2() {
        // Given
        val input = " practice   makes   perfect"

        // When
        val result = reorderSpaces(input)

        // Then
        assertEquals("practice   makes   perfect ", result)
    }

    @Test
    fun test3() {
        // Given
        val input = "a"

        // When
        val result = reorderSpaces(input)

        // Then
        assertEquals("a", result)
    }

    @Test
    fun test4() {
        // Given
        val input = "  hello"

        // When
        val result = reorderSpaces(input)

        // Then
        assertEquals("hello  ", result)
    }
}