package arrayandlist.medium

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Find peak element
 *
 * A peak element is an element that is strictly greater than its neighbors.
 *
 * Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains
 * multiple peaks, return the index to any of the peaks.
 *
 * You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be
 * strictly greater than a neighbor that is outside the array.
 *
 * You must write an algorithm that runs in O(log n) time.
 * Example 1:
 *      Input: nums = [1,2,3,1]
 *      Output: 2
 *      Explanation: 3 is a peak element and your function should return the index number 2.
 *
 * Example 2:
 *      Input: nums = [1,2,1,3,5,6,4]
 *      Output: 5
 *      Explanation: Your function can return either index number 1 where the peak element is 2, or index
 *      number 5 where the peak element is 6.
 *
 * Constraints:
 *      1 <= nums.length <= 1000
 *      -2^31 <= nums[i] <= 2^31 - 1
 *      nums[i] != nums[i + 1] for all valid i.
 */
abstract class FindPeakElement {

    abstract fun findPeakElement(nums: IntArray): Int

    @Test
    fun test1() {
        // Given
        val list = intArrayOf(1, 2, 3, 1)

        // When
        val result = findPeakElement(list)

        // Then
        assertEquals(2, result)
    }

    @Test
    fun test2() {
        // Given
        val list = intArrayOf(1,2,1,3,5,6,4)

        // When
        val result = findPeakElement(list)

        // Then
        assertTrue(listOf(1, 5).contains(result))
    }
}

class FindPeakElementSimpleLinear: FindPeakElement() {

    override fun findPeakElement(nums: IntArray): Int {
        if (nums.isEmpty()) return -1
        if (nums.size == 1) return 0

        nums.forEachIndexed { index, i ->
            if ((index == 0 || nums[index - 1] <= i) &&
                (index == nums.size - 1 || nums[index + 1] <= i)) {
                return index
            }
        }

        return -1
    }
}

class FindPeakElementBinarySearch: FindPeakElement() {

    /**
     * The idea is using binary search
     * It goes to the part which the number is bigger
     * Complexity:
     * - Time: O(log n)
     * - Space: O(1)
     */
    override fun findPeakElement(nums: IntArray): Int {
        var left = 0
        var right = nums.size - 1

        while (left <= right) {
            val middle = left + (right - left)/2
            if ((middle == 0 || nums[middle - 1] <= nums[middle]) &&
                (middle == nums.size - 1 || nums[middle + 1] <= nums[middle])) {
                    return middle
            }

            if (middle == 0 || nums[middle - 1] <= nums[middle]) {
                left = middle + 1
            } else {
                right = middle - 1
            }
        }

        return -1
    }

}