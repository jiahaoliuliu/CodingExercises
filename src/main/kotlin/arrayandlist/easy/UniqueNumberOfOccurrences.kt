package arrayandlist.easy

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Unique number of occurrences
 *
 * Given an array of integers arr, return true if the number of occurrences of each value in the array is
 * unique or false otherwise.
 *
 * Example 1:
 *      Input: arr = [1,2,2,1,1,3]
 *      Output: true
 *      Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number
 *      of occurrences.
 *
 * Example 2:
 *      Input: arr = [1,2]
 *      Output: false
 *
 * Example 3:
 *      Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
 *      Output: true
 * Constraints:
 *      1 <= arr.length <= 1000
 *      -1000 <= arr[i] <= 1000
 */
class UniqueNumberOfOccurrences {

    /**
     * Initial thoughts
     *
     * 1. Create frequency map
     * - key: number
     * - value: its frequency
     *
     * 2. Check if all the frequencies are unique
     * - Convert it to set
     * - Compare the set size with the original size
     */
    private fun uniqueOccurrences(arr: IntArray): Boolean {
        // 1. Create frequency map
        val frequencyMap = arr.toList().groupingBy { it }.eachCount()

        // 2. Check values uniqueness
        return frequencyMap.values.toSet().size == frequencyMap.values.size
    }

    @Test
    fun test1() {
        // Given
        val arr = intArrayOf(1,2,2,1,1,3)

        // When
        val result = uniqueOccurrences(arr)

        // Then
        assertTrue(result)
    }

    @Test
    fun test2() {
        // Given
        val arr = intArrayOf(1,2)

        // When
        val result = uniqueOccurrences(arr)

        // Then
        assertFalse(result)
    }

    @Test
    fun test3() {
        // Given
        val arr = intArrayOf(-3,0,1,-3,1,1,1,-3,10,0)

        // When
        val result = uniqueOccurrences(arr)

        // Then
        assertTrue(result)
    }
}