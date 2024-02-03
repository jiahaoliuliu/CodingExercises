package arrayandlist.easy

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n,
 * representing the number of elements in nums1 and nums2 respectively.
 *
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 * The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
 * To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should
 * be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
 *
 * Example 1:
 *      Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 *      Output: [1,2,2,3,5,6]
 *      Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
 *      The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
 *
 * Example 2:
 *      Input: nums1 = [1], m = 1, nums2 = [], n = 0
 *      Output: [1]
 *      Explanation: The arrays we are merging are [1] and [].
 *      The result of the merge is [1].
 *
 * Example 3:
 *      Input: nums1 = [0], m = 0, nums2 = [1], n = 1
 *      Output: [1]
 *      Explanation: The arrays we are merging are [] and [1].
 *      The result of the merge is [1].
 *      Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result
 *      can fit in nums1.
 *
 * Constraints:
 *      nums1.length == m + n
 *      nums2.length == n
 *      0 <= m, n <= 200
 *      1 <= m + n <= 200
 *      -10^9 <= nums1[i], nums2[j] <= 10^9
 *
 * Follow up: Can you come up with an algorithm that runs in O(m + n) time?
 */
class MergeSortedArray {
    /**
     * Initial thoughts
     * The final solution is stored inside array1, thuse we need to override the content of it
     * The content of array starts from the first position, which means if the algorithm
     * starts from the beginning, the valid number need to be cached, otherwise they will be overridden
     * But if we start from the back, then the numbers can be kept
     *
     * So, having two pointer that iterate through the nums1 and nums2 will work
     * Algorithm 1.0
     * 1. init
     * array1Pos = m
     * array2Pos = n
     * 2. Loop
     * For all the positions(i) in array 1, coming from back to front
     *  if (array1[array1Pos] > array2[array2Pos])
     *   -> array1[i] = array1[array1Pos]
     *   -> array1Pos--
     *  else
     *   -> array1[i] = array2[array2Pos]
     *   -> array2Pos--
     *
     *  3. Return array1
     *
     *  Limitation of the algorithm
     *  -> array1Pos could reach to 0
     *    -> Add special condition inside loop
     *  -> array2Pos could reach to 0
     *    -> Add special condition inside loop
     *
     * Algorithm 2.0
     * 1. init
     * array1Pos = m - 1 // If the size = 3, array1Pos should be 2, because the index starts with 0
     * array2Pos = n - 1 // Same here
     * 2. Loop
     * For all the positions(i) in array 1, coming from back to front
     *  if (array1Pos == 0 && array2Pos > 0) // Since m + n = array1.length, when array1Pos == 0, array2Pos always > 0
     *   -> array1[i] = array2[array2Pos]
     *   -> array2Pos--
     *   -> continue on next loop
     *  if (array2Pos == 0 && array1Pos > 0) // Since m + n = array1.length, when array2Pos == 0, array1Pos always > 0
     *   -> array1[i] = array1[array1Pos]
     *   -> array1Pos--
     *   -> continue on next loop
     *
     *  if (array1[array1Pos] > array2[array2Pos])
     *   -> array1[i] = array1[array1Pos]
     *   -> array1Pos--
     *  else
     *   -> array1[i] = array2[array2Pos]
     *   -> array2Pos--
     *
     *  3. Return array1 -> Not need. The result is in array1
     * Corner cases
     * - m = 0 => Covered when array1Pos < 0
     * - n = 0 => Covered when array2Pos < 0
     *
     */
    private fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
//        * 1. init
        var array1Pos = m -1
        var array2Pos = n - 1
//        * 2. Loop
//        * For all the positions(i) in array 1, coming from back to front
        for (i in nums1.size - 1 downTo 0) {
//        *  if (array1Pos == 0 && array2Pos > 0) // Since m + n = array1.length, when array1Pos == 0, array2Pos always > 0
//        *   -> array1[i] = array2[array2Pos]
//        *   -> array2--
//        *   -> continue on next loop
            if (array1Pos < 0 && array2Pos >= 0) {
                nums1[i] = nums2[array2Pos]
                array2Pos--
                continue
            }

//        *  if (array2Pos == 0 && array1Pos > 0) // Since m + n = array1.length, when array2Pos == 0, array1Pos always > 0
//        *   -> array1[i] = array1[array1Pos]
//        *   -> array1Pos--
//        *   -> continue on next loop
            // Optimization -> If array2Pos < 0, there is not need to change num1
            if (array2Pos < 0 && array1Pos >= 0) {
//                nums1[i] = nums1[array1Pos]
//                array1Pos --
                continue
            }

//        *  if (array1[array1Pos] > array2[array2Pos])
//        *   -> array1[i] = array1[array1Pos]
//        *   -> array1Pos--
//        *  else
//        *   -> array1[i] = array2[array2Pos]
//        *   -> array2Pos--
            if (nums1[array1Pos] > nums2[array2Pos]) {
                nums1[i] = nums1[array1Pos]
                array1Pos--
            } else {
                nums1[i] = nums2[array2Pos]
                array2Pos--
            }
        }
    }

    @Test
    fun test1() {
        // Given
        val nums1 = intArrayOf(1, 2, 3, 0, 0, 0)
        val m = 3
        val nums2 = intArrayOf(2, 5, 6)
        val n = 3

        // When
        merge(nums1, m, nums2, n)

        // Then
        assertTrue(intArrayOf(1, 2, 2, 3, 5, 6) contentEquals nums1)
    }

    @Test
    fun test2() {
        // Given
        val nums1 = intArrayOf(1)
        val m = 1
        val nums2 = intArrayOf()
        val n = 0

        // When
        merge(nums1, m, nums2, n)

        // Then
        assertTrue(intArrayOf(1) contentEquals nums1)
    }

    @Test
    fun test3() {
        // Given
        val nums1 = intArrayOf(0)
        val m = 0
        val nums2 = intArrayOf(1)
        val n = 1

        // When
        merge(nums1, m, nums2, n)

        // Then
        assertTrue(intArrayOf(1) contentEquals nums1)
    }

}