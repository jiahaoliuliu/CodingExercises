package arrayandlist.medium

import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Product of array except self
 *
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all
 * the elements of nums except nums[i].
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 * Example 1:
 *      Input: nums = [1,2,3,4]
 *      Output: [24,12,8,6]
 *
 * Example 2:
 *      Input: nums = [-1,1,0,-3,3]
 *      Output: [0,0,9,0,0]
 *
 * Constraints:
 *      2 <= nums.length <= 10^5
 *      -30 <= nums[i] <= 30
 *  The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra
 * space for space complexity analysis.)
 */
class ProductOfArrayExceptSelf {

    private fun productExceptSelf(nums: IntArray): IntArray {
        // 1. Init the data
        val pre = IntArray(nums.size)
        pre[0] = 1
        val suff = IntArray(nums.size)
        suff[nums.size -1] = 1;

        // 2. Loop
        // prefix array
        nums.forEachIndexed {
                i, item ->
            if (i > 0)
                pre[i] = pre[i - 1] * nums[i - 1]
        }

        // suffix array
        for (i in nums.size - 2 downTo 0) { // From nums.length -2 to 0 (included)
            print(i)
            suff[i] = suff[i + 1] * nums[i + 1]
        }

        // collect the result
        val result = IntArray(nums.size)
        result.forEachIndexed {
                i, _ ->
            result[i] = pre[i] * suff[i]
        }

        return result
    }

    @Test
    fun test1() {
        // Given
        val input = intArrayOf(1, 2, 3, 4)

        // When
        val result = productExceptSelf(input)

        // Then
        assertTrue(intArrayOf(24, 12, 8, 6) contentEquals result)
    }

    @Test
    fun test2() {
        // Given
        val input = intArrayOf(-1,1,0,-3,3)

        // When
        val result = productExceptSelf(input)

        // Then
        assertTrue(intArrayOf(0, 0, 9, 0, 0) contentEquals result)
    }
}