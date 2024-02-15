package string.easy

import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.StringBuilder

/**
 * Add strings
 *
 * Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and
 * num2 as a string.
 * You must solve the problem without using any built-in library for handling large integers (such as BigInteger).
 * You must also not convert the inputs to integers directly.
 *
 * Example 1:
 *      Input: num1 = "11", num2 = "123"
 *      Output: "134"
 * Example 2:
 *      Input: num1 = "456", num2 = "77"
 *      Output: "533"
 *
 * Example 3:
 *      Input: num1 = "0", num2 = "0"
 *      Output: "0"
 *
 * Constraints:
 *      1 <= num1.length, num2.length <= 10^4
 *      num1 and num2 consist of only digits.
 *      num1 and num2 don't have any leading zeros except for the zero itself.
 */
class AddStrings {

    /**
     * Initial thought
     *
     * Divide and conquer
     *
     * Divide each one of the numbers and sum them independently
     * - Taking care if they need to carry any number
     */
    private fun addStrings(num1: String, num2: String): String {
        // 1. Init the variables
        val bigNumber = if (num1.length > num2.length) num1 else num2
        val smallerNumber = if (num1.length > num2.length) num2 else num1
        var smallerNumberIndex = smallerNumber.length - 1
        // Optionally we can create a stack to store the temporally values
        val sumStack = ArrayDeque<Char>()
        sumStack.addFirst('0')

        // 2. Loop
        for (bigNumberIndex in bigNumber.length - 1 downTo 0) {
            val previousRemaining = sumStack.removeFirst()
            val firstResult = addChar(bigNumber[bigNumberIndex], previousRemaining)

            if (smallerNumberIndex >= 0) {
                val secondResult = addChar(firstResult.first, smallerNumber[smallerNumberIndex])
                smallerNumberIndex--
                sumStack.addFirst(secondResult.first)
                if (secondResult.second == '1' || firstResult.second == '1') {
                    sumStack.addFirst('1')
                } else {
                    sumStack.addFirst('0')
                }
            // If there is not smaller number
            } else {
                // Adding the sum
                sumStack.addFirst(firstResult.first)
                // Adding the carry
                sumStack.addFirst(firstResult.second)
            }
        }

        // 3. Show result
        // If the first element is zero, then remove it
        if (sumStack.first() == '0') {
            sumStack.removeFirst()
        }
        val sb = StringBuilder()
        while (sumStack.isNotEmpty()) {
            sb.append(sumStack.removeFirst())
        }
        return sb.toString()
    }

    /**
     * Adding two chars
     * We can use a lot of cases here...
     * Or we can create an array of results
     * and iterate through
     * the result
     * - First: the actual sum
     * - Second: the carry
     */
    private fun addChar(char1: Char, char2: Char): Pair<Char, Char> {
        val resultsArray = mutableListOf<Pair<Char, Char>>()
        // Fill the results
        resultsArray.add(Pair('0', '0'))
        resultsArray.add(Pair('1', '0'))
        resultsArray.add(Pair('2', '0'))
        resultsArray.add(Pair('3', '0'))
        resultsArray.add(Pair('4', '0'))
        resultsArray.add(Pair('5', '0'))
        resultsArray.add(Pair('6', '0'))
        resultsArray.add(Pair('7', '0'))
        resultsArray.add(Pair('8', '0'))
        resultsArray.add(Pair('9', '0'))
        resultsArray.add(Pair('0', '1'))
        resultsArray.add(Pair('1', '1'))
        resultsArray.add(Pair('2', '1'))
        resultsArray.add(Pair('3', '1'))
        resultsArray.add(Pair('4', '1'))
        resultsArray.add(Pair('5', '1'))
        resultsArray.add(Pair('6', '1'))
        resultsArray.add(Pair('7', '1'))
        resultsArray.add(Pair('8', '1'))
        resultsArray.add(Pair('9', '1'))

        var index = 0
        index += calculateSteps(char1)
        index += calculateSteps(char2)

        return resultsArray[index]
    }

    private fun calculateSteps(step: Char): Int {
        return step - '0'
    }

    @Test
    fun test1() {
        // Given
        val num1 = "11"
        val num2 = "123"

        // When
        val result = addStrings(num1, num2)

        // Then
        assertEquals("134", result)
    }

    @Test
    fun test2() {
        // Given
        val num1 = "456"
        val num2 = "77"

        // When
        val result = addStrings(num1, num2)

        // Then
        assertEquals("533", result)
    }

    @Test
    fun test3() {
        // Given
        val num1 = "0"
        val num2 = "0"

        // When
        val result = addStrings(num1, num2)

        // Then
        assertEquals("0", result)
    }

    @Test
    fun test4() {
        // Given
        val num1 = "111"
        val num2 = "222"

        // When
        val result = addStrings(num1, num2)

        // Then
        assertEquals("333", result)
    }

}