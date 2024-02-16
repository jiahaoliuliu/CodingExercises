package arrayandlist.medium

import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.Integer.max

/**
 * Maximum subarray
 *
 * Given an integer array nums, find the subarray with the largest sum, and return its sum.
 *
 * Example 1:
 *      Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 *      Output: 6
 *      Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 *
 * Example 2:
 *      Input: nums = [1]
 *      Output: 1
 *      Explanation: The subarray [1] has the largest sum 1.
 *
 * Example 3:
 *      Input: nums = [5,4,-1,7,8]
 *      Output: 23
 *      Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 *
 * Constraints:
 *      1 <= nums.length <= 10^5
 *      -10^4 <= nums[i] <= 10^4
 *
 * Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer
 * approach, which is more subtle.
 */
class MaximumSubarray {

    /**
     * There is an algorithm for it.
     *
     * It is called Kadane's algorithm (published on 1984)
     * https://en.wikipedia.org/wiki/Maximum_subarray_problem
     * def max_subarray(numbers):
     *      best_sum = - infinity
     *      current_sum = 0
     *      for x in numbers:
     *          current_sum = max(x, current_sum + x)
     *          best_sum = max(best_sum, current_sum)
     *
     *      return best_sum
     */
    private fun maxSubArray(nums: IntArray): Int {
        var bestSum = Int.MIN_VALUE
        var currentSum = 0
        for (num in nums) {
            currentSum = max(num, currentSum + num)
            bestSum = max(bestSum, currentSum)
        }

        return bestSum
    }

    @Test
    fun test1() {
        // Given
        val nums = intArrayOf(-2,1,-3,4,-1,2,1,-5,4)

        // When
        val result = maxSubArray(nums)

        // Then
        assertEquals(6, result)
    }

    @Test
    fun test2() {
        // Given
        val nums = intArrayOf(1)

        // When
        val result = maxSubArray(nums)

        // Then
        assertEquals(1, result)
    }

    @Test
    fun test3() {
        // Given
        val nums = intArrayOf(5,4,-1,7,8)

        // When
        val result = maxSubArray(nums)

        // Then
        assertEquals(23, result)
    }

    @Test
    fun test4() {
        // Given
        val nums = intArrayOf(-1)

        // When
        val result = maxSubArray(nums)

        // Then
        assertEquals(-1, result)
    }


}