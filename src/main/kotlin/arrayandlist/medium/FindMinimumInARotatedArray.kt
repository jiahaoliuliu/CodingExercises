package arrayandlist.medium

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Find minimum in a roted array
 *
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array
 * nums = [0,1,2,4,5,6,7] might become:
 *      [4,5,6,7,0,1,2] if it was rotated 4 times.
 *      [0,1,2,4,5,6,7] if it was rotated 7 times.
 *      Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0],
 *      a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums of unique elements, return the minimum element of this array.
 * You must write an algorithm that runs in O(log n) time.
 * Example 1:
 *      Input: nums = [3,4,5,1,2]
 *      Output: 1
 *      Explanation: The original array was [1,2,3,4,5] rotated 3 times.
 *
 * Example 2:
 *      Input: nums = [4,5,6,7,0,1,2]
 *      Output: 0
 *      Explanation: The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 *
 * Example 3:
 *      Input: nums = [11,13,15,17]
 *      Output: 11
 *      Explanation: The original array was [11,13,15,17] and it was rotated 4 times.
 *
 * Constraints:
 *      n == nums.length
 *      1 <= n <= 5000
 *      -5000 <= nums[i] <= 5000
 *      All the integers of nums are unique.
 *      nums is sorted and rotated between 1 and n times.
 */
abstract class FindMinimumInASortedArray {

    abstract fun findMin(nums: IntArray): Int

    @Test
    fun test1() {
        // Given
        val list = intArrayOf(3,4,5,1,2)

        // When
        val result = findMin(list)

        // Then
        assertEquals(1, result)
    }

    @Test
    fun test2() {
        // Given
        val list = intArrayOf(4,5,6,7,0,1,2)

        // When
        val result = findMin(list)

        // Then
        assertEquals(0, result)
    }

    @Test
    fun test3() {
        // Given
        val list = intArrayOf(11,13,15,17)

        // When
        val result = findMin(list)

        // Then
        assertEquals(11, result)
    }

    @Test
    fun test4() {
        // Given
        val list = intArrayOf(2, 1)

        // When
        val result = findMin(list)

        // Then
        assertEquals(1, result)
    }

    @Test
    fun test5() {
        // Given
        val list = intArrayOf(2, 3, 4, 5, 1)

        // When
        val result = findMin(list)

        // Then
        assertEquals(1, result)
    }

    @Test
    fun test6() {
        // Given
        val list = intArrayOf(5, 1, 2, 3, 4)

        // When
        val result = findMin(list)

        // Then
        assertEquals(1, result)
    }
}

class FindMinimumInASortedArrayImpl: FindMinimumInASortedArray() {

    /**
     * Initial thoughts
     *
     * We can use the binary search for it
     *
     * Scenario 1:
     * - The element in the middle is the biggest among neighbours
     * [3,4,5,1,2] middle = 5
     * -> Go to right
     *
     * Scenario 2:
     * - The element in the middle is the smallest one among neighbours
     * [4,5,0,1,2] middle = 0
     * -> return 0
     *
     * Scenario 3:
     * - The element in the middle is bigger than left and smaller than right
     * [1, 2, 3, 4, 5]
     * -> Go to left
     * The algorithm needs also take into account the right element
     * Complexity:
     * - Time: O(log n)
     * - Space: O(1)
     */
    override fun findMin(nums: IntArray): Int {
        var left = 0
        var right = nums.size - 1

        // If the array has not been rotated
        if (nums[right] > nums[left]) return nums[left]

        while (left < right) {
            val middlePos = left + (right - left)/2
            // If the next element is smaller, then the next element is the minimum
            if (nums[middlePos + 1] < nums[middlePos]) return nums[middlePos + 1]
            // Else if the current number is smaller than the previous number, then the current number is the minimum
            else if (nums[middlePos] < nums[middlePos - 1]) return nums[middlePos]
            // Else split
            else if (nums[middlePos] > nums[right]) left = middlePos + 1
            else right = middlePos - 1
        }

        // If the number cannot be found
        return nums[left]
    }

}