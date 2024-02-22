package arrayandlist.easy

import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Set mismatch
 *
 * You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately,
 * due to some error, one of the numbers in s got duplicated to another number in the set, which results
 * in repetition of one number and loss of another number.
 *
 * You are given an integer array nums representing the data status of this set after the error.
 *
 * Find the number that occurs twice and the number that is missing and return them in the form of an array.
 *
 * Example 1:
 *      Input: nums = [1,2,2,4]
 *      Output: [2,3]
 *
 * Example 2:
 *      Input: nums = [1,1]
 *      Output: [1,2]
 *
 * Constraints:
 *      2 <= nums.length <= 10^4
 *      1 <= nums[i] <= 10^4
 *
 */
class SetMismatch {

    /**
     * The nums is not sorted!
     *
     * 1. Create a boolean array of size nums.size
     * 2. For each item and position, check the index of value -1
     * - If it was false, set it as true
     * - If it was true, set the number as duplicated -> Add it to a variable
     *
     * 3. Loop through the boolean array and add the index +1 of the which the value is false
     * 4. Return the values
     */
    private fun findErrorNums(nums: IntArray): IntArray {
        // 1. Init variables
        val cache = BooleanArray(nums.size)
        var duplicated = -1
        var missing = -1

        // 2. Loop through
        nums.forEach {
            if (cache[it - 1]) {
                duplicated = it
            } else {
                cache[it - 1] = true
            }
        }

        // 3. Find the missing one
        cache.forEachIndexed { index, b ->
            if (!b) {
                missing = index + 1
            }
        }

        // 4. Return the result
        return intArrayOf(duplicated, missing)
    }

    @Test
    fun test1() {
        // Given
        val nums = intArrayOf(1,2,2,4)

        // When
        val result = findErrorNums(nums)

        // Then
        assertTrue(intArrayOf(2, 3) contentEquals result)
    }

    @Test
    fun test2() {
        // Given
        val nums = intArrayOf(1,1)

        // When
        val result = findErrorNums(nums)

        // Then
        assertTrue(intArrayOf(1, 2) contentEquals result)
    }

    @Test
    fun test3() {
        // Given
        val nums = intArrayOf(3, 2, 2)

        // When
        val result = findErrorNums(nums)

        // Then
        assertTrue(intArrayOf(2, 1) contentEquals result)
    }
}