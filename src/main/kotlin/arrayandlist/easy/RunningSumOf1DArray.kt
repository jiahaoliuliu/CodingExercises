package arrayandlist.easy

import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
 *
 * Return the running sum of nums.
 *
 * Example 1:
 *      Input: nums = [1,2,3,4]
 *      Output: [1,3,6,10]
 *      Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].
 *
 * Example 2:
 *      Input: nums = [1,1,1,1,1]
 *      Output: [1,2,3,4,5]
 *      Explanation: Running sum is obtained as follows: [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1].
 *
 * Example 3:
 *      Input: nums = [3,1,2,10,1]
 *      Output: [3,4,6,16,17]
 *
 * Constraints:
 *      1 <= nums.length <= 1000
 *      -10^6 <= nums[i] <= 10^6
 */
class RunningSumOf1DArray {

    private fun runningSum(nums: IntArray): IntArray {
        val result = IntArray(nums.size)
        result[0] = nums[0]

        for (i in 1 until nums.size) {
            result[i] = result[i-1] + nums[i]
        }

        return result
    }

    @Test
    fun test1() {
        // Given
        val nums = intArrayOf(1, 2, 3, 4)

        // When
        val result = runningSum(nums)

        // Then
        assertTrue(intArrayOf(1, 3, 6, 10) contentEquals result)
    }

    @Test
    fun test2() {
        // Given
        val nums = intArrayOf(1, 1, 1, 1, 1)

        // When
        val result = runningSum(nums)

        // Then
        assertTrue(intArrayOf(1, 2, 3, 4, 5) contentEquals result)
    }

    @Test
    fun test3() {
        // Given
        val nums = intArrayOf(3,1,2,10,1)

        // When
        val result = runningSum(nums)

        // Then
        assertTrue(intArrayOf(3,4,6,16,17) contentEquals result)
    }

}