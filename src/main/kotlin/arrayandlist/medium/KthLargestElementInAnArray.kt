package arrayandlist.medium

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.PriorityQueue

/**
 * Kth largest element in an array
 *
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 *
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Can you solve it without sorting?
 * Example 1:
 *      Input: nums = [3,2,1,5,6,4], k = 2
 *      Output: 5
 *
 * Example 2:
 *      Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 *      Output: 4
 *
 * Constraints:
 *      1 <= k <= nums.length <= 10^5
 *      -10^4 <= nums[i] <= 10^4
 */
class KthLargestElementInAnArray {

    /**
     * Initial thoughts
     *
     * Easy solution: build an max heap and return the kth largest element
     *
     * Another solution:
     * We need a frequency map
     * the kth largest elements comes from the back
     *
     * [3,2,1,5,6,4], k = 2
     * list sorted [1, 2, 3, 4, 5, 6]
     * kth largest element is 5
     *
     * Without sorting, given an element, how do I know the position of it in the sorted array?
     * - How about maintaining a buffer of k elements sorted from minor to mayor, and
     * then return the first one
     *
     * The elements are not related. They are random
     * We cannot sort them
     * We don't know what element comes first
     *
     * Per each element, we need to check which position of it in the list is
     * -> Create an structure that for any new int, the buffer will remain sorted
     *
     */
    private fun findKthLargest(nums: IntArray, k: Int): Int {
        // 1. Init the values
        val maxComparator: Comparator<Int> = Comparator { o1, o2 -> o2 - o1 }
        val maxHeap = PriorityQueue(maxComparator)
        var result = 0
        var iteration = k

        // 2. Loop
        for (num in nums) {
            maxHeap.add(num)
        }

        // 3. Update the result
        while (iteration > 0) {
            result = maxHeap.poll()
            iteration--
        }

        return result
    }

    @Test
    fun test1() {
        // Given
        val nums = intArrayOf(3,2,1,5,6,4)
        val k = 2

        // When
        val result = findKthLargest(nums, k)

        // Then
        assertEquals(5, result)
    }

    @Test
    fun test2() {
        // Given
        val nums = intArrayOf(3,2,3,1,2,4,5,5,6)
        val k = 4

        // When
        val result = findKthLargest(nums, k)

        // Then
        assertEquals(4, result)
    }
}