package string.easy

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.collections.ArrayDeque

/**
 * Remove all adjacent duplicates in string
 *
 * You are given a string s consisting of lowercase English letters. A duplicate removal consists of choosing
 * two adjacent and equal letters and removing them.
 *
 * We repeatedly make duplicate removals on s until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made. It can be proven that the answer
 * is unique.
 *
 * Example 1:
 *      Input: s = "abbaca"
 *      Output: "ca"
 *      Explanation: in "abbaca" we could remove "bb" since the letters are adjacent and equal,
 *      and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
 *
 * Example 2:
 *      Input: s = "azxxzy"
 *      Output: "ay"
 *
 * Constraints:
 *      1 <= s.length <= 10^5
 *      s consists of lowercase English letters.
 */
class RemoveAllAdjacentDuplicatesInString {

    /**
     * Initial though
     * - Given abbaca
     *  - First -> aaca
     *  - Second -> ca
     * The data input will be changing constantly. And at some point one data validated (the first 'a')
     * needs to be checked again, until the first position
     *
     * We can have two pointers:
     * - p1: pointing to the first element
     * - p2: Going through the string
     * Problem: How to return the result?
     * - We need to store the temporally result some structure, which
     *  - have a pointer to the last checked element
     *  - have the capacity to remove items
     *  - the last element added could be removed
     *
     *  We can use a stack for it
     */
    private fun removeDuplicates(s: String): String {
        // 1. Init data
        val stack = ArrayDeque<Char>()

        // 2. Loop
        for (myChar in s) {
            if (stack.isEmpty()) {
                stack.addLast(myChar)
            } else {
                // If they are the same char
                if (stack.last() == myChar) {
                    stack.removeLast()
                } else {
                    stack.addLast(myChar)
                }
            }
        }

        // 3. Return result
        return stack.joinToString("")
    }

    @Test
    fun tes1() {
        // Given
        val input = "abbaca"

        // When
        val result = removeDuplicates(input)

        // Then
        assertEquals("ca", result)
    }

    @Test
    fun tes2() {
        // Given
        val input = "azxxzy"

        // When
        val result = removeDuplicates(input)

        // Then
        assertEquals("ay", result)
    }

}